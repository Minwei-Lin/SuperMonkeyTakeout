package com.woniu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Title: WebMvcConfig
 * @Author linminwei
 * @Package com.woniu.config
 * @Date 2023/5/9 16:49
 * @description: 前端资源放行配置类
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    /**
     * 设置静态资源映射
     */

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        //添加映射
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
    }
}
