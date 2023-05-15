package com.minwei.vo;

import com.minwei.pojo.Category;
import com.minwei.pojo.Setmeal;
import lombok.Data;

import java.util.List;

@Data
public class SetmealVo extends Setmeal {
    private String CategoryName;
    private List<Category> categoryList;
}
