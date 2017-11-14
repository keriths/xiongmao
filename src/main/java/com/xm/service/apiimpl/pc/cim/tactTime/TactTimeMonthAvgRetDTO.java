package com.xm.service.apiimpl.pc.cim.tactTime;

import com.xm.platform.annotations.ApiResultFieldDesc;

import java.util.List;

/**
 * Created by fanshuai on 17/11/15.
 */
public class TactTimeMonthAvgRetDTO {
    @ApiResultFieldDesc(desc = "月度平均值数据列表")
    List<TactTimeMonthAvgDataDTO> tactTimeMonthAvgDataDTOList;

    public List<TactTimeMonthAvgDataDTO> getTactTimeMonthAvgDataDTOList() {
        return tactTimeMonthAvgDataDTOList;
    }

    public void setTactTimeMonthAvgDataDTOList(List<TactTimeMonthAvgDataDTO> tactTimeMonthAvgDataDTOList) {
        this.tactTimeMonthAvgDataDTOList = tactTimeMonthAvgDataDTOList;
    }
}
