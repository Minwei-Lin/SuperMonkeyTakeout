package com.minwei.service.impl;

import com.minwei.pojo.OrderDetail;
import com.minwei.mapper.OrderDetailMapper;
import com.minwei.service.OrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单明细表 服务实现类
 * </p>
 *
 * @author minwei
 * @since 2023-05-09
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

}
