package com.xm.service.apiimpl.pc.fmcs.gas.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;

import java.util.List;

/**
 * Created by luokaiming on 2017/12/7 0007.
 */
public class NatgasAddressRetDTO {
    @ApiResultFieldDesc(desc = "地址数据列表")
    private List<NatgasAddressData> natgasAddressDataList;

    public List<NatgasAddressData> getNatgasAddressDataList() {
        return natgasAddressDataList;
    }

    public void setNatgasAddressDataList(List<NatgasAddressData> natgasAddressDataList) {
        this.natgasAddressDataList = natgasAddressDataList;
    }
}
