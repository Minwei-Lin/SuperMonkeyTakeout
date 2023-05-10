package com.minwei.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.minwei.pojo.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 菜品及套餐分类 Mapper 接口
 * </p>
 *
 * @author minwei
 * @since 2023-05-10 11:07:25
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
