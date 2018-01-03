package com.xm.service.apiimpl.pc.fmcs.gas.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fanshuai on 18/1/3.
 */
public class BigGasDataDTO extends BaseRetDTO {
    @ApiResultFieldDesc(desc = "大宗气体实时数据列表")
    private List<BigGasRealTimeDate> bigGasRealTimeDateList;
    @ApiResultFieldDesc(desc = "大宗气体天统计数据列表")
    private List<BigGasStatisticsDateRetDTO.BigGasStatisticsDate> bigGasDayStatisticsDateList;
    @ApiResultFieldDesc(desc = "大宗气体月统计数据列表")
    private List<BigGasStatisticsDateRetDTO.BigGasStatisticsDate> bigGasMonthStatisticsDateList;

    public List<BigGasRealTimeDate> getBigGasRealTimeDateList() {
        return bigGasRealTimeDateList;
    }

    public void setBigGasRealTimeDateList(List<BigGasRealTimeDate> bigGasRealTimeDateList) {
        this.bigGasRealTimeDateList = bigGasRealTimeDateList;
    }

    public List<BigGasStatisticsDateRetDTO.BigGasStatisticsDate> getBigGasDayStatisticsDateList() {
        return bigGasDayStatisticsDateList;
    }

    public void setBigGasDayStatisticsDateList(List<BigGasStatisticsDateRetDTO.BigGasStatisticsDate> bigGasDayStatisticsDateList) {
        this.bigGasDayStatisticsDateList = bigGasDayStatisticsDateList;
    }

    public List<BigGasStatisticsDateRetDTO.BigGasStatisticsDate> getBigGasMonthStatisticsDateList() {
        return bigGasMonthStatisticsDateList;
    }

    public void setBigGasMonthStatisticsDateList(List<BigGasStatisticsDateRetDTO.BigGasStatisticsDate> bigGasMonthStatisticsDateList) {
        this.bigGasMonthStatisticsDateList = bigGasMonthStatisticsDateList;
    }
}
