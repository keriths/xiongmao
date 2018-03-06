package com.xm.service.apiimpl.pc.integrateData.humidity.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wangshuna on 2018/3/6.
 */
public class WaterElectricityCollectDataDTO extends BaseRetDTO{

    @ApiResultFieldDesc(desc="市政府自来水今日数据返回")
    private List<WaterElectricityCollectData> tapWaterDayDataList;
    @ApiResultFieldDesc(desc="市政府自来水本月数据返回")
    private List<WaterElectricityCollectData> tapWaterMonthDataList;

    @ApiResultFieldDesc(desc="纯水今日数据返回")
    private List<WaterElectricityCollectData> pureWaterDayDataList;
    @ApiResultFieldDesc(desc="纯水本月数据返回")
    private List<WaterElectricityCollectData> pureWaterMonthDataList;

    @ApiResultFieldDesc(desc="冷冻水今日数据返回")
    private List<WaterElectricityCollectData> freezeWaterDayDataList;
    @ApiResultFieldDesc(desc="冷冻水本月数据返回")
    private List<WaterElectricityCollectData> freezeWaterMonthDataList;

    @ApiResultFieldDesc(desc="电今日数据返回")
    private List<WaterElectricityCollectData> electDayDataList;
    @ApiResultFieldDesc(desc="电本月数据返回")
    private List<WaterElectricityCollectData> electMonthDataList;

    public static class WaterElectricityCollectData{
        @ApiResultFieldDesc(desc = "当天使用总量")
        private BigDecimal totalNum;
        @ApiResultFieldDesc(desc = "时间")
        private String dataDate;

        public BigDecimal getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(BigDecimal totalNum) {
            this.totalNum = totalNum;
        }

        public String getDataDate() {
            return dataDate;
        }

        public void setDataDate(String dataDate) {
            this.dataDate = dataDate;
        }
    }

    public List<WaterElectricityCollectData> getTapWaterDayDataList() {
        return tapWaterDayDataList;
    }

    public void setTapWaterDayDataList(List<WaterElectricityCollectData> tapWaterDayDataList) {
        this.tapWaterDayDataList = tapWaterDayDataList;
    }

    public List<WaterElectricityCollectData> getTapWaterMonthDataList() {
        return tapWaterMonthDataList;
    }

    public void setTapWaterMonthDataList(List<WaterElectricityCollectData> tapWaterMonthDataList) {
        this.tapWaterMonthDataList = tapWaterMonthDataList;
    }

    public List<WaterElectricityCollectData> getPureWaterDayDataList() {
        return pureWaterDayDataList;
    }

    public void setPureWaterDayDataList(List<WaterElectricityCollectData> pureWaterDayDataList) {
        this.pureWaterDayDataList = pureWaterDayDataList;
    }

    public List<WaterElectricityCollectData> getPureWaterMonthDataList() {
        return pureWaterMonthDataList;
    }

    public void setPureWaterMonthDataList(List<WaterElectricityCollectData> pureWaterMonthDataList) {
        this.pureWaterMonthDataList = pureWaterMonthDataList;
    }

    public List<WaterElectricityCollectData> getFreezeWaterDayDataList() {
        return freezeWaterDayDataList;
    }

    public void setFreezeWaterDayDataList(List<WaterElectricityCollectData> freezeWaterDayDataList) {
        this.freezeWaterDayDataList = freezeWaterDayDataList;
    }

    public List<WaterElectricityCollectData> getFreezeWaterMonthDataList() {
        return freezeWaterMonthDataList;
    }

    public void setFreezeWaterMonthDataList(List<WaterElectricityCollectData> freezeWaterMonthDataList) {
        this.freezeWaterMonthDataList = freezeWaterMonthDataList;
    }

    public List<WaterElectricityCollectData> getElectDayDataList() {
        return electDayDataList;
    }

    public void setElectDayDataList(List<WaterElectricityCollectData> electDayDataList) {
        this.electDayDataList = electDayDataList;
    }

    public List<WaterElectricityCollectData> getElectMonthDataList() {
        return electMonthDataList;
    }

    public void setElectMonthDataList(List<WaterElectricityCollectData> electMonthDataList) {
        this.electMonthDataList = electMonthDataList;
    }
}
