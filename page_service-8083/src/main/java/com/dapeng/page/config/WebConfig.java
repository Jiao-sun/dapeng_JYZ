package com.dapeng.page.config;

import com.dapeng.page.utils.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {

  @Autowired
  LoginInterceptor loginInterceptor;

  final String[] notLoginInterceptPaths = {"/static/**", "/admin/login", "/error/**", "/login"};

  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
        .excludePathPatterns(notLoginInterceptPaths);

  }


  /***
   * addResourceLocations指的是文件放置的目录，addResoureHandler指的是对外暴露的访问路径
   */
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //排除静态资源拦截
    registry.addResourceHandler("classpath:/static/**").addResourceLocations("classpath:/static/");
  }

  /**
   * 配置不需要经过controller就跳转到登录页面
   */
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/login").setViewName("login");
  }

  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }
}
