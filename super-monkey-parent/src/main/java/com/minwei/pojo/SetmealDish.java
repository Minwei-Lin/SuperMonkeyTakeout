package com.minwei.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 套餐菜品关系
 * </p>
 *
 * @author minwei
 * @since 2023-05-10 11:07:25
 */
@Getter
@Setter
@TableName("setmeal_dish")
@ApiModel(value = "SetmealDish对象", description = "套餐菜品关系")
public class SetmealDish {

    @ApiModelProperty("主键")
    @TableId("id")
    private Long id;

    @ApiModelProperty("套餐id ")
    @TableField("setmeal_id")
    private String setmealId;

    @ApiModelProperty("菜品id")
    @TableField("dish_id")
    private String dishId;

    @ApiModelProperty("菜品名称 （冗余字段）")
    @TableField("name")
    private String name;

    @ApiModelProperty("菜品原价（冗余字段）")
    @TableField("price")
    private BigDecimal price;

    @ApiModelProperty("份数")
    @TableField("copies")
    private Integer copies;

    @ApiModelProperty("排序")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    @ApiModelProperty("创建人")
    @TableField("create_user")
    private Long createUser;

    @ApiModelProperty("修改人")
    @TableField("update_user")
    private Long updateUser;

    @ApiModelProperty("是否删除")
    @TableField("is_deleted")
    private Integer isDeleted;


}
