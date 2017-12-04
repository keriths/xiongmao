package com.xm.service.apiimpl.pc.fmcs.water.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by wanghsuna on 2017/11/30.
 */
public class TapWaterEveryDayRetDTO extends BaseRetDTO{

    @ApiResultFieldDesc(desc = "返回数据集合")
    private List<TapWaterEveryDayData> waterEveryDayDateList;

    public List<TapWaterEveryDayData> getWaterEveryDayDateList() {
        return waterEveryDayDateList;
    }

    public void setWaterEveryDayDateList(List<TapWaterEveryDayData> waterEveryDayDateList) {
        this.waterEveryDayDateList = waterEveryDayDateList;
    }
}
