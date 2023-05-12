package com.minwei.common;

/**
 * @Title: CustomException
 * @Author linminwei
 * @Package com.minwei.common
 * @Date 2023/5/12 11:24
 * @description: 自定义业务异常
 */
public class CustomException extends RuntimeException{
    public CustomException(String message){
        super(message);
    }
}
