package com.ketech.po;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class Factory implements Serializable {
    /**
     * 厂商编号
     */
    @Id
    @Column(name = "factory_id")
    private String factoryId;

    /**
     * 厂商名称
     */
    @Column(name = "factory_name")
    private String factoryName;

    /**
     * 负责人
     */
    @Column(name = "factory_charger")
    private String factoryCharger;

    /**
     * 联系电话
     */
    @Column(name = "charger_phone")
    private String chargerPhone;

    /**
     * 状态
     */
    private String status;

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
     * 获取厂商编号
     *
     * @return factory_id - 厂商编号
     */
    public String getFactoryId() {
        return factoryId;
    }

    /**
     * 设置厂商编号
     *
     * @param factoryId 厂商编号
     */
    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId == null ? null : factoryId.trim();
    }

    /**
     * 获取厂商名称
     *
     * @return factory_name - 厂商名称
     */
    public String getFactoryName() {
        return factoryName;
    }

    /**
     * 设置厂商名称
     *
     * @param factoryName 厂商名称
     */
    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName == null ? null : factoryName.trim();
    }

    /**
     * 获取负责人
     *
     * @return factory_charger - 负责人
     */
    public String getFactoryCharger() {
        return factoryCharger;
    }

    /**
     * 设置负责人
     *
     * @param factoryCharger 负责人
     */
    public void setFactoryCharger(String factoryCharger) {
        this.factoryCharger = factoryCharger == null ? null : factoryCharger.trim();
    }

    /**
     * 获取联系电话
     *
     * @return charger_phone - 联系电话
     */
    public String getChargerPhone() {
        return chargerPhone;
    }

    /**
     * 设置联系电话
     *
     * @param chargerPhone 联系电话
     */
    public void setChargerPhone(String chargerPhone) {
        this.chargerPhone = chargerPhone == null ? null : chargerPhone.trim();
    }

    /**
     * 获取状态
     *
     * @return status - 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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