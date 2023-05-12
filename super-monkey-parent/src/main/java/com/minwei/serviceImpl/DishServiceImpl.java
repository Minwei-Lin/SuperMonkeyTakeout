package com.minwei.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.minwei.dto.DishDTO;
import com.minwei.mapper.DishMapper;
import com.minwei.pojo.Dish;
import com.minwei.pojo.DishFlavor;
import com.minwei.service.DishFlavorService;
import com.minwei.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜品管理 服务实现类
 * </p>
 *
 * @author minwei
 * @since 2023-05-10 11:07:25
 */
@Service
@Slf4j
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Autowired
    private DishFlavorService dishFlavorService;

    /**
     * 新增菜品
     * @param dishDTO
     */
    @Transactional
    @Override
    public void saveWithFlavor(DishDTO dishDTO) {
        //保存菜品的基本信息到菜品表dish
        this.save(dishDTO);
        //获取DishDTO中的菜品ID
        Long dishId = dishDTO.getId();
        //将菜品口味集合遍历出来，并将菜品口味集合中的菜品ID初始化
        List<DishFlavor> flavors = dishDTO.getFlavors();
        flavors = flavors.stream().map((item)->{
           item.setDishId(dishId);
           return item;
        }).collect(Collectors.toList());
        //保存菜品口味数据到菜品口味表dish falvor表
        dishFlavorService.saveBatch(flavors);
    }
}
