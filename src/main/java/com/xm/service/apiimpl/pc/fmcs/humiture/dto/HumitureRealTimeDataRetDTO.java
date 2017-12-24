package com.xm.service.apiimpl.pc.fmcs.humiture.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by luokaiming on 2017/11/30 0030.
 */
public class HumitureRealTimeDataRetDTO extends BaseRetDTO{
    @ApiResultFieldDesc(desc = "返回数据列表")
    List<HumitureRealTimeDate> humitureRealTimeDateList;

    public List<HumitureRealTimeDate> getHumitureRealTimeDateList() {
        return humitureRealTimeDateList;
    }

    public void setHumitureRealTimeDateList(List<HumitureRealTimeDate> humitureRealTimeDateList) {
        this.humitureRealTimeDateList = humitureRealTimeDateList;
    }
}
