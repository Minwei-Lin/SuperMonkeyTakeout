package com.minwei.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @Title: GlobalExceptionHandler
 * @Author linminwei
 * @Package com.minwei.common
 * @Date 2023/5/11 16:50
 * @description: 全局异常处理器
 */
//Spring MVC对异常进行统一处理的注解
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 异常处理方法
     * @param ex
     * @return
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Result<String> exceptionHandler(SQLIntegrityConstraintViolationException ex){
        log.error(ex.getMessage());
        if (ex.getMessage().contains("Duplicate entry")){
            String[] split = ex.getMessage().split(" ");
            String msg = "账号 "+ split[2] + " 已存在";
            return Result.error(msg);
        }
        return Result.error("未知错误");
    }
}
