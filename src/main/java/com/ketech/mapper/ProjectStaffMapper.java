package com.ketech.mapper;

import com.ketech.po.Project;
import com.ketech.vo.ProjectStaffBean;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface ProjectStaffMapper {

    List<ProjectStaffBean> selectProjectAndStaffByProject(Project project);

}