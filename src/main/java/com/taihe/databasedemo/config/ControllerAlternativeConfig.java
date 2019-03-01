package com.taihe.databasedemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ControllerAlternativeConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/studentlogin").setViewName("studentlogin");
        registry.addViewController("/teacherlogin").setViewName("teacherlogin");
        registry.addViewController("/adminlogin").setViewName("adminlogin");
        registry.addViewController("/studentlogin.html").setViewName("studentlogin");

        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/home.html").setViewName("home");

        registry.addViewController("/studentList").setViewName("studentList");
        registry.addViewController("/teacherList").setViewName("teacherList");

        registry.addViewController("/newCourse").setViewName("newCourse");
        registry.addViewController("/newStudent").setViewName("newStudent");
        registry.addViewController("/newTeacher").setViewName("newTeacher");

    }
}
