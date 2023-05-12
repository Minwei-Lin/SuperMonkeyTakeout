package com.minwei.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.minwei.common.Result;
import com.minwei.dto.DishDTO;
import com.minwei.pojo.Dish;
import com.minwei.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 分页查询菜品
     * @param page 当前页
     * @param pageSize 每页记录数
     * @return
     */
    @GetMapping("/page")
    public Result page(Integer page,Integer pageSize){
        //构建分页构造器
        Page pageInfo = new Page(page,pageSize);
        //创建条件构造器
        LambdaQueryWrapper<Dish> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(Dish::getSort);
        dishService.page(pageInfo);
        return Result.success(pageInfo);
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

