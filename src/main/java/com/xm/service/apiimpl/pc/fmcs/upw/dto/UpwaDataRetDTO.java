package com.xm.service.apiimpl.pc.fmcs.upw.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.apiimpl.pc.fmcs.wwt.dto.WwtaData;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by luokaiming on 2017/12/21.
 */
public class UpwaDataRetDTO extends BaseRetDTO{

    @ApiResultFieldDesc(desc = "返回数据集合")
    private List<UpwaData> upwaDataList;

    public List<UpwaData> getUpwaDataList() {
        return upwaDataList;
    }

    public void setUpwaDataList(List<UpwaData> upwaDataList) {
        this.upwaDataList = upwaDataList;
    }
}
