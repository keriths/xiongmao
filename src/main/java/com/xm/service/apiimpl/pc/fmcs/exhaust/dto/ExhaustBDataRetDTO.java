package com.xm.service.apiimpl.pc.fmcs.exhaust.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by wangshuna on 2018/1/2.
 */
public class ExhaustBDataRetDTO extends BaseRetDTO{
    @ApiResultFieldDesc(desc = "返回数据列表")
    List<ExhaustBData> exhaustBDataList;

    public List<ExhaustBData> getExhaustBDataList() {
        return exhaustBDataList;
    }

    public void setExhaustBDataList(List<ExhaustBData> exhaustBDataList) {
        this.exhaustBDataList = exhaustBDataList;
    }
}
