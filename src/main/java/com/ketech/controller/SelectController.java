package com.ketech.controller;

import com.ketech.service.SelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @package: com.ketech.controller <br/>
 * @class: SelectController <br/>
 * @version: version 1.0 <br/>
 * @author: WuGuoHua <br/>
 * @date: 2017年11月09日 <br/>
 * @description: 用于填充Select的Controller <br/>.
 */

@Controller
@RequestMapping("/select")
public class SelectController {

    @Autowired
    private SelectService selectService;

    @RequestMapping("/listLocationAndMachine")
    @ResponseBody
    public Map<String, Object> listLocation(@RequestParam(required = false, value = "idList[]") List<String> idList){
        return selectService.listLocation(idList);
    }

    @RequestMapping("/listLocationAndMachineAndStaff")
    @ResponseBody
    public Map<String, Object> listLocationAndMachineAndStaff(@RequestParam(required = false, value = "idList[]") List<String> idList){
        return selectService.listLocationAndMachineAndStaff(idList);
    }

}
