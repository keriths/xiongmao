package com.xm.service.apiimpl.pc.fmcs.gas.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;

/**
 * Created by luokaiming on 2017/12/7 0007.
 */
public class NatgasAddressData {
    @ApiResultFieldDesc(desc = "地址名称")
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
