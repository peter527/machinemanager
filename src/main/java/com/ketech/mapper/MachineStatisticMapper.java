package com.ketech.mapper;

import com.ketech.po.LocationMachine;
import com.ketech.po.StaffMachine;
import com.ketech.vo.LocationMachineBean;
import com.ketech.vo.StaffMachineBean;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 作者: WuGuoHua <br/>
 * 创建日期: 2017年11月11日 <br/>.
 */

@Repository
public interface MachineStatisticMapper {

    List<StaffMachineBean> selectStaffMachineByStaff(StaffMachine staffMachine);

    List<LocationMachineBean> selectLocationMachineByStaff(LocationMachine locationMachine);

}
