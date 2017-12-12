package com.xm.service.apiimpl.pc.fmcs.humiture.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by luokaiming on 2017/11/30 0030.
 */
public class HumitureDataRetDTO extends BaseRetDTO{
    @ApiResultFieldDesc(desc = "返回数据列表")
    List<HumitureDate> humitureDateList;

    public List<HumitureDate> getHumitureDateList() {
        return humitureDateList;
    }

    public void setHumitureDateList(List<HumitureDate> humitureDateList) {
        this.humitureDateList = humitureDateList;
    }
}
