package com.xm.service.apiimpl.pc.cim.activation.dto;

import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by fanshuai on 17/11/12.
 */
public class ActivationDetailDTO implements Serializable{

    private Integer status;
    private Integer statusNum;

    private String factory;
    private String eqpType;
    private String dateTime;
    private List<ActivationStatusDTO> statusDTOList;
    private Integer target;
    private BigDecimal activation;

    public BigDecimal getActivation() {
        // TODO 稼动率实现
        if (CollectionUtils.isEmpty(statusDTOList)){
            return new BigDecimal("0");
        }
        BigDecimal t = new BigDecimal(getTarget());
        BigDecimal runStatusNum = getStatusNum("RUN");
        return  runStatusNum.divide(t).setScale(4,BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal getStatusNum(String run) {
        //TODO 实现
        return null;
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
}
