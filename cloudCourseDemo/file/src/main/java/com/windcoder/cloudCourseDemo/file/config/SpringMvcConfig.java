package com.windcoder.cloudCourseDemo.file.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 地址最后必须"/"结尾
        registry.addResourceHandler("/f/**").addResourceLocations("file:E:/00Work/Program/data/imooc/");
    }

}
