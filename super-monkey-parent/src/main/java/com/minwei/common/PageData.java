package com.minwei.common;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: PageData
 * @Author linminwei
 * @Package com.minwei.common
 * @Date 2023/5/10 11:17
 * @description: 分页响应对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("分页响应对象")
public class PageData {
    @ApiModelProperty("总记录数")
    private Long total;
    @ApiModelProperty("分页数据")
    private Object data;
}
