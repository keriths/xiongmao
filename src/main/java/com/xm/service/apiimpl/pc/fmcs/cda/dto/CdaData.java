package com.xm.service.apiimpl.pc.fmcs.cda.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;

import java.math.BigDecimal;

/**
 * Created by wangshuna on 2017/12/18.
 */
public class CdaData {

    @ApiResultFieldDesc(desc = "设备名称")
    private String key;
    @ApiResultFieldDesc(desc = "设备状态值")
    private BigDecimal val;
    @ApiResultFieldDesc(desc = "设备数据说明")
    private String keyDesc;
    @ApiResultFieldDesc(desc = "数据更新时间")
    private String dataDate;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public BigDecimal getVal() {
        if(val == null){
            return new BigDecimal("0");
        }
        return val;
    }

    public void setVal(BigDecimal val) {
        this.val = val;
    }

    public String getKeyDesc() {
        return keyDesc;
    }

    public void setKeyDesc(String keyDesc) {
        this.keyDesc = keyDesc;
    }

    public String getDataDate() {
        return dataDate;
    }

    public void setDataDate(String dataDate) {
        this.dataDate = dataDate;
    }
}
