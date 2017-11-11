package com.ketech.vo;

import java.util.Date;

/**
 * @package: com.ketech.vo <br/>
 * @class: OperationLogBean <br/>
 * @version: version 1.0 <br/>
 * @author: WuGuoHua <br/>
 * @date: 2017年11月10日 <br/>
 * @description: 操作日志包装类 <br/>.
 */

public class OperationLogBean {

    private String raiseId;
    private String acceptId;
    private String machineId;
    private Integer machineCount;
    private String operationType;
    private Date operationDate;
    private String operationMemo;
    private String staffName;
    private String raiseName;
    private String acceptName;
    private String machineName;
    private String factoryName;

    public String getRaiseId() {
        return raiseId;
    }

    public void setRaiseId(String raiseId) {
        this.raiseId = raiseId;
    }

    public String getAcceptId() {
        return acceptId;
    }

    public void setAcceptId(String acceptId) {
        this.acceptId = acceptId;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public Integer getMachineCount() {
        return machineCount;
    }

    public void setMachineCount(Integer machineCount) {
        this.machineCount = machineCount;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public String getOperationMemo() {
        return operationMemo;
    }

    public void setOperationMemo(String operationMemo) {
        this.operationMemo = operationMemo;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getRaiseName() {
        return raiseName;
    }

    public void setRaiseName(String raiseName) {
        this.raiseName = raiseName;
    }

    public String getAcceptName() {
        return acceptName;
    }

    public void setAcceptName(String acceptName) {
        this.acceptName = acceptName;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }
}
