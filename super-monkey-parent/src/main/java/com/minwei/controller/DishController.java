package com.minwei.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.minwei.common.Result;
import com.minwei.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class DishController {

    @Autowired
    private DishService dishService;

    @GetMapping("/page")
    public Result page(Integer page,Integer pageSize){
        //构建分页构造器
        Page pageInfo = new Page(page,pageSize);
        dishService.page(pageInfo);
        return Result.success(pageInfo);
    }

}

