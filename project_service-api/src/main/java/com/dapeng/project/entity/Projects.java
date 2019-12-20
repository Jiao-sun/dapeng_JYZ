package com.dapeng.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Projects {

  @TableId(value = "pId",type = IdType.INPUT)
  private Long pId;
  @NonNull
  private String pojName;
  @NonNull
  private String category;
  @NonNull
  private String createBy;

  /* @DateTimeFormat(pattern ="yyyy-MM-dd")//传入构建类格式化
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")//传出输出值*/
  private String startDate;

  /*
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")//传出输出值*/
  private String finishDate;


  public void setStartDate(String startDate) {
    if (startDate.split("-").length == 3) {
      this.startDate = startDate;
    } else if (startDate != null && !"".equals(startDate)) {
      Date date = new Date(startDate);
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      this.startDate = sdf.format(date);
    } else {
      this.startDate = null;
    }


  }

  public void setFinishDate(String finishDate) {
    if (finishDate.split("-").length == 3) {
      this.finishDate = finishDate;
    } else if (finishDate == null || "".equals(finishDate)) {
      this.finishDate = null;
    } else {
      Date date = new Date(finishDate);
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      this.finishDate = sdf.format(date);
    }

  }
}
