package com.xm.service.apiimpl.pc.fmcs.exhaust.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.RandomUtils;
import com.xm.platform.util.ReturnDataUtils;
import com.xm.service.constant.Constant;

import java.math.BigDecimal;

/**
 * Created by wanghsuna on 2018/1/2.
 */
public class SyncExhaustAData {
    @ApiResultFieldDesc(desc = "系统名称")
    private String name;

    @ApiResultFieldDesc(desc = "变频选择(0就绪1自动2变频)")
    private String hz;

    @ApiResultFieldDesc(desc = "状态(0自动1启动2停止3复位)")
    private String status;

    @ApiResultFieldDesc(desc = "风机状态(0运行1停止)")
    private String fanStatus;

    @ApiResultFieldDesc(desc = "轴承温度1")
    private BigDecimal temperatureOne;

    @ApiResultFieldDesc(desc = "轴承温度2")
    private BigDecimal temperatureTwo;

    @ApiResultFieldDesc(desc = "风机电流")
    private BigDecimal electricity;

    @ApiResultFieldDesc(desc = "频率反馈")
    private BigDecimal frequencyFeedback;

    @ApiResultFieldDesc(desc = "手动频率给定")
    private BigDecimal frequencyHand;

    @ApiResultFieldDesc(desc = "数据更新时间")
    private String dataDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHz() {
        return hz;
    }

    public void setHz(String hz) {
        this.hz = hz;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFanStatus() {
        return fanStatus;
    }

    public void setFanStatus(String fanStatus) {
        this.fanStatus = fanStatus;
    }

    public BigDecimal getTemperatureOne() {
        return temperatureOne;
    }

    public void setTemperatureOne(BigDecimal temperatureOne) {
        this.temperatureOne = temperatureOne;
    }

    public BigDecimal getTemperatureTwo() {
        return temperatureTwo;
    }

    public void setTemperatureTwo(BigDecimal temperatureTwo) {
        this.temperatureTwo = temperatureTwo;
    }

    public BigDecimal getElectricity() {
        return electricity;
    }

    public void setElectricity(BigDecimal electricity) {
        this.electricity = electricity;
    }

    public BigDecimal getFrequencyFeedback() {
        return frequencyFeedback;
    }

    public void setFrequencyFeedback(BigDecimal frequencyFeedback) {
        this.frequencyFeedback = frequencyFeedback;
    }

    public BigDecimal getFrequencyHand() {
        return frequencyHand;
    }

    public void setFrequencyHand(BigDecimal frequencyHand) {
        this.frequencyHand = frequencyHand;
    }

    public String getDataDate() {
        return dataDate;
    }

    public void setDataDate(String dataDate) {
        this.dataDate = dataDate;
    }
}
