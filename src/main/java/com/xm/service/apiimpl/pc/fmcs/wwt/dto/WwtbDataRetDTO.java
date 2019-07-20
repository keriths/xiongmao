package com.xm.service.apiimpl.pc.fmcs.wwt.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by wanghsuna on 2017/11/30.
 */
public class WwtbDataRetDTO extends BaseRetDTO{

    @ApiResultFieldDesc(desc = "返回数据集合")
    private List<WwtbData> wwtbDataList;

    public List<WwtbData> getWwtbDataList() {
        return wwtbDataList;
    }

    public void setWwtbDataList(List<WwtbData> wwtbDataList) {
        this.wwtbDataList = wwtbDataList;
    }
}
