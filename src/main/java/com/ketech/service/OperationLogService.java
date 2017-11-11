package com.ketech.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ketech.exception.MachineCountException;
import com.ketech.mapper.*;
import com.ketech.po.*;
import com.ketech.tdo.MessageResultBean;
import com.ketech.vo.OperationLogBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Resource
    private MachineMapper machineMapper;
    @Resource
    private OperationLogInfoMapper operationLogInfoMapper;
    @Resource
    private LocationMachineMapper locationMachineMapper;
    @Resource
    private StaffMachineMapper staffMachineMapper;

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

    public Map<String, Object> listOperationLogInfo(int start, int length, OperationLog operationLog){
        PageHelper.offsetPage(start, length);
        List<OperationLogBean> logList = operationLogInfoMapper.selectOperationLogInfo(operationLog);
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

    @Transactional(rollbackFor = MachineCountException.class)
    public MessageResultBean saveOpeartionLog(OperationLog operationLog, String saveType) {
        MessageResultBean messageResult = new MessageResultBean();
        int code = FAIL_CODE;
        String resultMessage = "";
        Machine machine = machineMapper.selectByPrimaryKey(operationLog.getMachineId());
        operationLog.setFactoryId(machine.getFactoryId());
        operationLog.setCreateDate(new Date());
        operationLog.setLogId(UUID.randomUUID().toString().replace("-",""));
        if ("进货".equals(operationLog.getOperationType())){
            modifyLocationMachineCount(operationLog.getStaffId(),operationLog.getMachineId(),operationLog.getMachineCount(),true);
        } else if ("退货".equals(operationLog.getOperationType())){
            try{
                modifyLocationMachineCount(operationLog.getStaffId(),operationLog.getMachineId(),operationLog.getMachineCount(),false);
            } catch (MachineCountException e){
                messageResult.setMsg("设备库存不足");
                throw e;
            }

        } else if ("领用".equals(operationLog.getOperationType())){
            modifyStaffMachineCount(operationLog.getStaffId(),operationLog.getMachineId(),operationLog.getMachineCount(),true);
            try{
                modifyLocationMachineCount(operationLog.getLocationId(),operationLog.getMachineId(),operationLog.getMachineCount(),false);
            } catch (MachineCountException e){
                messageResult.setMsg("设备库存不足");
                throw e;
            }
        } else if ("归还".equals(operationLog.getOperationType())){
            try {
                modifyStaffMachineCount(operationLog.getStaffId(),operationLog.getMachineId(),operationLog.getMachineCount(),false);
            } catch (MachineCountException e){
                messageResult.setMsg("设备库存不足");
                throw e;
            }
            modifyLocationMachineCount(operationLog.getLocationId(),operationLog.getMachineId(),operationLog.getMachineCount(),true);
        } else if ("分配".equals(operationLog.getOperationType())){
            try {
                modifyLocationMachineCount(operationLog.getStaffId(),operationLog.getMachineId(),operationLog.getMachineCount(),false);
            } catch (MachineCountException e){
                messageResult.setMsg("设备库存不足");
                throw e;
            }
            modifyLocationMachineCount(operationLog.getLocationId(),operationLog.getMachineId(),operationLog.getMachineCount(),true);
        }
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

    @Transactional(rollbackFor = MachineCountException.class)
    public boolean modifyLocationMachineCount(String locationId, String machineId, int count, boolean add){
        boolean operationResult = false;
        LocationMachine locationMachine = new LocationMachine();
        locationMachine.setLocationId(locationId);
        locationMachine.setMachineId(machineId);
        List<LocationMachine> machineList = locationMachineMapper.select(locationMachine);
        if (add){
            if (null == machineList || machineList.size() == 0){
                locationMachine.setMachineCount(count);
                locationMachine.setId(UUID.randomUUID().toString().replace("-",""));
                locationMachine.setCreateDate(new Date());
                if (locationMachineMapper.insert(locationMachine) > 0){
                    operationResult = true;
                }
            } else {
                LocationMachine record = machineList.get(0);
                record.setMachineCount(record.getMachineCount() + count);
                if (locationMachineMapper.updateByPrimaryKey(record) > 0){
                    operationResult = true;
                }
            }
        } else {
            if (null == machineList || machineList.size() == 0){
                operationResult = false;
            } else {
                LocationMachine record = machineList.get(0);
                if (record.getMachineCount() < count){
                    throw new MachineCountException("设备数量不足");
                } else {
                    record.setMachineCount(record.getMachineCount() - count);
                    if (locationMachineMapper.updateByPrimaryKey(record) > 0){
                        operationResult = true;
                    }
                }
            }
        }
        return operationResult;
    }

    @Transactional(rollbackFor = MachineCountException.class)
    public boolean modifyStaffMachineCount(String staffId, String machineId, int count, boolean add){
        boolean operationResult = false;
        StaffMachine staffMachine = new StaffMachine();
        staffMachine.setStaffId(staffId);
        staffMachine.setMachineId(machineId);
        List<StaffMachine> machineList = staffMachineMapper.select(staffMachine);
        if (add){
            if (null == machineList || machineList.size() == 0){
                staffMachine.setMachineCount(count);
                staffMachine.setId(UUID.randomUUID().toString().replace("-",""));
                staffMachine.setCreateDate(new Date());
                if (staffMachineMapper.insert(staffMachine) > 0){
                    operationResult = true;
                }
            } else {
                StaffMachine record = machineList.get(0);
                record.setMachineCount(record.getMachineCount() + count);
                if (staffMachineMapper.updateByPrimaryKey(record) > 0){
                    operationResult = true;
                }
            }
        } else {
            if (null == machineList || machineList.size() == 0){
                operationResult = false;
            } else {
                StaffMachine record = machineList.get(0);
                if (record.getMachineCount() < count){
                    throw new MachineCountException("设备数量不足");
                } else {
                    record.setMachineCount(record.getMachineCount() - count);
                    if (staffMachineMapper.updateByPrimaryKey(record) > 0){
                        operationResult = true;
                    }
                }
            }
        }
        return operationResult;
    }

}
