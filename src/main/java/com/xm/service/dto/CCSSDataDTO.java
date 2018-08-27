package com.xm.service.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by fanshuai on 18/8/26.
 */
public class CCSSDataDTO implements Serializable{
    @ApiResultFieldDesc(desc = "图中的数据位置唯一key")
    public String key;
    @ApiResultFieldDesc(desc = "正常状态的数量")
    public BigDecimal runStatusNum;
    @ApiResultFieldDesc(desc = "异常状态的数量")
    public BigDecimal errorStatusNum;
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

    public BigDecimal getRunStatusNum() {
        return runStatusNum;
    }

    public void setRunStatusNum(BigDecimal runStatusNum) {
        this.runStatusNum = runStatusNum;
    }

    public BigDecimal getErrorStatusNum() {
        return errorStatusNum;
    }

    public void setErrorStatusNum(BigDecimal errorStatusNum) {
        this.errorStatusNum = errorStatusNum;
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
