package com.ketech.po;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "staff_machine")
public class StaffMachine implements Serializable {
    @Id
    private String id;

    @Column(name = "staff_id")
    private String staffId;

    @Column(name = "machine_id")
    private String machineId;

    @Column(name = "machine_count")
    private Integer machineCount;

    @Column(name = "create_date")
    private Date createDate;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * @return staff_id
     */
    public String getStaffId() {
        return staffId;
    }

    /**
     * @param staffId
     */
    public void setStaffId(String staffId) {
        this.staffId = staffId == null ? null : staffId.trim();
    }

    /**
     * @return machine_id
     */
    public String getMachineId() {
        return machineId;
    }

    /**
     * @param machineId
     */
    public void setMachineId(String machineId) {
        this.machineId = machineId == null ? null : machineId.trim();
    }

    /**
     * @return machine_count
     */
    public Integer getMachineCount() {
        return machineCount;
    }

    /**
     * @param machineCount
     */
    public void setMachineCount(Integer machineCount) {
        this.machineCount = machineCount;
    }

    /**
     * @return create_date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}