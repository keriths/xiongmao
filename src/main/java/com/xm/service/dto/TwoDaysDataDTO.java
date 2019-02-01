package com.xm.service.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.DateUtils;
import com.xm.service.constant.Constant;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fanshuai on 18/8/12.
 */
public class TwoDaysDataDTO implements Serializable{
    private BigDecimal beforTotalNum;
    private Date beforDataDate;
    private BigDecimal afterTotalNum;
    private Date afterDataDate;
    private List<TwoDaysDataDTO> detailList = new ArrayList<>();

    public List<TwoDaysDataDTO> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<TwoDaysDataDTO> detailList) {
        this.detailList = detailList;
    }

    @ApiResultFieldDesc(desc = "使用总量")
    private BigDecimal totalNum;
    @ApiResultFieldDesc(desc = "时间")
    private String dataDate;

    public void setTotalNum(BigDecimal totalNum) {
        this.totalNum = totalNum;
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
            return totalNum.setScale(0,BigDecimal.ROUND_HALF_UP);
        }
        if (afterTotalNum!=null && beforTotalNum!=null){
            totalNum = afterTotalNum.subtract(beforTotalNum);
            while (totalNum.intValue()<0){
                totalNum = totalNum.add(new BigDecimal("1000000000"));
            }
//            totalNum = totalNum.divide(new BigDecimal("10000"),1,BigDecimal.ROUND_HALF_UP);
            return totalNum.setScale(0,BigDecimal.ROUND_HALF_UP);
        }
        return BigDecimal.ZERO;
    }


    public String getDataDate() {
        return DateUtils.getStrDate(beforDataDate,"yyyyMMdd");
    }
}
