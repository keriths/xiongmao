package com.xm.service.apiimpl.pc.cim.activation.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by fanshuai on 17/11/12.
 */
public class ActivationDetailDTO implements Serializable{
    @ApiResultFieldDesc(desc = "EQP类型的状态,如PHOTO,PVD,CVD,WET,DE")
    private String status;
    @ApiResultFieldDesc(desc = "EQP状态累计时间")
    private BigDecimal statusNum;
    @ApiResultFieldDesc(desc = "厂别,如Array,Cell,CF,SL-OC")
    private String factory;
    @ApiResultFieldDesc(desc = "EQP类型,如RUN,TRB,WAIT,MAN,MNT")
    private String eqpType;
    @ApiResultFieldDesc(desc = "EQP类型在各个状态(如PHOTO,PVD)的状态值显示")
    public List<ActivationStatusDTO> statusDTOList;
    @ApiResultFieldDesc(desc = "目标值")
    private Integer target=new Integer(0);
    @ApiResultFieldDesc(desc = "稼动率小数")
    private BigDecimal activation;
    @ApiResultFieldDesc(desc = "数据写入时间")
    private String dateTime;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getStatusNum() {
        return statusNum;
    }

    public void setStatusNum(BigDecimal statusNum) {
        this.statusNum = statusNum;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getEqpType() {
        return eqpType;
    }

    public void setEqpType(String eqpType) {
        this.eqpType = eqpType;
    }

    public List<ActivationStatusDTO> getStatusDTOList() {
        return statusDTOList;
    }

    public void setStatusDTOList(List<ActivationStatusDTO> statusDTOList) {
        this.statusDTOList = statusDTOList;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public BigDecimal getActivation() {
        return activation;
    }

    public void setActivation(BigDecimal activation) {
        this.activation = activation;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
