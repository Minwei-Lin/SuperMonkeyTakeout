package com.minwei.serviceImpl;

import com.pojo.Dish;
import com.mapper.DishMapper;
import com.service.DishService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜品管理 服务实现类
 * </p>
 *
 * @author minwei
 * @since 2023-05-10 11:07:25
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

}
