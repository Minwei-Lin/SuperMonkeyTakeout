package com.minwei.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.minwei.pojo.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 购物车 Mapper 接口
 * </p>
 *
 * @author minwei
 * @since 2023-05-10 11:07:25
 */
@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {

}
