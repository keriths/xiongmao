package com.xm.service.apiimpl.pc.cim.tactTime;

import com.xm.platform.annotations.ApiResultFieldDesc;

import java.io.Serializable;

/**
 * Created by luokaiming on 2017/11/13 0013.
 */
public class TactTimeData implements Serializable{
    @ApiResultFieldDesc(desc = "目标值")
    private Integer target;
    @ApiResultFieldDesc(desc = "实际值")
    private Integer actual;
    @ApiResultFieldDesc(desc = "横坐标时间")
    private String date;
    @ApiResultFieldDesc(desc = "产品类型")
    private String productType;


    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public Integer getActual() {
        return actual;
    }

    public void setActual(Integer actual) {
        this.actual = actual;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
