package com.taihe.databasedemo.config;

import com.taihe.databasedemo.component.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
    @Bean
    public LoginInterceptor getLoginInterceptor() {
        return new LoginInterceptor();
    }

    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(getLoginInterceptor()).excludePathPatterns(
                        "/studentloginAction",
                        "/studentlogin",
                        "/css/*",
                        "/js/*",
                        "/img/*"
                );

    }
}
