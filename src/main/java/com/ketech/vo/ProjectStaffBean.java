package com.ketech.vo;

import com.ketech.po.Project;
import com.ketech.po.Staff;

/**
 * @package: com.ketech.vo <br/>
 * @class: ProjectStaffBean <br/>
 * @version: version 1.0 <br/>
 * @author: WuGuoHua <br/>
 * @date: 2017年11月07日 <br/>
 * @description: 项目负责人实体类 <br/>.
 */

public class ProjectStaffBean extends Project {

    private Staff staff;

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
}
