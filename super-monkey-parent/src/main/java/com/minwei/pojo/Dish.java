package com.minwei.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 菜品管理
 * </p>
 *
 * @author minwei
 * @since 2023-05-10 11:07:25
 */
@Getter
@Setter
@TableName("dish")
@ApiModel(value = "Dish对象", description = "菜品管理")
public class Dish {

    @ApiModelProperty("主键")
      @TableId("id")
    private Long id;

    @ApiModelProperty("菜品名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("菜品分类id")
    @TableField("category_id")
    private Long categoryId;

    @ApiModelProperty("菜品价格")
    @TableField("price")
    private BigDecimal price;

    @ApiModelProperty("商品码")
    @TableField("code")
    private String code;

    @ApiModelProperty("图片")
    @TableField("image")
    private String image;

    @ApiModelProperty("描述信息")
    @TableField("description")
    private String description;

    @ApiModelProperty("0 停售 1 起售")
    @TableField("status")
    private Integer status;

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

    @ApiModelProperty("是否删除")
    @TableField("is_deleted")
    private Integer isDeleted;


}
