package com.dapeng.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dapeng.user.entity.User;
import com.dapeng.user.mapper.UserMapper;
import com.dapeng.user.service.Userservice;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements Userservice {

  @Autowired
  UserMapper userMapper;


  public int addUser(User user) {

    return userMapper.insert(user);
  }

  public int deleteUser(String userNo) {
    QueryWrapper<User> query = new QueryWrapper<User>();
    query.eq(userNo != null && !"".equals(userNo), "userNo", userNo);
    return userMapper.delete(query);
  }

  public int updateUser(User user) {
    return userMapper.updateById(user);
  }

  public User getUser(String userNo) {
    if (userNo == null && "".equals(userNo)) {
      return null;
    }
    QueryWrapper<User> query = new QueryWrapper<User>();
    query.eq( "userNo", userNo);
    User user = userMapper.selectOne(query);
    return user;
  }

  public List<User> getUserByName(String userName) {
    QueryWrapper<User> query = new QueryWrapper<User>();
    query.like(userName != null && !"".equals(userName), "userName", userName);
    return userMapper.selectList(query);
  }

  public List<User> getUserByTel(String tel) {
    QueryWrapper<User> query = new QueryWrapper<User>();
    query.like(tel != null && !"".equals(tel), "tel", tel);
    return userMapper.selectList(query);
  }
}
