package com.xm.service.apiimpl.pc.cim.equipmentstatus.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by wangshuna on 2017/12/26.
 */
public class EquipmentStatusDataRetDTO extends BaseRetDTO{

    @ApiResultFieldDesc(desc = "返回数据列表")
    List<EquipmentStatusData> equipmentStatusDataList;

    public List<EquipmentStatusData> getEquipmentStatusDataList() {
        return equipmentStatusDataList;
    }

    public void setEquipmentStatusDataList(List<EquipmentStatusData> equipmentStatusDataList) {
        this.equipmentStatusDataList = equipmentStatusDataList;
    }
}
