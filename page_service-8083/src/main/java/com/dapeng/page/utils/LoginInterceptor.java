package com.dapeng.page.utils;

import com.dapeng.page.entity.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoginInterceptor implements HandlerInterceptor {

  Logger logger = LoggerFactory.getLogger(getClass());
  @Autowired
  RedisUtil redisUtil;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    String url = request.getRequestURI();
    User user = (User) request.getSession().getAttribute("user");
    String sessionId = request.getSession().getId();
    logger.info("访问:" + url);
    if (redisUtil.hasUser(sessionId)) {
      if (redisUtil.getLoger(sessionId).toString().equals(user.toString())) {
        logger.info("已登录  信息正确，放行======");
        redisUtil.flushLoger(sessionId);
        return true;
      } else {

        logger.error("已登录  但信息不符，放行======");
        return false;
      }

    } else {
      logger.error("未登录 重定向到登录------");
      response.sendRedirect("/login");
      return false;
    }


    /*logger.info("进入LoginInterceptor拦截器==============");
    String basePath = request.getContextPath();
    String path = request.getRequestURI();
    String sessionId = request.getSession().getId();
    User user = (User) request.getSession().getAttribute("user");
    if (user == null) {
      logger.info("游客访问了:" + path);
    } else {
      logger.info(user.getUserName() + "访问了:" + path);
    }

    if (redisUtil.hasUser(sessionId)) {
      redisUtil.flushLoger(sessionId);
      logger.info("已登录，放行！");
      return true;
    }
    logger.info("尚未登录，跳转到登录界面");
    response.setHeader("Content-Type", "text/html;charset=UTF-8");
    response.sendRedirect(request.getContextPath() + "/login");
    return false;
*/

  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {

  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) throws Exception {

  }
}
