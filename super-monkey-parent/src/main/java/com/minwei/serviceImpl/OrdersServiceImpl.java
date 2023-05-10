package com.minwei.serviceImpl;

import com.pojo.Orders;
import com.mapper.OrdersMapper;
import com.service.OrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author minwei
 * @since 2023-05-10 11:07:25
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

}
