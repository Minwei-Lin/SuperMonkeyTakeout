package com.minwei;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Title: com.woniu.SuperMonkeyTakeOutApplication
 * @Author linminwei
 * @Package PACKAGE_NAME
 * @Date 2023/5/9 15:50
 * @description: 超级猩猩外卖父工程启动类
 */
@Slf4j
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
//@EnableTransactionManagement
public class SuperMonkeyTakeOutApplication {
    public static void main(String[] args) {
        SpringApplication.run(SuperMonkeyTakeOutApplication.class,args);
        log.info("项目启动");
    }
}
