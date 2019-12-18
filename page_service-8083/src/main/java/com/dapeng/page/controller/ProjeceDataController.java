package com.dapeng.page.controller;

import com.dapeng.page.client.ProjectClient;
import com.dapeng.page.entity.Projects;
import java.util.List;
import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project")
public class ProjeceDataController {

  @Resource
  ProjectClient projectClient;

  @GetMapping("/get")
  public ResponseEntity<List<Projects>> get(Projects projects) {
    return projectClient.get(projects);
  }

  @PostMapping("/add")
  public ResponseEntity<Integer> add( Projects projects) {
    return projectClient.add(projects);
  }

  @DeleteMapping("/delete/{pid}")
  public ResponseEntity<Integer> get(@PathParam("pid") Integer pid) {
    return projectClient.delete(pid);
  }

  @PutMapping("/update")
  public ResponseEntity<Integer> update(Projects projects) {
    return projectClient.update(projects);
  }

}
