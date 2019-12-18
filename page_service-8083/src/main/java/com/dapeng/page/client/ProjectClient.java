package com.dapeng.page.client;


import com.dapeng.page.entity.Projects;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(value = "projectServer")
@Primary
public interface ProjectClient {

  @GetMapping("/project/get")
  ResponseEntity<List<Projects>> get(@SpringQueryMap Projects project);

  @PostMapping("/project/add")
  ResponseEntity<Integer> add(@RequestBody Projects projects);

  @PutMapping("/update")
  public ResponseEntity<Integer> update(@SpringQueryMap Projects projects);

  @DeleteMapping("/delete/{pid}")
  public ResponseEntity<Integer> delete(@PathVariable("pid") Integer pid) ;

}
