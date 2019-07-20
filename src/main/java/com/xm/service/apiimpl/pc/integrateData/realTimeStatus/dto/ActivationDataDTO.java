package com.xm.service.apiimpl.pc.integrateData.realTimeStatus.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.apiimpl.pc.cim.oee.dto.ActivationDate;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by wangshuna on 2018/3/6.
 */
public class ActivationDataDTO extends BaseRetDTO {

    @ApiResultFieldDesc(desc = "重点设备稼动率列表")
    List<ActivationDate> activationDateList;

    public List<ActivationDate> getActivationDateList() {
        return activationDateList;
    }

    public void setActivationDateList(List<ActivationDate> activationDateList) {
        this.activationDateList = activationDateList;
    }
}
