package com.dapeng.user.controller;

import com.dapeng.user.entity.User;
import com.dapeng.user.service.Userservice;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  Userservice userservice;

  @PostMapping("/add")
  public ResponseEntity<Integer> add(@RequestBody User user) {
    return ResponseEntity.ok(userservice.addUser(user));
  }

  @DeleteMapping("/delete/{userNo}")
  public ResponseEntity<Integer> delete(@PathVariable("userNo") String userNo) {
    return ResponseEntity.ok(userservice.deleteUser(userNo));
  }

  @PutMapping("/update")
  public ResponseEntity<Integer> update(User user) {
    return ResponseEntity.ok(userservice.updateUser(user));
  }

  @GetMapping("/get/userNo")
  public ResponseEntity<User> GetByNo(@RequestParam("userNo") String uerNo) {
    return ResponseEntity.ok(userservice.getUser(uerNo));
  }

  @GetMapping("/get/userName")
  public  ResponseEntity<List<User>> GetByName(@RequestParam("userName") String userName){
    return  ResponseEntity.ok(userservice.getUserByName(userName));
  }

  @GetMapping("/get/tel")
  public  ResponseEntity<List<User>> GetByTEL(@RequestParam("tel") String tel){
    return  ResponseEntity.ok(userservice.getUserByTel(tel));
  }
}
