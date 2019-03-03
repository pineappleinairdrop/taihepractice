package com.taihe.databasedemo.config;

import com.taihe.databasedemo.component.AdminInterceptor;
import com.taihe.databasedemo.component.StudentInterceptor;
import com.taihe.databasedemo.component.TeacherInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
    @Bean
    public StudentInterceptor getStudentInterceptor() {
        return new StudentInterceptor();
    }

    @Bean
    public TeacherInterceptor getTeacherInterceptor() {
        return new TeacherInterceptor();
    }

    @Bean
    public AdminInterceptor getAdminInterceptor(){
        return new AdminInterceptor();
    }
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(getStudentInterceptor())./*excludePathPatterns(
                "/studentloginAction",
                "/studentlogin",
                "/adminlogin",
                "/adminloginAction",
                "/teacherlogin",
                "teacherloginAction",
                "/css/*",
                "/js/*",
                "/img/*",
                "/"
        ).*/addPathPatterns(
                "/home",
                "/deleteTaken/*",
                "/doModifyPassword",
                "/courseList",
                "/stulogout",
                "/selectCourse",
                "/selectCoursePost"

        );
        registry.addInterceptor(getTeacherInterceptor()).addPathPatterns(
                "/courseList4Teacher",
                "/deleteTeach",
                "/doModifyTeacherPassword",
                "/tealogout",
                "/teacher",
                "/selectCourse4Teacher",
                "/selectCoursePost4Teacher"

        );
        registry.addInterceptor(getAdminInterceptor()).addPathPatterns(
                "/admin",
                "/addCourse",
                "/addStudent",
                "/addTeacher",
                "/courseList4Admin",
                "/deleteCourse",
                "/doModifyCourse",
                "/refreshStudentList",
                "/refreshTeacherList",
                "/adminlogout",
                "courseCount"
        );
    }
}
