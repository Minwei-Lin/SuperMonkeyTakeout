package com.minwei.dto;

import com.minwei.pojo.Setmeal;
import com.minwei.pojo.SetmealDish;
import lombok.Data;

import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;
    private String categoryName;
}
