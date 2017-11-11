package com.ketech.po;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "operation_log")
public class OperationLog implements Serializable {
    @Column(name = "log_id")
    private String logId;

    @Column(name = "staff_id")
    private String staffId;

    @Column(name = "location_id")
    private String locationId;

    @Column(name = "machine_id")
    private String machineId;

    @Column(name = "factory_id")
    private String factoryId;

    @Column(name = "machine_count")
    private Integer machineCount;

    @Column(name = "operation_type")
    private String operationType;

    @Column(name = "operation_date")
    private Date operationDate;

    @Column(name = "operation_memo")
    private String operationMemo;

    @Column(name = "create_date")
    private Date createDate;

    private static final long serialVersionUID = 1L;

    /**
     * @return log_id
     */
    public String getLogId() {
        return logId;
    }

    /**
     * @param logId
     */
    public void setLogId(String logId) {
        this.logId = logId == null ? null : logId.trim();
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
     * @return factory_id
     */
    public String getFactoryId() {
        return factoryId;
    }

    /**
     * @param factoryId
     */
    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId == null ? null : factoryId.trim();
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
     * @return operation_type
     */
    public String getOperationType() {
        return operationType;
    }

    /**
     * @param operationType
     */
    public void setOperationType(String operationType) {
        this.operationType = operationType == null ? null : operationType.trim();
    }

    /**
     * @return operation_date
     */
    public Date getOperationDate() {
        return operationDate;
    }

    /**
     * @param operationDate
     */
    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    /**
     * @return operation_memo
     */
    public String getOperationMemo() {
        return operationMemo;
    }

    /**
     * @param operationMemo
     */
    public void setOperationMemo(String operationMemo) {
        this.operationMemo = operationMemo == null ? null : operationMemo.trim();
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