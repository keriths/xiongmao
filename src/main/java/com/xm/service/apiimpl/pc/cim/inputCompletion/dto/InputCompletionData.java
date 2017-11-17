package com.xm.service.apiimpl.pc.cim.inputCompletion.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * Created by fanshuai on 17/11/12.
 */
public class InputCompletionData implements Serializable{
    @ApiResultFieldDesc(desc = "厂别")
    private String factory;
    @ApiResultFieldDesc(desc = "计划")
    private Integer plan;
    @ApiResultFieldDesc(desc = "实际")
    private Integer actual;
    @ApiResultFieldDesc(desc = "达成率小数")
    private Double completionRate;
    @ApiResultFieldDesc(desc = "横坐标时间")
    private String dateTime;

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public Integer getPlan() {
        return plan;
    }

    public void setPlan(Integer plan) {
        this.plan = plan;
    }

    public Integer getActual() {
        return actual;
    }

    public void setActual(Integer actual) {
        this.actual = actual;
    }

    public Double getCompletionRate() {

        Integer a = this.plan;
        Integer b = this.actual;
        /*DecimalFormat df=new DecimalFormat("0.00");
        Double completionRate = Double.valueOf(df.format((float)a/b)).doubleValue();*/
        double completionRate = new BigDecimal((float)a/b).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
        return completionRate;

    }

    public void setCompletionRate(Double completionRate) {
        this.completionRate = completionRate;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
