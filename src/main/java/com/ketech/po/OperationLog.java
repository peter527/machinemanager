package com.ketech.po;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

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

    @Column(name = "machine_count")
    private Integer machineCount;

    @Column(name = "operation_type")
    private String operationType;

    @Column(name = "operation_date")
    private Date operationDate;

    @Column(name = "operatiion_memo")
    private String operatiionMemo;

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
     * @return operatiion_memo
     */
    public String getOperatiionMemo() {
        return operatiionMemo;
    }

    /**
     * @param operatiionMemo
     */
    public void setOperatiionMemo(String operatiionMemo) {
        this.operatiionMemo = operatiionMemo == null ? null : operatiionMemo.trim();
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