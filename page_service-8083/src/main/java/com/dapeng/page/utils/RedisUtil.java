package com.dapeng.page.utils;


import com.alibaba.fastjson.JSON;
import com.dapeng.page.entity.User;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * redis操作工具类.</br> (基于RedisTemplate)
 *
 * @author xcbeyond 2018年7月19日下午2:56:24
 */
@Component
public class RedisUtil {

  @Autowired
  private RedisTemplate<String, String> redisTemplate;


  /*设置登录*/
  public boolean setLoger(String sessionId, User user) {
    boolean reault = false;
    String jsonStr = JSON.toJSONString(user);
    redisTemplate.opsForValue().set(sessionId, jsonStr, 30, TimeUnit.MINUTES);
    reault = true;
    return reault;
  }

  /*获取登录*/
  public User getLoger(String sessionId) {
    User user = null;
    String maprStr = redisTemplate.opsForValue().get(sessionId);
    user = JSON.parseObject(maprStr, User.class);
    return user;
  }

  /*判断登录*/
  public boolean hasUser(String key) {
    return redisTemplate.hasKey(key);
  }

  public void flushLoger(String sessionId) {
    redisTemplate.expire(sessionId, 30, TimeUnit.MINUTES);
  }

  /**
   * 删除缓存
   */
  public boolean delete(final String key) {
    boolean result = false;
    result = redisTemplate.delete(key);
    return result;
  }
}
