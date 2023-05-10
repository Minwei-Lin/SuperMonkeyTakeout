package com.minwei.serviceImpl;

import com.pojo.DishFlavor;
import com.mapper.DishFlavorMapper;
import com.service.DishFlavorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜品口味关系表 服务实现类
 * </p>
 *
 * @author minwei
 * @since 2023-05-10 11:07:25
 */
@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {

}
