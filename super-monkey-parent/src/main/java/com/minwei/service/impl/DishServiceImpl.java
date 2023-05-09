package com.minwei.service.impl;

import com.minwei.pojo.Dish;
import com.minwei.mapper.DishMapper;
import com.minwei.service.DishService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜品管理 服务实现类
 * </p>
 *
 * @author minwei
 * @since 2023-05-09
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

}
