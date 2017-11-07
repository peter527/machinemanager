package com.ketech.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ketech.mapper.ProjectMapper;
import com.ketech.mapper.ProjectStaffMapper;
import com.ketech.po.Project;
import com.ketech.tdo.MessageResultBean;
import com.ketech.vo.ProjectStaffBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @package: com.ketech.service <br/>
 * @class: ProjectService <br/>
 * @version: version 1.0 <br/>
 * @author: WuGuoHua <br/>
 * @date: 2017年11月06日 <br/>
 * @description: 项目Service <br/>.
 */

@Service
public class ProjectService {

    @Resource
    private ProjectMapper projectMapper;
    @Resource
    private ProjectStaffMapper projectStaffMapper;

    public static final int SUCCESS_CODE = 200;
    public static final int FAIL_CODE = 500;

    /**
     * 分页查询设备信息
     * @param start 开始的条数
     * @param length 查询的条数
     * @param name 设备名称参数
     * @return 返回带分页信息的查询结果
     */
    public Map<String, Object> listProject(int start, int length, String name){
        Project project = new Project();
        if (StringUtils.isNotBlank(name)){
            project.setProjectName(name);
        }
        PageHelper.offsetPage(start, length);
        List<Project> projectList = projectMapper.select(project);
        PageInfo pageInfo = new PageInfo(projectList);
        Map<String, Object> maps = new HashMap<String, Object>();
        // 总记录数
        maps.put("recordsTotal", pageInfo.getTotal());
        // 过滤后的总记录数
        maps.put("recordsFiltered", pageInfo.getTotal());
        // 分页列表
        maps.put("data", projectList);
        return maps;
    }


    /**
     * 分页查询设备以及厂商信息
     * @param start 开始的条数
     * @param length 查询的条数
     * @param name 设备名称参数
     * @return 返回带分页信息的查询结果
     */
    public Map<String, Object> listProjectStaffInfo(int start, int length, String name){
        Project project = new Project();
        if (StringUtils.isNotBlank(name)){
            project.setProjectName(name);
        }
        PageHelper.offsetPage(start, length);
        List<ProjectStaffBean> projectStaffList = projectStaffMapper.selectProjectAndStaffByProject(project);
        PageInfo pageInfo = new PageInfo(projectStaffList);
        Map<String, Object> maps = new HashMap<String, Object>();
        // 总记录数
        maps.put("recordsTotal", pageInfo.getTotal());
        // 过滤后的总记录数
        maps.put("recordsFiltered", pageInfo.getTotal());
        // 分页列表
        maps.put("data", projectStaffList);
        return maps;
    }

    /**
     * 根据参数搜索设备信息
     * @param project 设备实体类
     * @return 返回查询结果集合
     */
    private List<Project> searchProject(Project project){
        List<Project> projectList = projectMapper.select(project);
        return projectList;
    }

    /**
     *
     * @param projectId
     * @param projectName
     * @param appName
     * @param appLatestVersion
     * @param staffId
     * @param operationType
     * @return
     */
    public MessageResultBean saveProject(String projectId, String projectName, String appName, String appLatestVersion, String staffId, String operationType){
        MessageResultBean messageResult = new MessageResultBean();
        int code = FAIL_CODE;
        String resultMessage = "";
        Project project = new Project();
        project.setProjectId(projectId);
        project.setProjectName(projectName);
        project.setAppName(appName);
        project.setAppLatestVersion(appLatestVersion);
        project.setStaffId(staffId);
        if ("0".equals(operationType)){
            project.setStatus("0");
            project.setCreateDate(new Date());
            String checkMessage = checkIdAndName(projectId, projectName);
            if (StringUtils.isNotBlank(checkMessage)){
                resultMessage = checkMessage;
            } else {
                if (projectMapper.insert(project) > 0){
                    code = SUCCESS_CODE;
                    resultMessage = "新增设备成功";
                }
            }
        } else {
            project.setModifyDate(new Date());
            if (projectMapper.updateByPrimaryKeySelective(project) > 0){
                code = SUCCESS_CODE;
                resultMessage = "修改设备成功";
            }
        }
        messageResult.setCode(code);
        messageResult.setMsg(resultMessage);
        return messageResult;
    }

    /**
     * 检测设备编号跟与设备名称是否存在
     * @param id 待检测设备编号
     * @param name 待检测设备名称
     * @return 返回检测信息：不存在返回空字符串，否则返回对应的信息
     */
    private String checkIdAndName(String id, String name){
        String result = "";
        Project idProject = new Project();
        idProject.setProjectId(id);
        if (searchProject(idProject).size() > 0){
            result = "项目编号已存在";
        }
        Project nameProject = new Project();
        nameProject.setProjectName(name);
        if(searchProject(nameProject).size() > 0){
            result = "项目名称已存在";
        }
        return result;
    }

}
