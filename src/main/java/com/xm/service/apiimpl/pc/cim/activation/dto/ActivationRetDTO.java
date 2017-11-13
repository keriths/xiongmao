package com.xm.service.apiimpl.pc.cim.activation.dto;

import com.xm.service.annotations.ApiResultFieldDesc;

import java.util.List;

/**
 * Created by fanshuai on 17/11/12.
 */
public class ActivationRetDTO {
    @ApiResultFieldDesc(desc = "EQP类型在各个状态(如PHOTO,PVD)不同时间点的状态值显示")
    List<ActivationDetailDTO> detailDTOList;

    public List<ActivationDetailDTO> getDetailDTOList() {
        return detailDTOList;
    }

    public void setDetailDTOList(List<ActivationDetailDTO> detailDTOList) {
        this.detailDTOList = detailDTOList;
    }
}
