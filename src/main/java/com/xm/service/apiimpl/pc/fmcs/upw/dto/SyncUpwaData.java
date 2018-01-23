package com.xm.service.apiimpl.pc.fmcs.upw.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.ReturnDataUtils;
import com.xm.service.constant.Constant;

import java.util.Date;

/**
 * Created by luokaiming on 2017/12/21 0018.
 * 纯水制造系统 设备状态
 */
public class SyncUpwaData {

    @ApiResultFieldDesc(desc = "设备名称")
    private String key;

    @ApiResultFieldDesc(desc = "设备状态值")
    private String value;

    @ApiResultFieldDesc(desc = "数据时间")
    private Date dataDate;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getDataDate() {
        return dataDate;
    }

    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }
}
