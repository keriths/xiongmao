package com.xm.service.apiimpl.pc.integrateData.system.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.apiimpl.pc.fmcs.pcw.dto.HumiturePressureData;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by wangshuna on 2018/3/6.
 */
public class PcwDataDTO extends BaseRetDTO{
    @ApiResultFieldDesc(desc = "工艺冷却水系统数据返回")
    List<HumiturePressureData> humiturePressureDataList;

    public List<HumiturePressureData> getHumiturePressureDataList() {
        return humiturePressureDataList;
    }

    public void setHumiturePressureDataList(List<HumiturePressureData> humiturePressureDataList) {
        this.humiturePressureDataList = humiturePressureDataList;
    }
}
