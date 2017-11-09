package com.ketech.service;

import com.ketech.mapper.LocationMapper;
import com.ketech.mapper.MachineMapper;
import com.ketech.mapper.StaffMapper;
import com.ketech.po.Location;
import com.ketech.po.Machine;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @package: com.ketech.service <br/>
 * @class: SelectService <br/>
 * @version: version 1.0 <br/>
 * @author: WuGuoHua <br/>
 * @date: 2017年11月09日 <br/>
 * @description: 用于填充Select的Service <br/>.
 */

@Service
public class SelectService {

    @Resource
    private StaffMapper staffMapper;
    @Resource
    private LocationMapper locationMapper;
    @Resource
    private MachineMapper machineMapper;

    public Map<String,Object> listLocation(List<String> idList) {
        Map<String, Object> maps = new HashMap<String, Object>();
        Machine machine = new Machine();
        List<Machine> machineList = machineMapper.select(machine);
        if (null == idList || idList.size() == 0 ){
            Location location = new Location();
            List<Location> locationList = locationMapper.select(location);
            maps.put("locationList", locationList);
        } else {
            Example example = new Example(Location.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andIn("locationType", idList);
            List<Location> locationList = locationMapper.selectByExample(example);
            maps.put("locationList", locationList);
        }
        maps.put("machineList", machineList);
        return maps;
    }
}
