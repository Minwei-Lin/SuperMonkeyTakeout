package com.minwei.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.minwei.dto.DishDTO;
import com.minwei.pojo.Dish;

/**
 * <p>
 * 菜品管理 服务类
 * </p>
 *
 * @author minwei
 * @since 2023-05-10 11:07:25
 */
public interface DishService extends IService<Dish> {

    void saveWithFlavor(DishDTO dishDTO);
}
