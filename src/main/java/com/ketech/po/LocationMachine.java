package com.ketech.po;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "location_machine")
public class LocationMachine implements Serializable {
    @Id
    private String id;

    @Column(name = "location_id")
    private String locationId;

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
     * @return location_id
     */
    public String getLocationId() {
        return locationId;
    }

    /**
     * @param locationId
     */
    public void setLocationId(String locationId) {
        this.locationId = locationId == null ? null : locationId.trim();
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