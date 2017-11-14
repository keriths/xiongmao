package com.xm.service.apiimpl.pc.cim.activation.dto;

import com.xm.service.annotations.ApiMethodDoc;
import com.xm.service.annotations.ApiResultFieldDesc;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by fanshuai on 17/11/12.
 */
public class ActivationDetailDTO implements Serializable{
    @ApiResultFieldDesc(desc = "EQP类型的状态,如PHOTO,PVD,CVD,WET,DE")
    private Integer status;
    @ApiResultFieldDesc(desc = "某个状态EQP类型的状态值")
    private BigDecimal statusNum;
    @ApiResultFieldDesc(desc = "厂别,如Array,Cell,CF,SL-OC")
    private String factory;
    @ApiResultFieldDesc(desc = "EQP类型,如RUN,TRB,WAIT,MAN,MNT")
    private String eqpType;
    @ApiResultFieldDesc(desc = "EQP类型在各个状态(如PHOTO,PVD)不同时间点的状态值显示")
    private List<ActivationStatusDTO> statusDTOList;
    @ApiResultFieldDesc(desc = "目标值")
    private Integer target;
    @ApiResultFieldDesc(desc = "稼动率小数")
    private BigDecimal activation;
    @ApiResultFieldDesc(desc = "每个状态的Tact Time")
    private Integer tactTime;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    private BigDecimal getStatusNum(String run) {
        //TODO 实现
        return statusNum;
    }

    public void setStatusNum(BigDecimal statusNum) {
        this.statusNum = statusNum;
    }

    public BigDecimal getActivation() {
       // TODO 稼动率实现
        if (CollectionUtils.isEmpty(statusDTOList)){
            return new BigDecimal("0");
        }
        BigDecimal t = new BigDecimal(getTarget());
        BigDecimal runStatusNum = getStatusNum("RUN");
        return  runStatusNum.divide(t).setScale(4,BigDecimal.ROUND_HALF_UP);
       //return activation;
    }

    public void setActivation(BigDecimal activation) {
        this.activation = activation;
    }



    public Integer getTarget() {
        if (target!=null){
            return target;
        }
        if (CollectionUtils.isEmpty(statusDTOList)){
            target=0;
            return target;
        }
        Integer t = 0;
        for (ActivationStatusDTO statusDTO:statusDTOList){
            t+=statusDTO.getStatusNum();
        }
        target=t;
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
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

    public Integer getTactTime() {
        return tactTime;
    }

    public void setTactTime(Integer tactTime) {
        this.tactTime = tactTime;
    }
}
