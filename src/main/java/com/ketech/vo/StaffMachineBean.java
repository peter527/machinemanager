package com.ketech.vo;

import com.ketech.po.Machine;
import com.ketech.po.Staff;
import com.ketech.po.StaffMachine;

/**
 * @package: com.ketech.vo <br/>
 * @class: StaffMachineBean <br/>
 * @version: version 1.0 <br/>
 * @author: WuGuoHua <br/>
 * @date: 2017年11月11日 <br/>
 * @description: 人员设备实体类 <br/>.
 */

public class StaffMachineBean extends StaffMachine {

    private Staff staff;

    private Machine machine;

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }
}
