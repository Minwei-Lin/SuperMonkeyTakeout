package com.minwei.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 套餐
 * </p>
 *
 * @author minwei
 * @since 2023-05-10 11:07:25
 */
@Data
@TableName("setmeal")
@ApiModel(value = "Setmeal对象", description = "套餐")
public class Setmeal {

    @ApiModelProperty("主键")
    @TableId("id")
    private Long id;

    @ApiModelProperty("菜品分类id")
    @TableField("category_id")
    private Long categoryId;

    @ApiModelProperty("套餐名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("套餐价格")
    @TableField("price")
    private BigDecimal price;

    @ApiModelProperty("状态 0:停用 1:启用")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("编码")
    @TableField("code")
    private String code;

    @ApiModelProperty("描述信息")
    @TableField("description")
    private String description;

    @ApiModelProperty("图片")
    @TableField("image")
    private String image;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    @ApiModelProperty("创建人")
    @TableField(value = "create_user",fill = FieldFill.INSERT)
    private Long createUser;

    @ApiModelProperty("修改人")
    @TableField(value = "update_user",fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    @ApiModelProperty("是否删除")
    @TableField("is_deleted")
    @TableLogic(value = "0",delval = "1")
    private Integer isDeleted;
}
