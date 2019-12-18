package com.dapeng.page.exceptionHandler;

import lombok.Data;

@Data
public class PageException extends RuntimeException {

  /**
   * 错误码
   */
  protected String exptCode;
  /**
   * 错误信息
   */
  protected String exptMsg;

  public PageException(){
    super();
  }

  public PageException(String message, String exptCode) {
    super(message);
    this.exptCode = exptCode;
  }

  public PageException(ExceptionInterface exceptionInterface) {
    super(exceptionInterface.getExceptionCode());
    this.exptCode = exceptionInterface.getExceptionCode();
    this.exptMsg = exceptionInterface.getExceptionMsg();
  }
}
