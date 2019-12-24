package com.dapeng.page.controller;

import com.dapeng.page.client.UserClient;
import com.dapeng.page.entity.User;
import com.dapeng.page.utils.Md5Util;
import com.dapeng.page.utils.RedisUtil;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDataController {

  @Autowired
  UserClient userClient;
  @Autowired
  RedisUtil redisUtil;


  @GetMapping("user/get")
  public ResponseEntity GetByNo(@RequestParam("userName") String userName) {
    return userClient.GetByName(userName);

  }

  @PostMapping("/user/login")
  public String login(String userNo, String password, HttpSession session, Model model) {
    User user = userClient.GetByNo(userNo).getBody();
    if (user == null) {
      return "userNoError";
    }
    String md5PWD;
    try {
      md5PWD = Md5Util.md5(password);
    } catch (Exception e) {
      return "Error";
    }

    if (!user.getPassword().equals(md5PWD)) {
      return "passwordError";
    } else {
      session.setAttribute("user", user);
      redisUtil.setLoger(session.getId(), user);
      return "ok";
    }

  }

  @PostMapping("/user/add")
  public Map add(User user) {
    Map resultMap = new HashMap();
    boolean resultStatus = false;
    try {
      user.setPassword(Md5Util.md5(user.getPassword()));
      Long ftime = new Date().getTime();
      StringBuffer buffer = new StringBuffer("DP_");
      boolean flag = true;
      do {
        Long time = new Date().getTime();
        if (time - ftime <= 30000) {
          String timestr = time.toString();
          for (; timestr.length() > 1; ) {
            char c = (char) Integer.parseInt(timestr.substring(0, 2));
            if (c > '0' && c < '9' || c > 'a' && c < 'z' || c > 'A' && c < 'Z') {
              buffer.append(c);
            } else {

            }
            timestr = timestr.substring(2);
          }
          buffer.append(timestr);
          if (userClient.GetByNo(buffer.toString()).getBody() == null) {
            flag = false;
            user.setUserNo(buffer.toString());
          }
        } else {
          throw new Exception();
        }
      } while (flag);
      userClient.add(user);
      resultStatus = true;

    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } finally {
      resultMap.put("status", resultStatus);
      resultMap.put("userNo", user.getUserNo());
      return resultMap;
    }
  }

  @PutMapping("/user/updata")
  public ResponseEntity updataUserInfo(User user) {
    return userClient.update(user);
  }

  @GetMapping("/user/validata")
  public ResponseEntity validata(String userNo, String PWD, HttpSession session)
      throws NoSuchAlgorithmException {
    User user = (User) session.getAttribute("user");
    boolean flag = user.getPassword().equals(Md5Util.md5(PWD));
    return ResponseEntity.ok(flag);
  }

  @PostMapping("/user/logout")
  public boolean logout(HttpSession session) {
    redisUtil.delete(session.getId());
    return true;
  }


}

