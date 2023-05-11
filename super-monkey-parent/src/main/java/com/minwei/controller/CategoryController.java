package com.minwei.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.minwei.common.Result;
import com.minwei.pojo.Category;
import com.minwei.pojo.Employee;
import com.minwei.service.CategoryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

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

    /**
     * 添加菜品/套餐分类
     * @param request
     * @param category
     * @return
     */
    @PostMapping
    public Result addDishesCategory(HttpServletRequest request, @RequestBody Category category){
        //添加创建时间
        category.setCreateTime(new Date());
        //添加更改时间（初始化）
        category.setUpdateTime(new Date());
        //获取当前登录用户的ID
        Long empId = (Long) request.getSession().getAttribute("employee");
        category.setCreateUser(empId);
        category.setUpdateUser(empId);
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
        wrapper.orderByDesc(Category::getSort);
        //根据条件构造器查询
        categoryService.page(pageInfo,wrapper);
        return Result.success(pageInfo);
    }

    /**
     * 修改菜品/套餐分类
     * @param request
     * @param category
     * @return
     */
    @PutMapping
    public Result editCategory(HttpServletRequest request,@RequestBody Category category){
        //添加修改修改日期
        category.setUpdateTime(new Date());
        //获取当前登录的账户
        Long empId = (Long) request.getSession().getAttribute("employee");
        //添加修改的账户
        category.setUpdateUser(empId);
        categoryService.updateById(category);
        return Result.success("修改成功");
    }
}

