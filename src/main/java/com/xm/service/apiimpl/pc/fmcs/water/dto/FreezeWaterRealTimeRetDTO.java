package com.xm.service.apiimpl.pc.fmcs.water.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by wangshuna on 2017/11/30.
 */
public class FreezeWaterRealTimeRetDTO extends BaseRetDTO{

    @ApiResultFieldDesc(desc = "返回数据列表")
    private List<FreezeWaterRealTimeData> freezeWaterRealTimeDataList;

    public List<FreezeWaterRealTimeData> getFreezeWaterRealTimeDataList() {
        return freezeWaterRealTimeDataList;
    }

    public void setFreezeWaterRealTimeDataList(List<FreezeWaterRealTimeData> freezeWaterRealTimeDataList) {
        this.freezeWaterRealTimeDataList = freezeWaterRealTimeDataList;
    }
}
