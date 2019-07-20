package com.xm.service.apiimpl.pc.fmcs.pcw.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by wangshuna on 2017/12/18.
 */
public class HumiturePressureDataRetDTO extends BaseRetDTO{
    @ApiResultFieldDesc(desc = "返回数据列表")
    List<HumiturePressureData> humiturePressureDataList;

    public List<HumiturePressureData> getHumiturePressureDataList() {
        return humiturePressureDataList;
    }

    public void setHumiturePressureDataList(List<HumiturePressureData> humiturePressureDataList) {
        this.humiturePressureDataList = humiturePressureDataList;
    }
}
