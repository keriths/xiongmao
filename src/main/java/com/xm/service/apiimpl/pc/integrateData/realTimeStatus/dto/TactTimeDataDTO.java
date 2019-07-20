package com.xm.service.apiimpl.pc.integrateData.realTimeStatus.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.apiimpl.pc.cim.tactTime.dto.TactTimeMonthAvgDataDTO;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by wangshuna on 2018/3/6.
 */
public class TactTimeDataDTO extends BaseRetDTO{
    @ApiResultFieldDesc(desc = "重点设备(月度平均值)数据列表")
    List<TactTimeMonthAvgDataDTO> tactTimeMonthAvgDataList;

    public List<TactTimeMonthAvgDataDTO> getTactTimeMonthAvgDataList() {
        return tactTimeMonthAvgDataList;
    }

    public void setTactTimeMonthAvgDataList(List<TactTimeMonthAvgDataDTO> tactTimeMonthAvgDataList) {
        this.tactTimeMonthAvgDataList = tactTimeMonthAvgDataList;
    }
}
