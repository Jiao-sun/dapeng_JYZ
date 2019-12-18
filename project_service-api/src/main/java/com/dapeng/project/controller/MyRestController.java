package com.dapeng.project.controller;

import com.dapeng.project.entity.Projects;
import com.dapeng.project.service.ProjectsService;
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
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/project")
public class MyRestController {

  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  @Autowired
  ProjectsService projectsService;

  @GetMapping("/get")
  public ResponseEntity<List<Projects>> get( Projects projects) {
    return ResponseEntity.ok(projectsService.get(projects));
  }

  @PostMapping("/add")
  public ResponseEntity<Integer> add(@RequestBody Projects projects) {
    return ResponseEntity.ok(projectsService.add(  projects));
  }

  @PutMapping("/update")
  public ResponseEntity<Integer> update(Projects projects) {
    return ResponseEntity.ok(projectsService.update(projects));
  }

  @DeleteMapping("/delete/{pid}")
  public ResponseEntity<Integer> update(@PathVariable("pid") Integer pid) {
    return ResponseEntity.ok(projectsService.delete(pid));
  }
}
