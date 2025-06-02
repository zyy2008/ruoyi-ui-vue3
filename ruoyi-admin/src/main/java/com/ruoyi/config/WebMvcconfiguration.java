package com.ruoyi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 静态资源访问
 */
@Configuration
public class WebMvcconfiguration implements WebMvcConfigurer {

    @Value("${file.savePath}")
    private String savePath;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/files/**") // 设置访问前缀
                .addResourceLocations("file:" + savePath); // 设置本地目录
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}
