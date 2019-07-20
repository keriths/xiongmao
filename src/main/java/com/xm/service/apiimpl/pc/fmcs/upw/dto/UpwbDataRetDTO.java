package com.xm.service.apiimpl.pc.fmcs.upw.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by luokaiming on 2017/12/21.
 */
public class UpwbDataRetDTO extends BaseRetDTO{

    @ApiResultFieldDesc(desc = "返回数据集合")
    private List<UpwbData> upwbDataList;

    public List<UpwbData> getUpwbDataList() {
        return upwbDataList;
    }

    public void setUpwbDataList(List<UpwbData> upwbDataList) {
        this.upwbDataList = upwbDataList;
    }
}
