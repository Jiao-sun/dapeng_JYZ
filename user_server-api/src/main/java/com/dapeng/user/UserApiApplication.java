package com.dapeng.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.dapeng.user.mapper")
  public class UserApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(UserApiApplication.class, args);
  }

}
