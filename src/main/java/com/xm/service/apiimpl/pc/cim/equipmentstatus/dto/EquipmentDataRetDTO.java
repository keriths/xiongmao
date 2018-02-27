package com.xm.service.apiimpl.pc.cim.equipmentstatus.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by wangshuna on 2018/2/27.
 */
public class EquipmentDataRetDTO extends BaseRetDTO {
    @ApiResultFieldDesc(desc = "返回数据列表")
    List<EquipmentDataDto> equipmentDataList;

    public List<EquipmentDataDto> getEquipmentDataList() {
        return equipmentDataList;
    }

    public void setEquipmentDataList(List<EquipmentDataDto> equipmentDataList) {
        this.equipmentDataList = equipmentDataList;
    }

    /*@ApiResultFieldDesc(desc = "返回数据列表")
    List<EquipmentStatusData> equipmentDataList;

    public List<EquipmentStatusData> getEquipmentDataList() {
        return equipmentDataList;
    }

    public void setEquipmentDataList(List<EquipmentStatusData> equipmentDataList) {
        this.equipmentDataList = equipmentDataList;
    }*/
}
