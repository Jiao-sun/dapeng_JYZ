package com.dapeng.project.service;

import com.dapeng.project.entity.Projects;
import java.util.List;

public interface ProjectsService {

  /**
   * 添加项目
   *
   * @param project
   * @return
   * @description:
   * @author: jiaoyingzhong
   * @date: 2019/12/10
   */
  int add(Projects project);

  /**
   * 删除项目
   *
   * @return
   * @description:
   * @author: jiaoyingzhong
   * @date: 2019/12/10
   */
  int delete(Integer pid);

  /**
   *修改项目
   *
   * @param projects
   * @return
   * @description:
   * @author: jiaoyingzhong
   * @date: 2019/12/10
   */
  int update(Projects projects);


  List<Projects> get(Projects projects);

}
