package com.ketech.controller;

import com.ketech.service.ProjectService;
import com.ketech.tdo.MessageResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @package: com.ketech.controller <br/>
 * @class: ProjectController <br/>
 * @version: version 1.0 <br/>
 * @author: WuGuoHua <br/>
 * @date: 2017年11月02日 <br/>
 * @description: 项目类型Controller <br/>.
 */

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("project/index");
        return modelAndView;
    }

    @RequestMapping(value = "/listProject")
    @ResponseBody
    public Map<String, Object> listProjectStaffInfo(@RequestParam(required = false, defaultValue = "0") int start,
                                                      @RequestParam(required = false, defaultValue = "10") int length, String name) {
        return projectService.listProjectStaffInfo(start, length, name);
    }

    @RequestMapping(value = "/saveProject")
    @ResponseBody
    public MessageResultBean saveProject(String projectId, String projectName, String appName, String appLatestVersion, String staffId, String operationType) {
        return projectService.saveProject(projectId, projectName, appName, appLatestVersion, staffId, operationType);
    }
}
