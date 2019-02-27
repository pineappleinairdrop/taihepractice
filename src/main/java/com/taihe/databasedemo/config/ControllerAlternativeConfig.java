package com.taihe.databasedemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ControllerAlternativeConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/studentlogin").setViewName("studentlogin");
        registry.addViewController("/studentlogin.html").setViewName("studentlogin");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/home.html").setViewName("home");
        registry.addViewController("/studentList").setViewName("studentList");
    }
}
