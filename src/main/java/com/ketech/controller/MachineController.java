package com.ketech.controller;

import com.ketech.service.MachineService;
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
 * @class: MachineController <br/>
 * @version: version 1.0 <br/>
 * @author: WuGuoHua <br/>
 * @date: 2017年10月31日 <br/>
 * @description: 设备管理Controller <br/>.
 */

@Controller
@RequestMapping("/machine")
public class MachineController {

    @Autowired
    private MachineService machineService;

    @RequestMapping
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("machine/index");
        return modelAndView;
    }

    @RequestMapping(value = "/listMachine")
    @ResponseBody
    public Map<String, Object> listMachineFactoryInfo(@RequestParam(required = false, defaultValue = "0") int start,
                                           @RequestParam(required = false, defaultValue = "10") int length, String name) {
        return machineService.listMachineFactoryInfo(start, length, name);
    }

    @RequestMapping(value = "/saveMachine")
    @ResponseBody
    public MessageResultBean saveMachine(String machineId, String machineName, String factoryId, String operationType) {
        return machineService.saveMachine(machineId, machineName, factoryId, operationType);
    }
}
