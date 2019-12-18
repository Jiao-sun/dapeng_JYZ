package com.dapeng.page.entity;

import com.dapeng.page.exceptionHandler.ExceptionInterface;

public enum ExciptionEmu implements ExceptionInterface {

  // 数据操作错误定义
  SUCCESS("200", "成功!"),
  BODY_NOT_MATCH("400", "请求的数据格式不符!"),
  SIGNATURE_NOT_MATCH("401", "请求的数字签名不匹配!"),
  NOT_FOUND("404", "未找到该资源!"),
  LOGIN_TIMEOUT("410", "登录超时"),
  BAD_PASSWORD("411", "密码错误"),
  USER_NOT_FIND("412", "用户未找到"),
  INTERNAL_SERVER_ERROR("500", "服务器内部错误!"),
  SERVER_BUSY("503", "服务器正忙，请稍后再试!");
  /**
   * 错误码
   */
  private String resultCode;
  /**
   * 错误描述
   */
  private String resultMsg;

  ExciptionEmu(String resultCode, String resultMsg) {
    this.resultCode = resultCode;
    this.resultMsg = resultMsg;
  }

  public String getExceptionCode() {
    return  this.resultCode;
  }

  public String getExceptionMsg() {
    return this.resultMsg;
  }
}
