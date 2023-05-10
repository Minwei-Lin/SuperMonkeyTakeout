package com.minwei.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 菜品口味关系表
 * </p>
 *
 * @author minwei
 * @since 2023-05-10 11:07:25
 */
@Getter
@Setter
@TableName("dish_flavor")
@ApiModel(value = "DishFlavor对象", description = "菜品口味关系表")
public class DishFlavor {

    @ApiModelProperty("主键")
      @TableId("id")
    private Long id;

    @ApiModelProperty("菜品")
    @TableField("dish_id")
    private Long dishId;

    @ApiModelProperty("口味名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("口味数据list")
    @TableField("value")
    private String value;

    @ApiModelProperty("创建时间")
      @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
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
