package com.xm.service.apiimpl.pc.integrateData.system.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.apiimpl.pc.fmcs.cda.dto.CdaData;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by wangshuna on 2018/3/6.
 */
public class CdaDataDTO extends BaseRetDTO{
    @ApiResultFieldDesc(desc = "空气压缩系统数据返回")
    List<CdaData> cdaDataList;

    public List<CdaData> getCdaDataList() {
        return cdaDataList;
    }

    public void setCdaDataList(List<CdaData> cdaDataList) {
        this.cdaDataList = cdaDataList;
    }
}
