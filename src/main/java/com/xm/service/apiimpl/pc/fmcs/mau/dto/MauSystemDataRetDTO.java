package com.xm.service.apiimpl.pc.fmcs.mau.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by wangshuna on 2017/12/21.
 */
public class MauSystemDataRetDTO extends BaseRetDTO{
    @ApiResultFieldDesc(desc = "返回数据列表")
    List<MauSystemData> mauSystemDataList;

    public List<MauSystemData> getMauSystemDataList() {
        return mauSystemDataList;
    }

    public void setMauSystemDataList(List<MauSystemData> mauSystemDataList) {
        this.mauSystemDataList = mauSystemDataList;
    }
}
