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

import java.util.Date;

/**
 * <p>
 * 地址管理
 * </p>
 *
 * @author minwei
 * @since 2023-05-10 11:07:25
 */
@Getter
@Setter
@TableName("address_book")
@ApiModel(value = "AddressBook对象", description = "地址管理")
public class AddressBook {

    @ApiModelProperty("主键")
    @TableId("id")
    private Long id;

    @ApiModelProperty("用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("收货人")
    @TableField("consignee")
    private String consignee;

    @ApiModelProperty("性别 0 女 1 男")
    @TableField("sex")
    private Integer sex;

    @ApiModelProperty("手机号")
    @TableField("phone")
    private String phone;

    @ApiModelProperty("省级区划编号")
    @TableField("province_code")
    private String provinceCode;

    @ApiModelProperty("省级名称")
    @TableField("province_name")
    private String provinceName;

    @ApiModelProperty("市级区划编号")
    @TableField("city_code")
    private String cityCode;

    @ApiModelProperty("市级名称")
    @TableField("city_name")
    private String cityName;

    @ApiModelProperty("区级区划编号")
    @TableField("district_code")
    private String districtCode;

    @ApiModelProperty("区级名称")
    @TableField("district_name")
    private String districtName;

    @ApiModelProperty("详细地址")
    @TableField("detail")
    private String detail;

    @ApiModelProperty("标签")
    @TableField("label")
    private String label;

    @ApiModelProperty("默认 0 否 1是")
    @TableField("is_default")
    private Boolean isDefault;

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
    private Integer isDeleted;


}
