package com.xm.service.apiimpl.pc.cim.activation.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;

/**
 * Created by fanshuai on 17/11/12.
 */
public class ActivationStatusDTO {
    @ApiResultFieldDesc(desc = "EQP类型的状态,如PHOTO,PVD,CVD,WET,DE")
    private String status;
    @ApiResultFieldDesc(desc = "EQP状态累计时间")
    private Integer statusNum;
    @ApiResultFieldDesc(desc = "厂别,如Array,Cell,CF,SL-OC")
    private String factory;
    @ApiResultFieldDesc(desc = "EQP类型,如RUN,TRB,WAIT,MAN,MNT")
    private String eqpType;
    @ApiResultFieldDesc(desc = "数据写入时间")
    private String dateTime;

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getEqpType() {
        return eqpType;
    }

    public void setEqpType(String eqpType) {
        this.eqpType = eqpType;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStatusNum() {
        return statusNum;
    }

    public void setStatusNum(Integer statusNum) {
        this.statusNum = statusNum;
    }
}
