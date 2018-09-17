package com.xm.service.apiimpl.pc.integrateData.humidity.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.RandomUtils;
import com.xm.service.dto.BaseRetDTO;
import com.xm.service.dto.TwoDaysGasDataDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wangshuna on 2018/3/6.
 */
public class GasCollectDataDTO extends BaseRetDTO{

    @ApiResultFieldDesc(desc="大宗气今日数据返回")
    private List<TwoDaysGasDataDTO> bigGasDayCollectDataList;
    @ApiResultFieldDesc(desc="大宗气本月数据返回")
    private List<TwoDaysGasDataDTO> bigGasMonthCollectDataList;
    @ApiResultFieldDesc(desc="蒸汽,天然气今日数据返回")
    private List<TwoDaysGasDataDTO> natGasDayCollectDataList;
    @ApiResultFieldDesc(desc="蒸汽,天然气本月数据返回")
    private List<TwoDaysGasDataDTO> natGasMonthCollectDataList;

    public List<TwoDaysGasDataDTO> getBigGasDayCollectDataList() {
        return bigGasDayCollectDataList;
    }

    public void setBigGasDayCollectDataList(List<TwoDaysGasDataDTO> bigGasDayCollectDataList) {
        this.bigGasDayCollectDataList = bigGasDayCollectDataList;
    }

    public List<TwoDaysGasDataDTO> getBigGasMonthCollectDataList() {
        return bigGasMonthCollectDataList;
    }

    public void setBigGasMonthCollectDataList(List<TwoDaysGasDataDTO> bigGasMonthCollectDataList) {
        this.bigGasMonthCollectDataList = bigGasMonthCollectDataList;
    }

    public List<TwoDaysGasDataDTO> getNatGasDayCollectDataList() {
        return natGasDayCollectDataList;
    }

    public void setNatGasDayCollectDataList(List<TwoDaysGasDataDTO> natGasDayCollectDataList) {
        this.natGasDayCollectDataList = natGasDayCollectDataList;
    }

    public List<TwoDaysGasDataDTO> getNatGasMonthCollectDataList() {
        return natGasMonthCollectDataList;
    }

    public void setNatGasMonthCollectDataList(List<TwoDaysGasDataDTO> natGasMonthCollectDataList) {
        this.natGasMonthCollectDataList = natGasMonthCollectDataList;
    }

}
