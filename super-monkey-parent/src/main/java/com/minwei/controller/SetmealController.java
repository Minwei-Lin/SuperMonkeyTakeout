package com.minwei.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.minwei.common.Result;
import com.minwei.dto.SetmealDto;
import com.minwei.service.SetmealService;
import com.minwei.vo.SetmealVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Result<Page<SetmealVo>> page(Integer page,Integer pageSize,String name){
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
        return Result.success("添加套餐成功");
    }

    /**
     * 修改套餐
     * @param setmealDto
     * @return
     */
    @PutMapping
    public Result updateSetmealWithDish(@RequestBody SetmealDto setmealDto){
        setmealService.updateSetmealWithDish(setmealDto);
        return Result.success("套餐修改成功");
    }

    /**
     * 根据ID查询套餐详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<SetmealDto> querySeteamlDetails(@PathVariable Long id){
        SetmealDto setmealDto = setmealService.querySeteamlDetails(id);
        return Result.success(setmealDto);
    }


    /**
     * 删除套餐
     * @param ids
     * @return
     */
    @DeleteMapping
    public Result deletedSetmeals(@RequestParam List<Long> ids){
        setmealService.deletedSetmeals(ids);
        return Result.success("删除套餐成功");
    }

    /**
     * 启停售套餐
     * @param status
     * @param ids
     * @return
     */
    @PostMapping("/status/{status}")
    public Result setmealStatusByStatus(@PathVariable Integer status,Long[] ids){
        setmealService.setmealStatusByStatus(status,ids);
        return Result.success("启停售套餐成功");
    }
}

