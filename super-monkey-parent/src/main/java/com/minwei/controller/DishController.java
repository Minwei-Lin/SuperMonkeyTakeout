package com.minwei.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.minwei.common.Result;
import com.minwei.dto.DishDTO;
import com.minwei.pojo.Category;
import com.minwei.pojo.Dish;
import com.minwei.service.CategoryService;
import com.minwei.service.DishService;
import com.minwei.vo.DishVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜品管理 前端控制器
 * </p>
 *
 * @author minwei
 * @since 2023-05-10 11:07:25
 */
@RestController
@RequestMapping("/dish")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 分页查询菜品
     * @param page 当前页
     * @param pageSize 每页记录数
     * @return
     */
    @GetMapping("/page")
    public Result page(Integer page,Integer pageSize,String name){
        //构建分页构造器
        Page<Dish> pageInfo = new Page(page,pageSize);
        Page<DishVo> dishVoPage = new Page<>(page,pageSize);
        //创建条件构造器
        LambdaQueryWrapper<Dish> wrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        wrapper.like(name!=null,Dish::getName,name);
        wrapper.orderByAsc(Dish::getSort);
        //执行查询
        dishService.page(pageInfo,wrapper);
        //对象拷贝,分页中的records不需要拷贝，忽略掉
        BeanUtils.copyProperties(pageInfo,dishVoPage,"records");
        List<Dish> records = pageInfo.getRecords();
        List<DishVo> list = records.stream().map((item)->{
            DishVo dishVo = new DishVo();
            BeanUtils.copyProperties(item,dishVo);
            Long categoryId = item.getCategoryId();
            //根据ID查询分类对象
            Category category = categoryService.getById(categoryId);
            String categoryName = category.getName();
            dishVo.setCategoryName(categoryName);
            return dishVo;
        }).collect(Collectors.toList());
        dishVoPage.setRecords(list);
        return Result.success(dishVoPage);
    }

    /**
     * 添加菜品
     * @param dishDTO
     * @return
     */
    @PostMapping
    public Result addDish(@RequestBody DishDTO dishDTO){
        dishService.saveWithFlavor(dishDTO);
        return Result.success("添加成功");
    }

}

