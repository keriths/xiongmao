package com.xm.service.apiimpl.pc.integrateData.system.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.apiimpl.pc.fmcs.upw.dto.UpwbData;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by wangshuna on 2018/3/6.
 */
public class UpwDataDTO extends BaseRetDTO{
    @ApiResultFieldDesc(desc = "纯水制造系统数据返回")
    List<UpwbData> upwbDataList;

    public List<UpwbData> getUpwbDataList() {
        return upwbDataList;
    }

    public void setUpwbDataList(List<UpwbData> upwbDataList) {
        this.upwbDataList = upwbDataList;
    }
}
