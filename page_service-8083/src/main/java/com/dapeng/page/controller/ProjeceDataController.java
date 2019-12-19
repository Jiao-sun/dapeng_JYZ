package com.dapeng.page.controller;

import com.dapeng.page.client.ProjectClient;
import com.dapeng.page.entity.Projects;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class ProjeceDataController {

  @Resource
  ProjectClient projectClient;

  @GetMapping("/get")
  public ResponseEntity<List<Projects>> get(Projects projects) {
    return projectClient.get(projects);
  }

  @PostMapping("/add")
  public ResponseEntity<Integer> add(Projects projects) {
    return projectClient.add(projects);
  }

  @DeleteMapping("/delete/{pid}")
  public ResponseEntity<Integer> delete(@PathVariable("pid") Integer pid) {
    return projectClient.delete(pid);
  }

 @RequestMapping("/update")
  public ResponseEntity<Integer> update(Projects projects) {
    Projects p = projectClient.getProject(projects.getPId());
    if (projects.toString().equals(p.toString())) {
      return ResponseEntity.ok(0);
    }
    return projectClient.update(projects);

  }

}
