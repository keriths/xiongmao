package com.xm.service.apiimpl.pc.cim.Outputcompletion;

import com.xm.platform.annotations.ApiResultFieldDesc;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

import static java.lang.Double.valueOf;

/**
 * Created by luokaiming on 2017/11/13 0013.
 */
public class OutputCompletionData implements Serializable{
    @ApiResultFieldDesc(desc="厂别：如SL、")
    private String factory;
    @ApiResultFieldDesc(desc = "计划值")
    private Integer plan;
    @ApiResultFieldDesc(desc = "实际值")
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
        DecimalFormat df = new DecimalFormat("0.0000");//格式化小数

        Integer a=this.getPlan();
        Integer b=this.getActual();
        double completionRate = new BigDecimal((float)a/b).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
       // double completionRate=valueOf(df.format(a/b));
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
