package com.ketech.mapper;

import com.ketech.po.Project;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ProjectMapper extends Mapper<Project> {
}