package com.xm.service.apiimpl.pc.fmcs.pcw.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by wangshsuna on 2017/12/21.
 */
public class PcwEquipmentDataRetDTO extends BaseRetDTO{
    @ApiResultFieldDesc(desc = "返回数据列表")
    List<PcwEquipmentData> pcwEquipmentDataList;

    public List<PcwEquipmentData> getPcwEquipmentDataList() {
        return pcwEquipmentDataList;
    }

    public void setPcwEquipmentDataList(List<PcwEquipmentData> pcwEquipmentDataList) {
        this.pcwEquipmentDataList = pcwEquipmentDataList;
    }
}
