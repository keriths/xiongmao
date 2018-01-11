package com.xm.service.apiimpl.pc.fmcs.upw.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.RandomUtils;
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
        BigDecimal status=new BigDecimal(1);
        return status;
    }

    public void setStatus(BigDecimal status) {
        this.status = status;
    }

    public BigDecimal getTemperature() {
        BigDecimal temperature= new BigDecimal(RandomUtils.randomInt(15,25));
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    public BigDecimal getResistivity() {
        BigDecimal resistivity=RandomUtils.randomFloat(0.02f,0.04f,2);
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
        BigDecimal particle=RandomUtils.randomFloat(0.4f,1.3f,1);
        return particle;
    }

    public void setParticle(BigDecimal particle) {
        this.particle = particle;
    }

    public BigDecimal getToc() {
        BigDecimal toc=new BigDecimal(RandomUtils.randomInt(30,50));
        return toc;
    }

    public void setToc(BigDecimal toc) {
        this.toc = toc;
    }
}
