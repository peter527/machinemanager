package com.ketech.controller;

import com.ketech.service.StaffService;
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
 * @class: StaffController <br/>
 * @version: version 1.0 <br/>
 * @author: WuGuoHua <br/>
 * @date: 2017年10月31日 <br/>
 * @description: 员工Controller <br/>.
 */

@Controller
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @RequestMapping
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("staff/index");
        return modelAndView;
    }

    @RequestMapping(value = "/listStaff")
    @ResponseBody
    public Map<String, Object> listStaff(@RequestParam(required = false, defaultValue = "0") int start,
                                         @RequestParam(required = false, defaultValue = "10") int length, String name) {
        return staffService.listStaff(start, length, name);
    }

    @RequestMapping(value = "/saveStaff")
    @ResponseBody
    public MessageResultBean saveStaff(String staffId, String staffName, String staffPhone, String operationType) {
        return staffService.saveStaff(staffId, staffName, staffPhone, operationType);
    }


}
