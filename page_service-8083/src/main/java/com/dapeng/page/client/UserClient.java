package com.dapeng.page.client;


import com.dapeng.page.entity.User;
import feign.QueryMap;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "localhost:8082",
    value = "userServer")
public interface UserClient {

  @PostMapping("/user/add")
  public ResponseEntity<Integer> add(@QueryMap User user);

  @DeleteMapping("/user/delete/{userNo}")
  public ResponseEntity<Integer> delete(@PathVariable("userNo") String userNo);

  @PutMapping("/user/update")
  public ResponseEntity<Integer> update(@RequestBody User user);


  @GetMapping("/user/get/userNo")
  public ResponseEntity<User> GetByNo(@RequestParam("userNo") String userNo);

  @GetMapping("/user/get/userName")
  public ResponseEntity<List<User>> GetByName(@RequestParam("userName") String userName);

  @GetMapping("/user/get/tel")
  public ResponseEntity<List<User>> GetByTEL(@RequestParam("tel") String tel);


}
