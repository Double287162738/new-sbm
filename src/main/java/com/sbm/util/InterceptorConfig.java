package com.sbm.util;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Bean
    public HandlerInterceptor getUrlInterceptor(){
        return new UrlInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getUrlInterceptor()).addPathPatterns("/**")
                .excludePathPatterns(
                        "/personCenter/getUserDetail.do",
                        "/login/needLogin.do",
                        "/login/userLogin.do",
                        "/login/registered.do",
                        "/login/forgetPassword.do",
                        "/login/sendPhoneCode.do",
                        "/login/userLoginOut.do",
                        "/sousou/sougoods.do",
                        "/sousou/sougoodsDetail.do",
                        "/sousou/sougoodsHover.do",
                        "/sousou/souMainGoods.do",
                        "/suggestion/addSuggestion.do",
                        "/error",
                        "/html/login.html",
                        "/html/main.html",
                        "/html/home.html",
                        "/html/suggestion.html",
                        "/img/**",
                        "/js/**",
                        "/css/**",
                        "/layui/**",
                        "/newFileInput/**",
                        "/sliderImg/**",
                        "/sliderJs/**",
                        "/fileInputCss/**",
                        "/fileInputJs/**",
                        "/fonts/**",
                        "/newFileInput/**",
                        "/goodsPic/**",
                        "/avatarPic/**"
                );
        WebMvcConfigurer.super.addInterceptors(registry);
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/cola/");
//        WebMvcConfigurer.super.addResourceHandlers(registry);
//    }
}