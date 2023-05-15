package com.minwei.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.minwei.common.Result;
import com.minwei.dto.SetmealDto;
import com.minwei.service.SetmealService;
import com.minwei.vo.SetmealVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 套餐 前端控制器
 * </p>
 *
 * @author minwei
 * @since 2023-05-10 11:07:25
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Autowired
    private SetmealService setmealService;

    /**
     * 分页查询套餐
     * @param page 当前页数
     * @param pageSize 每页记录数
     * @param name
     * @return
     */
    @GetMapping("/page")
    public Result page(Integer page,Integer pageSize,String name){
        Page<SetmealVo> pageInfo = setmealService.setmealByPage(page, pageSize, name);
        return Result.success(pageInfo);
    }

    /**
     * 保存套餐
     * @param setmealDto
     * @return
     */
    @PostMapping
    public Result addSetmealWithDish(@RequestBody SetmealDto setmealDto){
        setmealService.addSetmealWithDish(setmealDto);
        return Result.success("套餐添加成功");
    }
}

