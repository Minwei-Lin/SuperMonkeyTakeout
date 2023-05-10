package com.minwei.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.minwei.pojo.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author minwei
 * @since 2023-05-10 11:07:25
 */
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {

}
