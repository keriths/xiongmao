package com.xm.service.apiimpl.pc.cim.equipmentstatus.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.RandomUtils;
import com.xm.service.constant.Constant;

import java.math.BigDecimal;


/**
 * Created by wangshuna on 2017/12/26.
 */
public class EquipmentThroughputData {
    boolean showDemoData=false;
    public EquipmentThroughputData(){

    }
    public EquipmentThroughputData(String dataDate){
        this.dataDate = dataDate;
    }

    @ApiResultFieldDesc(desc = "厂别,如ARRAY,CELL,CF,SL-OC")
    private String factory;
    @ApiResultFieldDesc(desc = "过货量")
    private BigDecimal amount;
    @ApiResultFieldDesc(desc = "横坐标时间")
    private String dataDate;

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public BigDecimal getAmount() {
        if (amount == null) {
            if (showDemoData) {
                return RandomUtils.randomIntBigDecimal(3000, 6000);
            } else {
                return new BigDecimal("0");
            }
            //return new BigDecimal("0");
        }
        return amount.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDataDate() {
        return dataDate;
    }

    public void setDataDate(String dataDate) {
        this.dataDate = dataDate;
    }
}

