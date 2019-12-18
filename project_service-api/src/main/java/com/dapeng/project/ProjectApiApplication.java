package com.dapeng.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dapeng.project.mapper")
public class ProjectApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(ProjectApiApplication.class, args);
  }

}
