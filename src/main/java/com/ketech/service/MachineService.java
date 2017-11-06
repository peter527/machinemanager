package com.ketech.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ketech.mapper.MachineMapper;
import com.ketech.po.Machine;
import com.ketech.tdo.MessageResultBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @package: com.ketech.service <br/>
 * @class: MachineService <br/>
 * @version: version 1.0 <br/>
 * @author: WuGuoHua <br/>
 * @date: 2017年11月06日 <br/>
 * @description: 设备Service <br/>.
 */

@Service
public class MachineService {

    @Autowired
    private MachineMapper machineMapper;

    public static final int SUCCESS_CODE = 200;
    public static final int FAIL_CODE = 500;

    /**
     * 分页查询设备信息
     * @param start 开始的条数
     * @param length 查询的条数
     * @param name 设备名称参数
     * @return 返回带分页信息的查询结果
     */
    public Map<String, Object> listMachine(int start, int length, String name){
        Machine machine = new Machine();
        if (StringUtils.isNotBlank(name)){
            machine.setMachineName(name);
        }
        PageHelper.offsetPage(start, length);
        List<Machine> machineList = machineMapper.select(machine);
        PageInfo pageInfo = new PageInfo(machineList);
        Map<String, Object> maps = new HashMap<String, Object>();
        // 总记录数
        maps.put("recordsTotal", pageInfo.getTotal());
        // 过滤后的总记录数
        maps.put("recordsFiltered", pageInfo.getTotal());
        // 分页列表
        maps.put("data", machineList);
        return maps;
    }

    /**
     * 根据参数搜索设备信息
     * @param machine 设备实体类
     * @return 返回查询结果集合
     */
    private List<Machine> searchMachine(Machine machine){
        List<Machine> machineList = machineMapper.select(machine);
        return machineList;
    }

    /**
     * 保存设备信息
     * @param machineId 设备编号
     * @param machineName 设备名称
     * @param factoryId 厂商编号
     * @param operationType 操作类型：0.新增，1.更新
     * @return 返回操作结果信息
     */
    public MessageResultBean saveMachine(String machineId, String machineName, String factoryId, String operationType){
        MessageResultBean messageResult = new MessageResultBean();
        int code = FAIL_CODE;
        String resultMessage = "";
        Machine machine = new Machine();
        machine.setMachineId(machineId);
        machine.setMachineName(machineName);
        machine.setFactoryId(factoryId);
        if ("0".equals(operationType)){
            machine.setStatus("0");
            machine.setCreateDate(new Date());
            String checkMessage = checkIdAndName(machineId, machineName);
            if (StringUtils.isNotBlank(checkMessage)){
                resultMessage = checkMessage;
            } else {
                if (machineMapper.insert(machine) > 0){
                    code = SUCCESS_CODE;
                    resultMessage = "新增设备成功";
                }
            }
        } else {
            machine.setModifyDate(new Date());
            if (machineMapper.updateByPrimaryKeySelective(machine) > 0){
                code = SUCCESS_CODE;
                resultMessage = "修改设备成功";
            }
        }
        messageResult.setCode(code);
        messageResult.setMsg(resultMessage);
        return messageResult;
    }

    /**
     * 检测设备编号跟与设备名称是否存在
     * @param id 待检测设备编号
     * @param name 待检测设备名称
     * @return 返回检测信息：不存在返回空字符串，否则返回对应的信息
     */
    private String checkIdAndName(String id, String name){
        String result = "";
        Machine idMachine = new Machine();
        idMachine.setMachineId(id);
        if (searchMachine(idMachine).size() > 0){
            result = "设备编号已存在";
        }
        Machine nameMachine = new Machine();
        nameMachine.setMachineName(name);
        if(searchMachine(nameMachine).size() > 0){
            result = "设备名称已存在";
        }
        return result;
    }
}
