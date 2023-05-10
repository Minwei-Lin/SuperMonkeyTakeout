package com.minwei.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Title: Result
 * @Author linminwei
 * @Package com.minwei.common
 * @Date 2023/5/10 9:21
 * @description: 统一的响应对象
 */

@Data
@ApiModel("统一的响应对象")
public class Result {
    @ApiModelProperty("响应状态码")
    private Integer code;
    @ApiModelProperty("响应提示信息")
    private String message;
    @ApiModelProperty("响应数据")
    private Object data;

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
