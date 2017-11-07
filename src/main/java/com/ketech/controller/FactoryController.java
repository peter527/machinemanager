package com.ketech.controller;

import com.ketech.po.Factory;
import com.ketech.service.FactoryService;
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
 * @class: FactoryController <br/>
 * @version: version 1.0 <br/>
 * @author: WuGuoHua <br/>
 * @date: 2017年11月06日 <br/>
 * @description: 厂商Controller <br/>.
 */

@Controller
@RequestMapping("/factory")
public class FactoryController {

    @Autowired
    private FactoryService factoryService;

    @RequestMapping
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("factory/index");
        return modelAndView;
    }

    @RequestMapping(value = "/listFactory")
    @ResponseBody
    public Map<String, Object> listFactory(@RequestParam(required = false, defaultValue = "0") int start,
                                         @RequestParam(required = false, defaultValue = "10") int length, String name) {
        return factoryService.listFactory(start, length, name);
    }

    @RequestMapping(value = "/listAllFactory")
    @ResponseBody
    public Map<String, Object> listAllFactory(Factory factory){
        return factoryService.listAllFactory(factory);
    }

    @RequestMapping(value = "/saveFactory")
    @ResponseBody
    public MessageResultBean saveFactory(String factoryId, String factoryName, String factoryCharger, String chargerPhone, String operationType) {
        return factoryService.saveFactory(factoryId, factoryName, factoryCharger, chargerPhone, operationType);
    }
}
