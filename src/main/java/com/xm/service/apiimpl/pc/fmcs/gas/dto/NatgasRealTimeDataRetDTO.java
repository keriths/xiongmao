package com.xm.service.apiimpl.pc.fmcs.gas.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by luokaiming on 2017/11/30 0030.
 */
public class NatgasRealTimeDataRetDTO extends BaseRetDTO {
    @ApiResultFieldDesc(desc = "气体实时数据列表")
    private List<NatgasRealTimeData> natgasRealTimeDataList;

    public List<NatgasRealTimeData> getNatgasRealTimeDataList() {
        return natgasRealTimeDataList;
    }

    public void setNatgasRealTimeDataList(List<NatgasRealTimeData> natgasRealTimeDataList) {
        this.natgasRealTimeDataList = natgasRealTimeDataList;
    }
}
