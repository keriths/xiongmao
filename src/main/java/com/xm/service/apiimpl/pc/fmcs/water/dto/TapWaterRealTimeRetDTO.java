package com.xm.service.apiimpl.pc.fmcs.water.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by wangshuna on 2017/11/30.
 */
public class TapWaterRealTimeRetDTO extends BaseRetDTO{

    @ApiResultFieldDesc(desc = "返回数据列表")
    private List<TapWaterRealTimeData> waterRealTimeDateList;

    public List<TapWaterRealTimeData> getWaterRealTimeDateList() {
        return waterRealTimeDateList;
    }

    public void setWaterRealTimeDateList(List<TapWaterRealTimeData> waterRealTimeDateList) {
        this.waterRealTimeDateList = waterRealTimeDateList;
    }
}
