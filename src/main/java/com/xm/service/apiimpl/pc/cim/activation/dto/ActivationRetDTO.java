package com.xm.service.apiimpl.pc.cim.activation.dto;

import com.xm.service.annotations.ApiResultFieldDesc;

import java.util.List;

/**
 * Created by fanshuai on 17/11/12.
 */
public class ActivationRetDTO {
    @ApiResultFieldDesc(desc = "设备稼动显示如Array,Cell")
    List<ActivationDetailDTO> detailDTOList;

    public List<ActivationDetailDTO> getDetailDTOList() {
        return detailDTOList;
    }

    public void setDetailDTOList(List<ActivationDetailDTO> detailDTOList) {
        this.detailDTOList = detailDTOList;
    }
}
