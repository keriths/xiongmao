package com.xm.service.apiimpl.pc.fmcs.water.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by wanghsuna on 2017/11/30.
 */
public class PureWaterEveryDayRetDTO extends BaseRetDTO{

    @ApiResultFieldDesc(desc = "返回数据集合")
    private List<PureWaterEveryDayData> pureWaterEveryDayDataList;

    public List<PureWaterEveryDayData> getPureWaterEveryDayDataList() {
        return pureWaterEveryDayDataList;
    }

    public void setPureWaterEveryDayDataList(List<PureWaterEveryDayData> pureWaterEveryDayDataList) {
        this.pureWaterEveryDayDataList = pureWaterEveryDayDataList;
    }
}
