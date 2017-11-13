package com.xm.service.apiimpl.pc.cim.activation.dto;

import com.xm.service.annotations.ApiResultFieldDesc;

/**
 * Created by fanshuai on 17/11/12.
 */
public class ActivationStatusDTO {
    @ApiResultFieldDesc(desc = "EQP类型的状态,如PHOTO,PVD,CVD,WET,DE")
    private Integer status;
    @ApiResultFieldDesc(desc = "状态值")
    private Integer statusNum;

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
