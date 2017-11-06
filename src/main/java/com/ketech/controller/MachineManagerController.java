package com.ketech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @package: com.ketech.controller <br/>
 * @class: MachineManagerController <br/>
 * @version: version 1.0 <br/>
 * @author: WuGuoHua <br/>
 * @date: 2017年10月31日 <br/>
 * @description: 设备管理Controller <br/>.
 */

@Controller
@RequestMapping("/machine-manager")
public class MachineManagerController {

    @RequestMapping
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("machine-manager/index");
        return modelAndView;
    }

}
