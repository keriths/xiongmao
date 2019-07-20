package com.xm.service.apiimpl.pc.fmcs.mau.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by luokaiming on 2018/1/9 0009.
 */
public class MauDataRetDTO extends BaseRetDTO{

    @ApiResultFieldDesc(desc = "新风空调系统实时数据")
    private List<MauRealTimeData> mauRealTimeDataList;

    @ApiResultFieldDesc(desc = "新风空调系统接口数据")
    private List<MauSystemData> mauSystemDataList;

    public List<MauRealTimeData> getMauRealTimeDataList() {
        return mauRealTimeDataList;
    }

    public void setMauRealTimeDataList(List<MauRealTimeData> mauRealTimeDataList) {
        this.mauRealTimeDataList = mauRealTimeDataList;
    }

    public List<MauSystemData> getMauSystemDataList() {
        return mauSystemDataList;
    }

    public void setMauSystemDataList(List<MauSystemData> mauSystemDataList) {
        this.mauSystemDataList = mauSystemDataList;
    }
}
