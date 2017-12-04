package com.xm.service.apiimpl.pc.fmcs.water.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by wangshuna on 2017/11/30.
 */
public class PureWaterRealTimeRetDTO extends BaseRetDTO{

    @ApiResultFieldDesc(desc = "返回数据列表")
    private List<PureWaterRealTimeData> pureWaterRealTimeDataList;

    public List<PureWaterRealTimeData> getPureWaterRealTimeDataList() {
        return pureWaterRealTimeDataList;
    }

    public void setPureWaterRealTimeDataList(List<PureWaterRealTimeData> pureWaterRealTimeDataList) {
        this.pureWaterRealTimeDataList = pureWaterRealTimeDataList;
    }
}
