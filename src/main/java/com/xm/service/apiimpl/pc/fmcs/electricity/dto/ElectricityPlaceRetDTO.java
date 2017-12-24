package com.xm.service.apiimpl.pc.fmcs.electricity.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by wangshsuna on 2017/11/30.
 */
public class ElectricityPlaceRetDTO extends BaseRetDTO{

    @ApiResultFieldDesc(desc = "返回数据列表")
    private List<ElectricityPlaceDate> electricityPlaceDateList;

    public List<ElectricityPlaceDate> getElectricityPlaceDateList() {
        return electricityPlaceDateList;
    }

    public void setElectricityPlaceDateList(List<ElectricityPlaceDate> electricityPlaceDateList) {
        this.electricityPlaceDateList = electricityPlaceDateList;
    }
}
