package com.dapeng.page.utils;

import com.dapeng.page.entity.ExciptionEmu;
import com.dapeng.page.entity.ResultBody;
import com.dapeng.page.exceptionHandler.PageException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
  private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  /**
   * 处理自定义的业务异常
   *
   * @param req
   * @param e
   * @return
   */
  @ExceptionHandler(PageException.class)
  public ResultBody bizExceptionHandler(HttpServletRequest req, PageException e) {
    logger.error("发生业务异常！原因是：{}", e.getExptMsg());//控制台日志输出
    return ResultBody.error(e.getExptCode(), e.getExptMsg());//返回错误信息
  }

  /**
   * 处理空指针的异常
   *
   * @param req
   * @param e
   * @return
   */
  @ExceptionHandler(value = NullPointerException.class)
  @ResponseBody
  public ResultBody exceptionHandler(HttpServletRequest req, NullPointerException e) {
    logger.error("发生空指针异常！原因是:", e);
    return ResultBody.error(ExciptionEmu.BODY_NOT_MATCH);
  }


  /**
   * 处理其他异常
   *
   * @param req
   * @param e
   * @return
   */
  @ExceptionHandler(value = Exception.class)
  @ResponseBody
  public ResultBody exceptionHandler(HttpServletRequest req, Exception e) {
    logger.error("未知异常！原因是:", e);
    return ResultBody.error(ExciptionEmu.INTERNAL_SERVER_ERROR);
  }

}
