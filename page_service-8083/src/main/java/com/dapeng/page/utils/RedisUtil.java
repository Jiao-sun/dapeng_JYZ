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

  public boolean setLoger(String sessionId, User user) {
    boolean reault = false;
    String jsonStr = JSON.toJSONString(user);
    try {
      redisTemplate.opsForValue().set(sessionId, jsonStr, 30, TimeUnit.MINUTES);
      reault = true;
    } catch (Exception e) {
    }
    return reault;
  }

  public User getLoger(String sessionId) {
    User user = null;
    try {
      String maprStr = redisTemplate.opsForValue().get(sessionId);
      user = JSON.parseObject(maprStr, User.class);
    } catch (Exception e) {

    }
    return user;
  }

  public boolean hasUser(String key){
    return  redisTemplate.hasKey(key);
  }



  /**
   * 读取缓存
   *
   * @param key
   * @return
   */
  public String get(final String key) {
    return redisTemplate.opsForValue().get(key);
  }

  public boolean setObj(final String key, Object obj) {
    boolean result = false;
    String jsonString = JSON.toJSONString(obj);
    result = set(key, jsonString);
    return result;
  }

  /**
   * 写入缓存
   */
  public boolean set(final String key, String value) {
    boolean result = false;
    try {
      redisTemplate.opsForValue().set(key, value, 30, TimeUnit.MINUTES);
      result = true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  /**
   * 更新缓存
   */
  public boolean getAndSet(final String key, String value) {
    boolean result = false;
    try {
      redisTemplate.opsForValue().getAndSet(key, value);
      result = true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  /**
   * 删除缓存
   */
  public boolean delete(final String key) {
    boolean result = false;
    try {
      redisTemplate.delete(key);
      result = true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

}
