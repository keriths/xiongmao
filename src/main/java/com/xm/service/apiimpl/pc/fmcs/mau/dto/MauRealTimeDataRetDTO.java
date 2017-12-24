package com.xm.service.apiimpl.pc.fmcs.mau.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by wangshuna on 2017/12/21.
 */
public class MauRealTimeDataRetDTO extends BaseRetDTO{
    @ApiResultFieldDesc(desc = "返回数据列表")
    List<MauRealTimeData> mauRealTimeDataList;

    public List<MauRealTimeData> getMauRealTimeDataList() {
        return mauRealTimeDataList;
    }

    public void setMauRealTimeDataList(List<MauRealTimeData> mauRealTimeDataList) {
        this.mauRealTimeDataList = mauRealTimeDataList;
    }
}
