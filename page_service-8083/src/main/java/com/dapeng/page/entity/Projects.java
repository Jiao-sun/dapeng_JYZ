package com.dapeng.page.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class Projects {
  private Integer pId;
  private String pojName;
  private String category;
  private String createBy;

   @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")//传出输出值
    @DateTimeFormat(pattern ="yyyy-MM-dd")//传入构建类格式化
  private Date startDate;

   @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")//传出输出值
    @DateTimeFormat(pattern ="yyyy-MM-dd")
  private Date finishDate;
}
