package com.dapeng.user.service;

import com.dapeng.user.entity.User;
import java.util.List;

public interface Userservice {

  /**
   * @param user
   * @return 1 or 0
   * @description:
   * @author: jiaoyingzhong
   * @date: 2019/12/12
   */
  int addUser(User user);

  /**
   * @param userNo
   * @return
   * @description:
   * @author: jiaoyingzhong
   * @date: 2019/12/12
   */
  int deleteUser(String userNo);

  int updateUser(User user);

  User getUser(String userNo);

  List<User> getUserByName(String userName);

  List<User> getUserByTel( String tel);

}
