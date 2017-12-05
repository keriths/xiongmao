package com.xm.service.apiimpl.pc.fmcs.humiture.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by wangshuna on 2017/12/5.
 */
public class HumiturePlaceDateRetDTO extends BaseRetDTO{

    @ApiResultFieldDesc(desc = "返回数据列表")
    List<HumiturePlaceDate> humiturePlaceDateList;

    public List<HumiturePlaceDate> getHumiturePlaceDateList() {
        return humiturePlaceDateList;
    }

    public void setHumiturePlaceDateList(List<HumiturePlaceDate> humiturePlaceDateList) {
        this.humiturePlaceDateList = humiturePlaceDateList;
    }
}
