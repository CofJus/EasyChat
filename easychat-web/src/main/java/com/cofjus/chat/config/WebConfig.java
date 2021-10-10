package com.cofjus.chat.config;

import com.cofjus.chat.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author Rui
 * @Date 2021/10/10 11:13
 * @Version 1.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // TODO 拦截器不生效
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**");
    }
}
