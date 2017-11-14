package com.xm.service.apiimpl.pc.cim.activation.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;

/**
 * Created by fanshuai on 17/11/12.
 */
public class ActivationStatusDTO {
    @ApiResultFieldDesc(desc = "EQP类型的状态,如PHOTO,PVD,CVD,WET,DE")
    private Integer status;
    @ApiResultFieldDesc(desc = "某个状态EQP类型的状态值")
    private Integer statusNum;

    @ApiResultFieldDesc(desc = "数据写入时间")
    private String dateTime;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatusNum() {
        return statusNum;
    }

    public void setStatusNum(Integer statusNum) {
        this.statusNum = statusNum;
    }
}
