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

    @Column(name = "location_address")
    private String locationAddress;

    @Column(name = "location_charger_name")
    private String locationChargerName;

    @Column(name = "location_charger_phone")
    private String locationChargerPhone;

    @Column(name = "company_staff_id")
    private String companyStaffId;

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
     * @return location_address
     */
    public String getLocationAddress() {
        return locationAddress;
    }

    /**
     * @param locationAddress
     */
    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress == null ? null : locationAddress.trim();
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
     * @return company_staff_id
     */
    public String getCompanyStaffId() {
        return companyStaffId;
    }

    /**
     * @param companyStaffId
     */
    public void setCompanyStaffId(String companyStaffId) {
        this.companyStaffId = companyStaffId == null ? null : companyStaffId.trim();
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