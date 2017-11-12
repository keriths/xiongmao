package com.xm.service.apiimpl.pc.cim.equipmentstatus.dto;

import com.xm.service.annotations.ApiResultFieldDesc;

import java.util.List;
import java.util.Map;

/**
 * Created by fanshuai on 17/11/8.
 */
public class EquipmentRealTimeStatusResultDTO {
    @ApiResultFieldDesc(desc = "可稼动设备数量")
    private String activationNum;
    @ApiResultFieldDesc(desc = "可稼动设备比例")
    private String activationRate;
    @ApiResultFieldDesc(desc = "PM中设备数量")
    private String pmNum;
    @ApiResultFieldDesc(desc = "PM中设备比例")
    private String pmRate;
    @ApiResultFieldDesc(desc = "故障中设备数量")
    private String faultNum;
    @ApiResultFieldDesc(desc = "故障中设备比例")
    private String faultRate;
    @ApiResultFieldDesc(desc = "AMHS链接状态")
    private String amhsStatus;

    public String getActivationNum() {
        return activationNum;
    }

    public void setActivationNum(String activationNum) {
        this.activationNum = activationNum;
    }

    public String getActivationRate() {
        return activationRate;
    }

    public void setActivationRate(String activationRate) {
        this.activationRate = activationRate;
    }

    public String getPmNum() {
        return pmNum;
    }

    public void setPmNum(String pmNum) {
        this.pmNum = pmNum;
    }

    public String getPmRate() {
        return pmRate;
    }

    public void setPmRate(String pmRate) {
        this.pmRate = pmRate;
    }

    public String getFaultNum() {
        return faultNum;
    }

    public void setFaultNum(String faultNum) {
        this.faultNum = faultNum;
    }

    public String getFaultRate() {
        return faultRate;
    }

    public void setFaultRate(String faultRate) {
        this.faultRate = faultRate;
    }

    public String getAmhsStatus() {
        return amhsStatus;
    }

    public void setAmhsStatus(String amhsStatus) {
        this.amhsStatus = amhsStatus;
    }
}
