package com.ketech.vo;

import com.ketech.po.Location;
import com.ketech.po.LocationMachine;
import com.ketech.po.Machine;

/**
 * @package: com.ketech.vo <br/>
 * @class: LocationMachineBean <br/>
 * @version: version 1.0 <br/>
 * @author: WuGuoHua <br/>
 * @date: 2017年11月11日 <br/>
 * @description: 地点设备实体类 <br/>.
 */

public class LocationMachineBean extends LocationMachine {

    private Location location;

    private Machine machine;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }
}
