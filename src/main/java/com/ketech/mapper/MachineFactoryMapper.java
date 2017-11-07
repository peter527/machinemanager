package com.ketech.mapper;

import com.ketech.po.Machine;
import com.ketech.vo.MachineFactoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @package: com.ketech.mapper <br/>
 * @class: MachineFactoryMapper <br/>
 * @version: version 1.0 <br/>
 * @author: WuGuoHua <br/>
 * @date: 2017年11月06日 <br/>
 * @description: 设备信息查询Mapper <br/>.
 */

@Repository
public interface MachineFactoryMapper {

    List<MachineFactoryBean> selectMachineAndFactoryByMachine(Machine machine);

}