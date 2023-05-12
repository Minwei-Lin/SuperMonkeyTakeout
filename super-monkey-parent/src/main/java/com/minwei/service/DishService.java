package com.minwei.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.minwei.dto.DishDTO;
import com.minwei.pojo.Dish;
import com.minwei.vo.DishVo;

import java.util.List;

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


    DishVo getByIdWithFlavor(Long id);

    void updateDishWithFlavor(DishVo dishVo);

    void deleteDishWithFlavor(List<Long> ids);

    void updateDishStatus(Integer status, Long[] ids);

}
