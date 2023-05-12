package com.minwei.vo;

import com.minwei.pojo.Dish;
import lombok.Data;

/**
 * @Title: DishVo
 * @Author linminwei
 * @Package com.minwei.vo
 * @Date 2023/5/12 18:03
 * @description: Dish的展示模型
 */
@Data
public class DishVo extends Dish {
    private String categoryName;
}
