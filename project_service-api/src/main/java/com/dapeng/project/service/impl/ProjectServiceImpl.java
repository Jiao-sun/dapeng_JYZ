package com.dapeng.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dapeng.project.entity.Projects;
import com.dapeng.project.mapper.ProjectSMapper;
import com.dapeng.project.service.ProjectsService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectsService {

  @Resource
  private ProjectSMapper projectSMapper;

  public int add(Projects project) {
    QueryWrapper<Projects> wrapper = new QueryWrapper();


    return projectSMapper.insert(project);
  }

  public int delete(Integer pid) {
    QueryWrapper<Projects> wrapper = new QueryWrapper();
    wrapper.eq(pid != null && pid != 0, "pId", pid);
    return projectSMapper.delete(wrapper);
  }

  public int update(Projects projects) {
    return projectSMapper.updateById(projects);
  }

  public List<Projects> get(Projects projects) {
    QueryWrapper<Projects> wrapper = new QueryWrapper();
    wrapper.eq(projects.getPId() != null && !"".equals(projects.getPId()), "pId", projects.getPId())
        .like(projects.getPojName() != null && !"".equals(projects.getPojName()), "pojName",
            projects.getPojName())
        .like(projects.getCategory() != null && !"".equals(projects.getCategory()), "category",
            projects.getCategory())
        .like(projects.getCreateBy() != null && !"".equals(projects.getCreateBy()), "createBy",
            projects.getCreateBy())
        .eq(projects.getFinishDate() != null && !"".equals(projects.getFinishDate()), "finishDate",
            projects.getFinishDate())
        .eq(projects.getStartDate() != null && !"".equals(projects.getStartDate()), "startDate",
            projects.getStartDate());
    return projectSMapper.selectList(wrapper);
  }

  @Override
  public Projects findProjectByID(Integer pId) {
    Projects project = null;
    QueryWrapper<Projects> wrapper = new QueryWrapper();
    wrapper.eq(pId != null && pId != 0, "pId", pId);
    project = projectSMapper.selectOne(wrapper);
    return project;
  }
}
