package com.xm.service.apiimpl.pc.fmcs.exhaust.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by wanghsuna on 2018/1/2.
 */
public class ExhaustADataRetDTO extends BaseRetDTO{
    @ApiResultFieldDesc(desc = "返回数据列表")
    List<ExhaustAData> exhaustADataList;

    public List<ExhaustAData> getExhaustADataList() {
        return exhaustADataList;
    }

    public void setExhaustADataList(List<ExhaustAData> exhaustADataList) {
        this.exhaustADataList = exhaustADataList;
    }
}
