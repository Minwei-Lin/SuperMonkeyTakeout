package com.minwei.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.minwei.common.CustomException;
import com.minwei.common.Result;
import com.minwei.pojo.Category;
import com.minwei.pojo.Dish;
import com.minwei.pojo.Setmeal;
import com.minwei.service.CategoryService;
import com.minwei.service.DishService;
import com.minwei.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜品及套餐分类 前端控制器
 * </p>
 *
 * @author minwei
 * @since 2023-05-10 11:07:25
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private DishService dishService;
    @Autowired
    private SetmealService setmealService;
    /**
     * 添加菜品/套餐分类
     * @param category
     * @return
     */
    @PostMapping
    public Result addDishesCategory(@RequestBody Category category){
        categoryService.save(category);
        return Result.success("新增菜品成功");
    }

    /**
     * 分页查询菜品/套餐分类
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public Result page(int page,int pageSize){
        //构建分页构造器
        Page pageInfo = new Page(page,pageSize);
        //构建条件构造器
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper();
        //添加排序条件
        wrapper.orderByAsc(Category::getSort);
        //根据条件构造器查询
        categoryService.page(pageInfo,wrapper);
        return Result.success(pageInfo);
    }

    /**
     * 修改菜品/套餐分类
     * @param category
     * @return
     */
    @PutMapping
    public Result editCategory(@RequestBody Category category){
        categoryService.updateById(category);
        return Result.success("修改成功");
    }

    /**
     * 根据ID删除分类，并在删除前判断是否已经关联了菜品或套餐
     * @param id
     * @return
     */
    @DeleteMapping
    public Result deleted(Long id){
        //查询当前分类是否关联了菜品，如已关联，抛出业务异常
        LambdaQueryWrapper<Dish> dishWrapper = new LambdaQueryWrapper<>();
        //添加查询条件，根据分类ID查询
        dishWrapper.eq(Dish::getCategoryId,id);
        //查询是否有菜品
        int countDish = dishService.count(dishWrapper);
        if (countDish>0){
            //已经关联了菜品，抛出业务异常
            throw new CustomException("当前分类下关联了菜品，无法删除");
        }
        //查询当前分类是否关联了套餐，如已关联，抛出业务异常
        LambdaQueryWrapper<Setmeal> setmealWrapper = new LambdaQueryWrapper<>();
        //添加查询条件，根据分类ID查询
        setmealWrapper.eq(Setmeal::getCategoryId,id);
        //查询是否有菜品
        int countSetmeaal = setmealService.count(setmealWrapper);
        if (countSetmeaal>0){
            //说明已经关联了套餐，抛出业务异常
            throw new CustomException("当前分类下关联了套餐，无法删除");
        }
        //执行删除
        categoryService.removeById(id);
        return Result.success("删除成功");
    }

    /**
     * 获取分类列表
     * @return
     */
    @GetMapping("/list")
    public Result ListCategory(Category category){
        //创建条件构造器
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        //添加条件
        wrapper.eq(category.getType()!=null,Category::getType,category.getType());
        //添加排序条件
        wrapper.orderByAsc(Category::getSort).orderByDesc(Category::getCreateTime);
        //查询
        List<Category> list = categoryService.list(wrapper);
        return Result.success(list);
    }
}

