package com.xm.service.dto;

import com.xm.platform.util.DateUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by fanshuai on 18/8/12.
 */
public class DayDataDTO implements Serializable{
    private BigDecimal totalNum;
    private Date dataDate;

    public BigDecimal getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(BigDecimal totalNum) {
        this.totalNum = totalNum;
    }

    public Date getDataDate() {
        return dataDate;
    }

    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }

    public String getDatadateStr(){
        return DateUtils.getStrDate(dataDate, "yyyy-MM-dd HH:mm:ss");
    }
}
