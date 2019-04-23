package com.pjqdyd.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**   
 * @Description:  [Mvc层的相关配置]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Value("${common.slife.fileSpace}")
    private String fileSpace;

    /**
     * 配置静态资源的路径
     * (springboot的web资源的默认访问路径是classpath/或resources/下的static|template)
     * (这里我们要配置,图片等文件可以在项目路径下访问)
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //  "/**"表示项目的所有路径下可访问
        //  表示把本地的file:盘符:/..路径映射到项目的路径下可访问
        registry.addResourceHandler("/**")
                .addResourceLocations("file:C:/Users/Z2/Desktop/slife-file/")
                .addResourceLocations("classpath:/META-INF/resources/");

        //假如swagger-ui的html界面提示mapping映射找不到
        //是由于swagger生成的html文件是放在项目的classpath:/META-INF/resources下
        //重写了配置后就可能访问不到了
        //解决办法是继续加上 .addResourceLocations("classpath:/META-INF/resources/");

    }

}
