package com.minwei.common;

/**
 * @Title: BaseContext
 * @Author linminwei
 * @Package com.minwei.common
 * @Date 2023/5/12 10:05
 * @description: 基于ThreadLocal封装工具类，用于保存和获取当期登录用户的ID
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }
    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
