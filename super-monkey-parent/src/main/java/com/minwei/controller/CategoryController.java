package com.minwei.controller;


import com.minwei.common.Result;
import com.minwei.pojo.Category;
import com.minwei.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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

}

