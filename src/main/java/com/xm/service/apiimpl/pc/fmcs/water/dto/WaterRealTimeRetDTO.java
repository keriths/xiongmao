package com.xm.service.apiimpl.pc.fmcs.water.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by wangshuna on 2017/11/30.
 */
public class WaterRealTimeRetDTO extends BaseRetDTO{

    @ApiResultFieldDesc(desc = "返回数据列表")
    private List<WaterRealTimeDate> waterRealTimeDateList;

    public List<WaterRealTimeDate> getWaterRealTimeDateList() {
        return waterRealTimeDateList;
    }

    public void setWaterRealTimeDateList(List<WaterRealTimeDate> waterRealTimeDateList) {
        this.waterRealTimeDateList = waterRealTimeDateList;
    }
}
