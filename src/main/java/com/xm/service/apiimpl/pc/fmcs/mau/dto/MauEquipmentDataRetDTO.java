package com.xm.service.apiimpl.pc.fmcs.mau.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by wangshuna on 2017/12/21.
 */
public class MauEquipmentDataRetDTO extends BaseRetDTO{
    @ApiResultFieldDesc(desc = "返回数据列表")
    List<MauEquipmentData> mauEquipmentDataList;

    public List<MauEquipmentData> getMauEquipmentDataList() {
        return mauEquipmentDataList;
    }

    public void setMauEquipmentDataList(List<MauEquipmentData> mauEquipmentDataList) {
        this.mauEquipmentDataList = mauEquipmentDataList;
    }
}
