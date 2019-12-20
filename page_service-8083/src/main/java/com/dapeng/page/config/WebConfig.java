package com.dapeng.page.config;

import com.dapeng.page.utils.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Autowired
  LoginInterceptor loginInterceptor;

  final String[] notLoginInterceptPaths = {"/static/**", "/user/login", "/error/**", "js/", "/login"};

  //注册扩展拦截器
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
        .excludePathPatterns(notLoginInterceptPaths);

  }


  /***
   * addResourceLocations指的是文件放置的目录，addResoureHandler指的是对外暴露的访问路径
   */
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //排除静态资源拦截
    registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

  }

  /**
   * 配置不需要经过controller就跳转到登录页面
   */
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/login").setViewName("login");
    registry.addViewController("/main").setViewName("index");
  }

  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }
}
