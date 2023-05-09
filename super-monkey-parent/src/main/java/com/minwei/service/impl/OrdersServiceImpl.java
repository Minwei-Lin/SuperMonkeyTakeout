package com.minwei.service.impl;

import com.minwei.pojo.Orders;
import com.minwei.mapper.OrdersMapper;
import com.minwei.service.OrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author minwei
 * @since 2023-05-09
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

}
