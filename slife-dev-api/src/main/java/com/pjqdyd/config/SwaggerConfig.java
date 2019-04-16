package com.pjqdyd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**   
 * @Description:  [Swagger2 UI的配置类]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@Configuration
@Profile({"dev","test"})
public class SwaggerConfig {

    //配置swagger2, 配置基本的内容比如扫描的包等
    @Bean
    public Docket createRestApi(){

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.pjqdyd.controller"))
                .paths(PathSelectors.any()).build();
    }

    //构建api文档的描述信息
    private ApiInfo apiInfo(){

        return new ApiInfoBuilder()
                .title("SLife APP BackEnd RESTful APIs")
                .contact(new Contact("pjqdyd","http://www.pjqdyd.com","1909025079@qq.com"))
                .description("SLife 生活街APP-后端接口文档")
                .version("1.0.0").build();
    }

}
