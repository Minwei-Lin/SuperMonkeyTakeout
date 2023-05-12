package com.minwei.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.minwei.dto.DishDTO;
import com.minwei.mapper.DishMapper;
import com.minwei.pojo.Dish;
import com.minwei.pojo.DishFlavor;
import com.minwei.service.DishFlavorService;
import com.minwei.service.DishService;
import com.minwei.vo.DishVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜品管理 服务实现类
 * </p>
 *
 * @author minwei
 * @since 2023-05-10 11:07:25
 */
@Service
@Slf4j
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Autowired
    private DishFlavorService dishFlavorService;

    /**
     * 新增菜品
     * @param dishDTO
     */
    @Transactional
    @Override
    public void saveWithFlavor(DishDTO dishDTO) {
        //保存菜品的基本信息到菜品表dish
        this.save(dishDTO);
        //获取DishDTO中的菜品ID
        Long dishId = dishDTO.getId();
        //将菜品口味集合遍历出来，并将菜品口味集合中的菜品ID初始化
        List<DishFlavor> flavors = dishDTO.getFlavors();
        flavors = flavors.stream().map((item)->{
           item.setDishId(dishId);
           return item;
        }).collect(Collectors.toList());
        //保存菜品口味数据到菜品口味表dish falvor表
        dishFlavorService.saveBatch(flavors);
    }

    /**
     * 根据ID查询对应的菜品信息和口味信息
     * @param id
     * @return
     */
    @Override
    public DishVo getByIdWithFlavor(Long id) {
        //查询菜品基本信息
        Dish dish = this.getById(id);
        DishVo dishVo = new DishVo();
        BeanUtils.copyProperties(dish,dishVo);
        //查询口味基本信息
        LambdaQueryWrapper<DishFlavor> wrapper = new LambdaQueryWrapper();
        wrapper.eq(DishFlavor::getDishId,dish.getId());
        List<DishFlavor> flavors = dishFlavorService.list(wrapper);
        dishVo.setFlavors(flavors);
        return dishVo;
    }

    /**
     * 更新菜品信息
     * @param dishVo
     */
    @Override
    public void updateDishWithFlavor(DishVo dishVo) {
        //更新dish表
        this.updateById(dishVo);
        //先清理当前菜品对应的口味数据
        LambdaQueryWrapper<DishFlavor> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DishFlavor::getId,dishVo.getId());
        dishFlavorService.remove(wrapper);
        //再重新添加当前提交过来的口味数据
        List<DishFlavor> flavors = dishVo.getFlavors();
        flavors = flavors.stream().map((item)->{
            item.setDishId(dishVo.getId());
            return item;
        }).collect(Collectors.toList());
        dishFlavorService.saveBatch(flavors);
    }

    /**
     * 删除菜品
     * @param ids
     */
    @Override
    public void deleteDishWithFlavor(List<Long> ids) {
        //删除菜品信息
        this.removeByIds(ids);
        for (Long id : ids) {
            //删除菜品口味信息
            LambdaQueryWrapper<DishFlavor> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(DishFlavor::getDishId,id);
            dishFlavorService.remove(wrapper);
        }
    }

    /**
     * 菜品启停售
     * @param status
     * @param ids
     */
    @Override
    public void updateDishStatus(Integer status, Long[] ids) {
        for (Long id : ids) {
            //通过ID查询菜品
            Dish dish = this.getById(id);
            //更改状态
            dish.setStatus(status);
            this.updateById(dish);
        }
    }
}
