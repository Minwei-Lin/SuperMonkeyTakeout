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
 * 菜品及套餐分类
 * </p>
 *
 * @author minwei
 * @since 2023-05-10 11:07:25
 */
@Getter
@Setter
@TableName("category")
@ApiModel(value = "Category对象", description = "菜品及套餐分类")
public class Category {

    @ApiModelProperty("主键")
      @TableId("id")
    private Long id;

    @ApiModelProperty("类型   1 菜品分类 2 套餐分类")
    @TableField("type")
    private Integer type;

    @ApiModelProperty("分类名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("顺序")
    @TableField("sort")
    private Integer sort;

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


}
