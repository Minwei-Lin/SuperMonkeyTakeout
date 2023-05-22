package com.minwei.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.minwei.dto.SetmealDto;
import com.minwei.mapper.SetmealMapper;
import com.minwei.pojo.Category;
import com.minwei.pojo.Setmeal;
import com.minwei.pojo.SetmealDish;
import com.minwei.service.CategoryService;
import com.minwei.service.SetmealDishService;
import com.minwei.service.SetmealService;
import com.minwei.vo.SetmealVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 套餐 服务实现类
 * </p>
 *
 * @author minwei
 * @since 2023-05-10 11:07:25
 */
@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Autowired
    private SetmealMapper setmealMapper;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SetmealDishService setmealDishService;

    @Override
    public Page<SetmealVo> setmealByPage(Integer page, Integer pageSize, String name) {
        //构建分页构造器
        Page<Setmeal> pageInfo =  new Page<>(page,pageSize);
        Page<SetmealVo> setmealVoPage = new Page<>(page,pageSize);
        //构建条件构造器
        LambdaQueryWrapper<Setmeal> wrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        wrapper.like(StringUtils.isNotEmpty(name),Setmeal::getName,name);
        //添加排序条件
        wrapper.orderByDesc(Setmeal::getUpdateTime);
        //执行查询
        setmealMapper.selectPage(pageInfo, wrapper);
        //将查询出的结果拷贝至Vo的分页对象中,忽略records
        BeanUtils.copyProperties(page,setmealVoPage,"records");
        List<Setmeal> records = pageInfo.getRecords();
        List<SetmealVo> list = records.stream().map((itme)->{
            SetmealVo setmealVo  = new SetmealVo();
            BeanUtils.copyProperties(itme,setmealVo);
            Long categoryId = itme.getCategoryId();
            //根据ID查询分类对象
            Category category = categoryService.getById(categoryId);
            String categoryName = category.getName();
            setmealVo.setCategoryName(categoryName);
            return setmealVo;
        }).collect(Collectors.toList());
        setmealVoPage.setRecords(list);
        return setmealVoPage;
    }

    /**
     * 保存套餐
     * @param setmealDto
     */
    @Override
    @Transactional
    public void addSetmealWithDish(SetmealDto setmealDto) {
        //保存套餐信息
        setmealMapper.insert(setmealDto);
        //遍历套餐中的菜品
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes.stream().map((item)->{
            item.setSetmealId(String.valueOf(setmealDto.getId()));
            return item;
        }).collect(Collectors.toList());
        //保存套餐和菜品的关联信息
        setmealDishService.saveBatch(setmealDishes);
    }

    @Override
    public void updateSetmealWithDish(SetmealDto setmealDto) {
        //更新套餐信息
        setmealMapper.updateById(setmealDto);
        //先清空当前套餐对应的菜品
        LambdaQueryWrapper<SetmealDish> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SetmealDish::getSetmealId,setmealDto.getId());
        setmealDishService.remove(wrapper);
        //遍历套餐中的菜品
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes.stream().map((item)->{
            item.setSetmealId(String.valueOf(setmealDto.getId()));
            return item;
        }).collect(Collectors.toList());
        //更新套餐和菜品的关联信息
        setmealDishService.saveBatch(setmealDishes);
    }

    @Override
    public SetmealDto querySeteamlDetails(Long id) {
        //查询套餐详细信息
        Setmeal setmeal = setmealMapper.selectById(id);
        //属性拷贝
        SetmealDto setmealDto = new SetmealDto();
        BeanUtils.copyProperties(setmeal,setmealDto);
        //查询套餐所对应的菜品
        //1.构建条件构造器
        LambdaQueryWrapper<SetmealDish> wrapper = new LambdaQueryWrapper();
        //2.构建查询条件
        wrapper.eq(SetmealDish::getSetmealId,setmeal.getId());
        List<SetmealDish> list = setmealDishService.list(wrapper);
        setmealDto.setSetmealDishes(list);
        return setmealDto;
    }

    @Override
    public void deletedSetmeals(List<Long> ids) {
        //删除套餐信息
        this.removeByIds(ids);
        for (Long id : ids) {
            //删除套餐对应的菜品信息
            LambdaQueryWrapper<SetmealDish> wrapper = new LambdaQueryWrapper();
            wrapper.eq(SetmealDish::getSetmealId,id);
            setmealDishService.remove(wrapper);
        }
    }

    @Override
    public void setmealStatusByStatus(Integer status, Long[] ids) {
        for (Long id : ids) {
            //通过ID查询套餐
            Setmeal setmeal = this.getById(id);
            //设置套餐状态
            setmeal.setStatus(status);
            //更新套餐信息
            this.updateById(setmeal);
        }
    }


}
