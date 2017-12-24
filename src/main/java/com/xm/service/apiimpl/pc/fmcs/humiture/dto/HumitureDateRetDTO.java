package com.xm.service.apiimpl.pc.fmcs.humiture.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by wangshuna on 2017/12/5.
 */
public class HumitureDateRetDTO extends BaseRetDTO{

    @ApiResultFieldDesc(desc = "返回数据列表")
    List<HumitureDate> humitureDateList;

    public List<HumitureDate> getHumitureDateList() {
        return humitureDateList;
    }

    public void setHumitureDateList(List<HumitureDate> humitureDateList) {
        this.humitureDateList = humitureDateList;
    }
}
