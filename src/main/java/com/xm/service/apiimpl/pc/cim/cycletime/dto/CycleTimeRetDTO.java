package com.xm.service.apiimpl.pc.cim.cycletime.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by fanshuai on 17/11/12.
 */
public class CycleTimeRetDTO extends BaseRetDTO{
    @ApiResultFieldDesc(desc = "返回数据列表")
    private List<CycleTimeData> cycleTimeDataList;


    public List<CycleTimeData> getCycleTimeDataList() {
        return cycleTimeDataList;
    }

    public void setCycleTimeDataList(List<CycleTimeData> cycleTimeDataList) {
        this.cycleTimeDataList = cycleTimeDataList;
    }
}