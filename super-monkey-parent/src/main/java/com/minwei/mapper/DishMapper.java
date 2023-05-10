package com.minwei.mapper;

import com.pojo.Dish;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 菜品管理 Mapper 接口
 * </p>
 *
 * @author minwei
 * @since 2023-05-10 11:07:25
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {

}
