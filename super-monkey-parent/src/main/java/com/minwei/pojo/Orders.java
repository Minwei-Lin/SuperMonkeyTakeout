package com.minwei.pojo;

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
 * 订单表
 * </p>
 *
 * @author minwei
 * @since 2023-05-10 11:07:25
 */
@Getter
@Setter
@TableName("orders")
@ApiModel(value = "Orders对象", description = "订单表")
public class Orders {

    @ApiModelProperty("主键")
    @TableId("id")
    private Long id;

    @ApiModelProperty("订单号")
    @TableField("number")
    private String number;

    @ApiModelProperty("订单状态 1待付款，2待派送，3已派送，4已完成，5已取消")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("下单用户")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("地址id")
    @TableField("address_book_id")
    private Long addressBookId;

    @ApiModelProperty("下单时间")
    @TableField("order_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date orderTime;

    @ApiModelProperty("结账时间")
    @TableField("checkout_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date checkoutTime;

    @ApiModelProperty("支付方式 1微信,2支付宝")
    @TableField("pay_method")
    private Integer payMethod;

    @ApiModelProperty("实收金额")
    @TableField("amount")
    private BigDecimal amount;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;

    @TableField("phone")
    private String phone;

    @TableField("address")
    private String address;

    @TableField("user_name")
    private String userName;

    @TableField("consignee")
    private String consignee;


}
