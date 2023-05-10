package com.minwei.serviceImpl;

import com.pojo.ShoppingCart;
import com.mapper.ShoppingCartMapper;
import com.service.ShoppingCartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 购物车 服务实现类
 * </p>
 *
 * @author minwei
 * @since 2023-05-10 11:07:25
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {

}
