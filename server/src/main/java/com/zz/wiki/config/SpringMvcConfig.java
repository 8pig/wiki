package com.zz.wiki.config;


import com.zz.wiki.interceptor.LogInterceptor;
import com.zz.wiki.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author zhou
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {



    @Resource
    LoginInterceptor loginInterceptor;


//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns(
//                        "/test/**",
//                        "/user/login",
//
//                        "/doc/all/**",
//                        "/doc/vote/**",
//                        "/doc/find-content/**",
//                        "/ebook-snapshot/**"
//                );
//
//    }
}
