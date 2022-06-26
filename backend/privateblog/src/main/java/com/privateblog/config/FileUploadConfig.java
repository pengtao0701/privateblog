package com.privateblog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileUploadConfig implements WebMvcConfigurer {
   

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 获取文件的真实路径
        String path = System.getProperty("user.dir")+"\\target\\classes\\static\\user\\";
        System.out.println(" System.getProperty(\"user.dir\")=" +  System.getProperty("user.dir"));
        //       /images/**是对应resource下工程目录
        registry.addResourceHandler("/user/**").addResourceLocations("file:"+path);
    }
}
