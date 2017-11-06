package com.ketech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("project/index");
        return modelAndView;
    }
}
