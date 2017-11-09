package com.ketech.controller;

import com.ketech.po.OperationLog;
import com.ketech.service.OperationLogService;
import com.ketech.tdo.MessageResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @package: com.ketech.controller <br/>
 * @class: OperationLogController <br/>
 * @version: version 1.0 <br/>
 * @author: WuGuoHua <br/>
 * @date: 2017年11月09日 <br/>
 * @description: 操作日志Controller <br/>.
 */

@Controller
@RequestMapping("/operationLog")
public class OperationLogController {

    @Autowired
    private OperationLogService operationLogService;

    @RequestMapping("/listOperationLog")
    @ResponseBody
    public Map<String, Object> listOperationLog(@RequestParam(required = false, defaultValue = "0") int start,
                                         @RequestParam(required = false, defaultValue = "10") int length, OperationLog operationLog) {
        return operationLogService.listOperationLog(start, length, operationLog);
    }

    @RequestMapping(value = "/saveOperationLog")
    @ResponseBody
    public MessageResultBean saveOpeartionLog(OperationLog operationLog, String saveType) {
        return operationLogService.saveOpeartionLog(operationLog, saveType);
    }

}
