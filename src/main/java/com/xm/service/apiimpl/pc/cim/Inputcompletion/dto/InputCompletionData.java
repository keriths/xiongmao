package com.xm.service.apiimpl.pc.cim.Inputcompletion.dto;

import com.xm.service.annotations.ApiResultFieldDesc;

import java.io.Serializable;

/**
 * Created by fanshuai on 17/11/12.
 */
public class InputCompletionData implements Serializable{
    @ApiResultFieldDesc(desc = "计划")
    private Integer plan;
    @ApiResultFieldDesc(desc = "实际")
    private Integer actual;
    @ApiResultFieldDesc(desc = "达成率小数")
    private Double completionRate;
    @ApiResultFieldDesc(desc = "横坐标时间")
    private String date;
    @ApiResultFieldDesc(desc = "产品类型")
    private String producttype;

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
        return completionRate;
    }

    public void setCompletionRate(Double completionRate) {
        this.completionRate = completionRate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype;
    }
}
