package com.ketech.controller;

import com.ketech.po.Location;
import com.ketech.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @package: com.ketech.controller <br/>
 * @class: LocationController <br/>
 * @version: version 1.0 <br/>
 * @author: WuGuoHua <br/>
 * @date: 2017年10月31日 <br/>
 * @description: 项目点Controller <br/>.
 */

@Controller
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @RequestMapping
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("location/index");
        return modelAndView;
    }

    @RequestMapping(value = "/listLocation")
    @ResponseBody
    public Map<String, Object> ListLocationInfo(@RequestParam(required = false, defaultValue = "0") int start,
                                                @RequestParam(required = false, defaultValue = "10") int length, Location location) {
        return locationService.ListLocationInfo(start, length, location);
    }

}
