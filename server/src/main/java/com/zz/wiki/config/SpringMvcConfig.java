//package com.zz.wiki.config;
//
//
//import com.zz.wiki.interceptor.LogInterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import javax.annotation.Resource;
//
///**
// * @author zhou
// */
//@Configuration
//public class SpringMvcConfig implements WebMvcConfigurer {
//
//
//    @Resource
//    LogInterceptor logInterceptor;
//
//    public void addInterceptors(InterceptorRegistry registry){
//        registry.addInterceptor(logInterceptor)
//                .addPathPatterns("/**").excludePathPatterns("/login");
//    }
//
////    @Resource
////    LoginInterceptor loginInterceptor;
////
////    @Resource
////    ActionInterceptor actionInterceptor;
////
////    public void addInterceptors(InterceptorRegistry registry) {
////        registry.addInterceptor(loginInterceptor)
////                .addPathPatterns("/**")
////                .excludePathPatterns(
////                        "/test/**",
////                        "/redis/**",
////                        "/user/login",
////                        "/category/all",
////                        "/ebook/list",
////                        "/doc/all/**",
////                        "/doc/vote/**",
////                        "/doc/find-content/**",
////                        "/ebook-snapshot/**"
////                );
////
////        registry.addInterceptor(actionInterceptor)
////                .addPathPatterns(
////                        "/*/save",
////                        "/*/delete/**",
////                        "/*/reset-password");
////    }
//}
