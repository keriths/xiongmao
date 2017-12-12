package com.xm.service.apiimpl.pc.fmcs.gas.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by wangshuna on 2017/12/6.
 */
public class BigGasRealTimeDateRetDTO extends BaseRetDTO{

    @ApiResultFieldDesc(desc = "返回数据列表")
    List<BigGasRealTimeDate> bigGasRealTimeDateList;

    public List<BigGasRealTimeDate> getBigGasRealTimeDateList() {
        return bigGasRealTimeDateList;
    }

    public void setBigGasRealTimeDateList(List<BigGasRealTimeDate> bigGasRealTimeDateList) {
        this.bigGasRealTimeDateList = bigGasRealTimeDateList;
    }
}
