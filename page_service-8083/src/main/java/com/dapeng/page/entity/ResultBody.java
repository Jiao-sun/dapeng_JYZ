package com.dapeng.page.entity;

import com.alibaba.fastjson.JSONObject;
import com.dapeng.page.exceptionHandler.ExceptionInterface;
import lombok.Data;

@Data
public class ResultBody {

  /**
   * 响应代码
   */
  private String code;

  /**
   * 响应消息
   */
  private String message;

  /**
   * 响应结果
   */
  private Object result;

  public ResultBody() {
  }

  public ResultBody(ExceptionInterface errorInfo) {
    this.code = errorInfo.getExceptionCode();
    this.message = errorInfo.getExceptionMsg();
  }

  /**
   * 成功
   *
   * @return
   */
  public static ResultBody success() {
    return success(null);
  }

  /**
   * 成功
   *
   * @param data
   * @return
   */
  public static ResultBody success(Object data) {
    ResultBody rb = new ResultBody();
    rb.setCode(ExciptionEmu.SUCCESS.getExceptionCode());
    rb.setMessage(ExciptionEmu.SUCCESS.getExceptionMsg());
    rb.setResult(data);
    return rb;
  }

  /**
   * 失败
   */
  public static ResultBody error(ExceptionInterface errorInfo) {
    ResultBody rb = new ResultBody();
    rb.setCode(errorInfo.getExceptionCode());
    rb.setMessage(errorInfo.getExceptionMsg());
    rb.setResult(null);
    return rb;
  }

  /**
   * 失败
   */
  public static ResultBody error(String code, String message) {
    ResultBody rb = new ResultBody();
    rb.setCode(code);
    rb.setMessage(message);
    rb.setResult(null);
    return rb;
  }

  /**
   * 失败
   */
  public static ResultBody error(String message) {
    ResultBody rb = new ResultBody();
    rb.setCode("-1");
    rb.setMessage(message);
    rb.setResult(null);
    return rb;
  }

  @Override
  public String toString() {
    return JSONObject.toJSONString(this);
  }

}
