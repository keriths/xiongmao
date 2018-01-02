package com.xm.service.apiimpl.pc.fmcs.upw.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.ReturnDataUtils;
import com.xm.service.constant.Constant;

import java.math.BigDecimal;

/**
 * Created by luokaiming on 2017/12/21 0018.
 * 纯水制造系统 设备状态、温度、电阻率
 */
public class UpwbData {

    @ApiResultFieldDesc(desc = "设备编号")
    private String code;

    @ApiResultFieldDesc(desc = "设备状态值")
    private BigDecimal status;

    @ApiResultFieldDesc(desc = "温度")
    private BigDecimal temperature;

    @ApiResultFieldDesc(desc = "电阻率")
    private BigDecimal resistivity;

    @ApiResultFieldDesc(desc = "颗粒")
    private BigDecimal particle;

    @ApiResultFieldDesc(desc = "TOC")
    private BigDecimal toc;

    @ApiResultFieldDesc(desc = "数据时间")
    private String DataDate;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getStatus() {
        return status;
    }

    public void setStatus(BigDecimal status) {
        this.status = status;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    public BigDecimal getResistivity() {
        return resistivity;
    }

    public void setResistivity(BigDecimal resistivity) {
        this.resistivity = resistivity;
    }

    public String getDataDate() {
        return DataDate;
    }

    public void setDataDate(String dataDate) {
        DataDate = dataDate;
    }

    public BigDecimal getParticle() {
        return particle;
    }

    public void setParticle(BigDecimal particle) {
        this.particle = particle;
    }

    public BigDecimal getToc() {
        return toc;
    }

    public void setToc(BigDecimal toc) {
        this.toc = toc;
    }
}
