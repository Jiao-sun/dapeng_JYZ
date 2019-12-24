package com.dapeng.page.controller;

import com.dapeng.page.client.UserClient;
import com.dapeng.page.utils.RedisUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

  @Autowired
  RedisUtil redisUtil;
  @Autowired
  UserClient userClient;

  /**
   * @return
   * @description:
   * @author: jiaoyingzhong
   * @date: 2019/12/13
   * <p>
   * 访问首页
   */

  @GetMapping("user/info")
  public String ChangeInfo(HttpServletRequest request) {
    return "userInfo";

  }


  @GetMapping({"/","/index"})
  public String main(HttpSession session, Model model) {
    if (redisUtil.hasUser(session.getId())) {
      return "redirect:/main";
    } else {
      return "/login";
    }
  }

  @RequestMapping("/main")
  public String index() {

    return "index";
  }
}

