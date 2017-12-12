package com.xm.service.apiimpl.pc.fmcs.electricity.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by wangshsuna on 2017/11/30.
 */
public class ElectricityRetDTO extends BaseRetDTO{

    @ApiResultFieldDesc(desc = "返回数据列表")
    private List<ElectricityDate> electricityDateList;

    public List<ElectricityDate> getElectricityDateList() {
        return electricityDateList;
    }

    public void setElectricityDateList(List<ElectricityDate> electricityDateList) {
        this.electricityDateList = electricityDateList;
    }
}
