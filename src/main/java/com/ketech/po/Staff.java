package com.ketech.po;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class Staff implements Serializable {
    /**
     * 人员ID
     */
    @Id
    @Column(name = "staff_id")
    private String staffId;

    /**
     * 人员姓名
     */
    @Column(name = "staff_name")
    private String staffName;

    /**
     * 人员联系方式
     */
    @Column(name = "staff_phone")
    private String staffPhone;

    /**
     * 人员状态
     */
    @Column(name = "staff_status")
    private String staffStatus;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 修改时间
     */
    @Column(name = "modify_date")
    private Date modifyDate;

    private static final long serialVersionUID = 1L;

    /**
     * 获取人员ID
     *
     * @return staff_id - 人员ID
     */
    public String getStaffId() {
        return staffId;
    }

    /**
     * 设置人员ID
     *
     * @param staffId 人员ID
     */
    public void setStaffId(String staffId) {
        this.staffId = staffId == null ? null : staffId.trim();
    }

    /**
     * 获取人员姓名
     *
     * @return staff_name - 人员姓名
     */
    public String getStaffName() {
        return staffName;
    }

    /**
     * 设置人员姓名
     *
     * @param staffName 人员姓名
     */
    public void setStaffName(String staffName) {
        this.staffName = staffName == null ? null : staffName.trim();
    }

    /**
     * 获取人员联系方式
     *
     * @return staff_phone - 人员联系方式
     */
    public String getStaffPhone() {
        return staffPhone;
    }

    /**
     * 设置人员联系方式
     *
     * @param staffPhone 人员联系方式
     */
    public void setStaffPhone(String staffPhone) {
        this.staffPhone = staffPhone == null ? null : staffPhone.trim();
    }

    /**
     * 获取人员状态
     *
     * @return staff_status - 人员状态
     */
    public String getStaffStatus() {
        return staffStatus;
    }

    /**
     * 设置人员状态
     *
     * @param staffStatus 人员状态
     */
    public void setStaffStatus(String staffStatus) {
        this.staffStatus = staffStatus == null ? null : staffStatus.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取修改时间
     *
     * @return modify_date - 修改时间
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
     * 设置修改时间
     *
     * @param modifyDate 修改时间
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}