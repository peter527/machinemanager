package com.ketech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @package: com.ketech.controller <br/>
 * @class: MachineInfoController <br/>
 * @version: version 1.0 <br/>
 * @author: WuGuoHua <br/>
 * @date: 2017年10月31日 <br/>
 * @description: 设备分布Controller <br/>.
 */

@Controller
@RequestMapping("/machine-info")
public class MachineInfoController {

    @RequestMapping
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("machine-info/index");
        return modelAndView;
    }

}
