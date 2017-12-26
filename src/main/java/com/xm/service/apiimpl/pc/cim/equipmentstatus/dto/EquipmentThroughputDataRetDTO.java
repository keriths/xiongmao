package com.xm.service.apiimpl.pc.cim.equipmentstatus.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by wangshuna on 2017/12/26.
 */
public class EquipmentThroughputDataRetDTO extends BaseRetDTO{
    @ApiResultFieldDesc(desc = "返回数据列表")
    List<EquipmentThroughputData> equipmentThroughputDataList;

    public List<EquipmentThroughputData> getEquipmentThroughputDataList() {
        return equipmentThroughputDataList;
    }

    public void setEquipmentThroughputDataList(List<EquipmentThroughputData> equipmentThroughputDataList) {
        this.equipmentThroughputDataList = equipmentThroughputDataList;
    }
}
