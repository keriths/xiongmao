package com.xm.service.apiimpl.pc.fmcs.exhaust.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.ReturnDataUtils;
import com.xm.service.constant.Constant;

import java.util.Date;

/**
 * Created by wangshuna on 2018/1/2.
 */
public class SyncExhaustBData {

    @ApiResultFieldDesc(desc = "设备名称")
    private String key;
    @ApiResultFieldDesc(desc = "设备状态值")
    private String val;
    @ApiResultFieldDesc(desc = "数据时间")
    private Date dataDate;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public Date getDataDate() {
        return dataDate;
    }

    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }
}
