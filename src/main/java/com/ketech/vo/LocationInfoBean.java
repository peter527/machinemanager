package com.ketech.vo;

import com.ketech.po.Project;
import com.ketech.po.Staff;

public class LocationInfoBean extends Project {

    private Staff staff;
    private Project project;

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
