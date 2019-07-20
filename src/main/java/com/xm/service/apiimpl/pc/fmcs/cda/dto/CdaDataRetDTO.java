package com.xm.service.apiimpl.pc.fmcs.cda.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by wangshuna on 2017/12/18.
 */
public class CdaDataRetDTO extends BaseRetDTO{
    @ApiResultFieldDesc(desc = "返回数据列表")
    List<CdaData> cdaDataList;

    public List<CdaData> getCdaDataList() {
        return cdaDataList;
    }

    public void setCdaDataList(List<CdaData> cdaDataList) {
        this.cdaDataList = cdaDataList;
    }
}
