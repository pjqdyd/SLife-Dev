package com.pjqdyd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Springboot启动类
 */
@EnableSwagger2
@SpringBootApplication
public class SLifeApplication {

    public static void main(String[] args){
        SpringApplication.run(SLifeApplication.class, args);
    }

}
