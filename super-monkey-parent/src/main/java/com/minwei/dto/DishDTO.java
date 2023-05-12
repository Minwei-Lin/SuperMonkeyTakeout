package com.minwei.dto;

import com.minwei.pojo.Dish;
import com.minwei.pojo.DishFlavor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: DishDTO
 * @Author linminwei
 * @Package com.minwei.dto
 * @Date 2023/5/12 15:07
 * @description: Dish的数据传输对象，用于封装页面提交的数据
 */
@Data
public class DishDTO extends Dish {
    private List<DishFlavor> flavors = new ArrayList<>();
}
