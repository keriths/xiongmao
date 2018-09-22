package com.xm.service.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by fanshuai on 18/8/26.
 */
public class KeyValueDTO implements Serializable{
    @ApiResultFieldDesc(desc = "图中的数据位置唯一key")
    public String key;
    @ApiResultFieldDesc(desc = "图中的数据位置的值")
    public String val;
    @ApiResultFieldDesc(desc = "key的描述")
    public String keyDesc;
    @ApiResultFieldDesc(desc = "数据产生的时间")
    public Date dataDate;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getVal() {
        return StringUtils.twoPoint(val);
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getKeyDesc() {
        return keyDesc;
    }

    public void setKeyDesc(String keyDesc) {
        this.keyDesc = keyDesc;
    }

    public Date getDataDate() {
        return dataDate;
    }

    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }
}
