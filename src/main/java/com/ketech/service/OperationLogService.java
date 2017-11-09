package com.ketech.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ketech.mapper.OperationLogMapper;
import com.ketech.po.OperationLog;
import com.ketech.po.Staff;
import com.ketech.tdo.MessageResultBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @package: com.ketech.service <br/>
 * @class: OperationLogService <br/>
 * @version: version 1.0 <br/>
 * @author: WuGuoHua <br/>
 * @date: 2017年11月09日 <br/>
 * @description: 操作日志Service <br/>.
 */

@Service
public class OperationLogService {

    public static final int SUCCESS_CODE = 200;
    public static final int FAIL_CODE = 500;

    @Resource
    private OperationLogMapper operationLogMapper;

    /**
     * 分页查询人员信息
     * @param start 开始的条数
     * @param length 查询的条数
     * @param operationLog 姓名参数
     * @return 返回带分页信息的查询结果
     */
    public Map<String, Object> listOperationLog(int start, int length, OperationLog operationLog){
        PageHelper.offsetPage(start, length);
        List<OperationLog> logList = operationLogMapper.select(operationLog);
        PageInfo pageInfo = new PageInfo(logList);
        Map<String, Object> maps = new HashMap<String, Object>();
        // 总记录数
        maps.put("recordsTotal", pageInfo.getTotal());
        // 过滤后的总记录数
        maps.put("recordsFiltered", pageInfo.getTotal());
        // 分页列表
        maps.put("data", logList);
        return maps;
    }

    public MessageResultBean saveOpeartionLog(OperationLog operationLog, String saveType) {
        MessageResultBean messageResult = new MessageResultBean();
        int code = FAIL_CODE;
        String resultMessage = "";
        operationLog.setCreateDate(new Date());
        operationLog.setLogId(UUID.randomUUID().toString().replace("-",""));
        if (operationLogMapper.insert(operationLog) > 0){
            code = SUCCESS_CODE;
            resultMessage = "新增日志成功";
        } else {
            resultMessage = "新增日志失败";
        }
        messageResult.setCode(code);
        messageResult.setMsg(resultMessage);
        return messageResult;
    }
}
