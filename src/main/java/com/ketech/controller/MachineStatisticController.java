package com.ketech.controller;

import com.ketech.po.LocationMachine;
import com.ketech.po.StaffMachine;
import com.ketech.service.MachineStaticticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @package: com.ketech.controller <br/>
 * @class: MachineStatisticController <br/>
 * @version: version 1.0 <br/>
 * @author: WuGuoHua <br/>
 * @date: 2017年11月11日 <br/>
 * @description: 设备统计Controller <br/>.
 */

@Controller
@RequestMapping("/machineStatistic")
public class MachineStatisticController {

    @Autowired
    private MachineStaticticService machineStaticticService;

    @RequestMapping("/listStaffMachine")
    @ResponseBody
    public Map<String, Object> listStaffMachine(@RequestParam(required = false, defaultValue = "0") int start,
                                                @RequestParam(required = false, defaultValue = "10") int length, StaffMachine staffMachine) {
        return machineStaticticService.listStaffMachine(start, length, staffMachine);
    }

    @RequestMapping("/listLocationMachine")
    @ResponseBody
    public Map<String, Object> listLocationMachine(@RequestParam(required = false, defaultValue = "0") int start,
                                                @RequestParam(required = false, defaultValue = "10") int length, LocationMachine locationMachine) {
        return machineStaticticService.listLocationMachine(start, length, locationMachine);
    }

}
