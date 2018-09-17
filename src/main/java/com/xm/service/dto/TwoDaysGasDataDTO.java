package com.xm.service.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.DateUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by fanshuai on 18/8/12.
 */
public class TwoDaysGasDataDTO implements Serializable{
    @ApiResultFieldDesc(desc = "气体类型")
    private String gasType;
    @ApiResultFieldDesc(desc = "气体名称")
    private String gasName;
    private BigDecimal beforTotalNum;
    private Date beforDataDate;
    private BigDecimal afterTotalNum;
    private Date afterDataDate;

    @ApiResultFieldDesc(desc = "使用总量")
    private BigDecimal totalNum;
    @ApiResultFieldDesc(desc = "时间")
    private String dataDate;

    public String getGasName() {
        return gasName;
    }

    public void setGasName(String gasName) {
        this.gasName = gasName;
    }

    public String getGasType() {
        return gasType;
    }

    public void setGasType(String gasType) {
        this.gasType = gasType;
    }

    public BigDecimal getBeforTotalNum() {
        return beforTotalNum;
    }

    public void setBeforTotalNum(BigDecimal beforTotalNum) {
        this.beforTotalNum = beforTotalNum;
    }

    public Date getBeforDataDate() {
        return beforDataDate;
    }

    public void setBeforDataDate(Date beforDataDate) {
        this.beforDataDate = beforDataDate;
    }

    public BigDecimal getAfterTotalNum() {
        return afterTotalNum;
    }

    public void setAfterTotalNum(BigDecimal afterTotalNum) {
        this.afterTotalNum = afterTotalNum;
    }

    public Date getAfterDataDate() {
        return afterDataDate;
    }

    public void setAfterDataDate(Date afterDataDate) {
        this.afterDataDate = afterDataDate;
    }

    public BigDecimal getTotalNum() {
        if (totalNum!=null){
            return totalNum;
        }
        if (afterTotalNum!=null && beforTotalNum!=null){
            totalNum = afterTotalNum.subtract(beforTotalNum);
            while (totalNum.intValue()<0){
                totalNum = totalNum.add(new BigDecimal("1000000000"));
            }
            return totalNum;
        }
        return BigDecimal.ZERO;
    }


    public String getDataDate() {
        return DateUtils.getStrDate(beforDataDate,"yyyyMMdd");
    }
}
