package com.woniu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Title: com.woniu.SuperMonkeyTakeOutTest
 * @Author linminwei
 * @Package PACKAGE_NAME
 * @Date 2023/5/9 15:50
 * @description: 超级猩猩外卖父工程启动测试类
 */
@Slf4j
@SpringBootApplication
public class SuperMonkeyTakeOutApplication {
    public static void main(String[] args) {
        SpringApplication.run(SuperMonkeyTakeOutApplication.class,args);
        log.info("项目启动");
    }
}
