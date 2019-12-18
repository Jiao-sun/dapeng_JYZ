package com.dapeng.page.controller;

import com.dapeng.page.client.UserClient;
import com.dapeng.page.utils.RedisUtil;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

  /*@RequestMapping("/**.html")
  public String  filterAll(HttpServletRequest request) {
    HttpSession session = request.getSession();
    String sessionId=session.getId();
    User user=redisUtil.getLoger(sessionId);
    if(user==null){
      return "login";
    }
    return request.getServletPath();
    
  }

*/
  @GetMapping("/")
  public String index(HttpSession session,Model model) {
    if (redisUtil.hasUser(session.getId())) {
      model.addAttribute("user",redisUtil.getLoger(session.getId()));
      return "/index";
    } else {
      return "/login";
    }
  }

  @GetMapping("/index.html")
  public String aindex() {

    return "index";
  }

  /**
   * @return
   * @description:
   * @author: jiaoyingzhong
   * @date: 2019/12/13
   */







}
