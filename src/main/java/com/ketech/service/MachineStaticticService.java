package com.ketech.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ketech.mapper.MachineStatisticMapper;
import com.ketech.po.LocationMachine;
import com.ketech.po.StaffMachine;
import com.ketech.vo.LocationMachineBean;
import com.ketech.vo.StaffMachineBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @package: com.ketech.service <br/>
 * @class: MachineStaticticService <br/>
 * @version: version 1.0 <br/>
 * @author: WuGuoHua <br/>
 * @date: 2017年11月11日 <br/>
 * @description: 设备统计Service <br/>.
 */

@Service
public class MachineStaticticService {

    @Resource
    private MachineStatisticMapper machineStatisticMapper;

    public Map<String,Object> listStaffMachine(int start, int length, StaffMachine staffMachine) {
        PageHelper.offsetPage(start, length);
        List<StaffMachineBean> staffMachineList = machineStatisticMapper.selectStaffMachineByStaff(staffMachine);
        PageInfo pageInfo = new PageInfo(staffMachineList);
        Map<String, Object> maps = new HashMap<String, Object>();
        // 总记录数
        maps.put("recordsTotal", pageInfo.getTotal());
        // 过滤后的总记录数
        maps.put("recordsFiltered", pageInfo.getTotal());
        // 分页列表
        maps.put("data", staffMachineList);
        return maps;
    }

    public Map<String,Object> listLocationMachine(int start, int length, LocationMachine locationMachine) {
        PageHelper.offsetPage(start, length);
        List<LocationMachineBean> locationMachineBeanList = machineStatisticMapper.selectLocationMachineByStaff(locationMachine);
        PageInfo pageInfo = new PageInfo(locationMachineBeanList);
        Map<String, Object> maps = new HashMap<String, Object>();
        // 总记录数
        maps.put("recordsTotal", pageInfo.getTotal());
        // 过滤后的总记录数
        maps.put("recordsFiltered", pageInfo.getTotal());
        // 分页列表
        maps.put("data", locationMachineBeanList);
        return maps;
    }
}
