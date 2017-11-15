package com.xm.service.apiimpl.pc.cim.activation.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;

import java.util.List;

/**
 * Created by fanshuai on 17/11/12.
 */
public class ActivationRetDTO {
    @ApiResultFieldDesc(desc = "设备稼动状态值显示如Array,Cell")
    List<ActivationDetailDTO> detailDateList;

    public List<ActivationDetailDTO> getDetailDTOList() {
        return detailDateList;
    }

    public void setDetailDTOList(List<ActivationDetailDTO> detailDTOList) {
        this.detailDateList = detailDTOList;
    }
}
