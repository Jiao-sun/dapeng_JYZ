package com.dapeng.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Projects  {

  private Integer pId;
  @NonNull
  private String pojName;
  @NonNull
  private String category;
  @NonNull
  private String createBy;

/*  @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")//传出输出值
  @DateTimeFormat(pattern ="yyyy-MM-dd")//传入构建类格式化*/
  private String startDate;

/*  @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")//传出输出值
  @DateTimeFormat(pattern ="yyyy-MM-dd")*/
  private String finishDate;

}
