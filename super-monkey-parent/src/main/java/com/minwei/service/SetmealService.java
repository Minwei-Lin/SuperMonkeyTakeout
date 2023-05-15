package com.minwei.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.minwei.dto.SetmealDto;
import com.minwei.pojo.Setmeal;
import com.minwei.vo.SetmealVo;

/**
 * <p>
 * 套餐 服务类
 * </p>
 *
 * @author minwei
 * @since 2023-05-10 11:07:25
 */
public interface SetmealService extends IService<Setmeal> {

    Page<SetmealVo> setmealByPage(Integer page, Integer pageSize, String name);

    void addSetmealWithDish(SetmealDto setmealDto);
}
