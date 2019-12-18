package com.dapeng.page;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class PageApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(PageApplication.class, args);
  }

}
