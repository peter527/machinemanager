package com.ketech.po;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class Location implements Serializable {
    @Id
    @Column(name = "location_id")
    private String locationId;

    @Column(name = "location_name")
    private String locationName;

    @Column(name = "location_type")
    private String locationType;

    @Column(name = "location_charger_name")
    private String locationChargerName;

    @Column(name = "location_charger_phone")
    private String locationChargerPhone;

    @Column(name = "staff_id")
    private String staffId;

    @Column(name = "project_id")
    private String projectId;

    private String status;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "modify_date")
    private Date modifyDate;

    private static final long serialVersionUID = 1L;

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
     * @return location_name
     */
    public String getLocationName() {
        return locationName;
    }

    /**
     * @param locationName
     */
    public void setLocationName(String locationName) {
        this.locationName = locationName == null ? null : locationName.trim();
    }

    /**
     * @return location_type
     */
    public String getLocationType() {
        return locationType;
    }

    /**
     * @param locationType
     */
    public void setLocationType(String locationType) {
        this.locationType = locationType == null ? null : locationType.trim();
    }

    /**
     * @return location_charger_name
     */
    public String getLocationChargerName() {
        return locationChargerName;
    }

    /**
     * @param locationChargerName
     */
    public void setLocationChargerName(String locationChargerName) {
        this.locationChargerName = locationChargerName == null ? null : locationChargerName.trim();
    }

    /**
     * @return location_charger_phone
     */
    public String getLocationChargerPhone() {
        return locationChargerPhone;
    }

    /**
     * @param locationChargerPhone
     */
    public void setLocationChargerPhone(String locationChargerPhone) {
        this.locationChargerPhone = locationChargerPhone == null ? null : locationChargerPhone.trim();
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
     * @return project_id
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * @param projectId
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    /**
     * @return modify_date
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
     * @param modifyDate
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}