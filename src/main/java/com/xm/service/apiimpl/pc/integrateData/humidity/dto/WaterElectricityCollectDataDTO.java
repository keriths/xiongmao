package com.xm.service.apiimpl.pc.integrateData.humidity.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.RandomUtils;
import com.xm.service.dto.BaseRetDTO;
import com.xm.service.dto.TwoDaysDataDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wangshuna on 2018/3/6.
 */
public class WaterElectricityCollectDataDTO extends BaseRetDTO{

    @ApiResultFieldDesc(desc="市政府自来水今日数据返回")
    private List<TwoDaysDataDTO> tapWaterDayDataList;
    @ApiResultFieldDesc(desc="市政府自来水本月数据返回")
    private List<TwoDaysDataDTO> tapWaterMonthDataList;

    @ApiResultFieldDesc(desc="纯水今日数据返回")
    private List<TwoDaysDataDTO> pureWaterDayDataList;
    @ApiResultFieldDesc(desc="纯水本月数据返回")
    private List<TwoDaysDataDTO> pureWaterMonthDataList;

    @ApiResultFieldDesc(desc="冷冻水今日数据返回")
    private List<TwoDaysDataDTO> freezeWaterDayDataList;
    @ApiResultFieldDesc(desc="冷冻水本月数据返回")
    private List<TwoDaysDataDTO> freezeWaterMonthDataList;

    @ApiResultFieldDesc(desc="电今日数据返回")
    private List<TwoDaysDataDTO> electDayDataList;
    @ApiResultFieldDesc(desc="电本月数据返回")
    private List<TwoDaysDataDTO> electMonthDataList;

    public List<TwoDaysDataDTO> getTapWaterDayDataList() {
        return tapWaterDayDataList;
    }

    public void setTapWaterDayDataList(List<TwoDaysDataDTO> tapWaterDayDataList) {
        this.tapWaterDayDataList = tapWaterDayDataList;
    }

    public List<TwoDaysDataDTO> getTapWaterMonthDataList() {
        return tapWaterMonthDataList;
    }

    public void setTapWaterMonthDataList(List<TwoDaysDataDTO> tapWaterMonthDataList) {
        this.tapWaterMonthDataList = tapWaterMonthDataList;
    }

    public List<TwoDaysDataDTO> getPureWaterDayDataList() {
        return pureWaterDayDataList;
    }

    public void setPureWaterDayDataList(List<TwoDaysDataDTO> pureWaterDayDataList) {
        this.pureWaterDayDataList = pureWaterDayDataList;
    }

    public List<TwoDaysDataDTO> getPureWaterMonthDataList() {
        return pureWaterMonthDataList;
    }

    public void setPureWaterMonthDataList(List<TwoDaysDataDTO> pureWaterMonthDataList) {
        this.pureWaterMonthDataList = pureWaterMonthDataList;
    }

    public List<TwoDaysDataDTO> getFreezeWaterDayDataList() {
        return freezeWaterDayDataList;
    }

    public void setFreezeWaterDayDataList(List<TwoDaysDataDTO> freezeWaterDayDataList) {
        this.freezeWaterDayDataList = freezeWaterDayDataList;
    }

    public List<TwoDaysDataDTO> getFreezeWaterMonthDataList() {
        return freezeWaterMonthDataList;
    }

    public void setFreezeWaterMonthDataList(List<TwoDaysDataDTO> freezeWaterMonthDataList) {
        this.freezeWaterMonthDataList = freezeWaterMonthDataList;
    }

    public List<TwoDaysDataDTO> getElectDayDataList() {
        return electDayDataList;
    }

    public void setElectDayDataList(List<TwoDaysDataDTO> electDayDataList) {
        this.electDayDataList = electDayDataList;
    }

    public List<TwoDaysDataDTO> getElectMonthDataList() {
        return electMonthDataList;
    }

    public void setElectMonthDataList(List<TwoDaysDataDTO> electMonthDataList) {
        this.electMonthDataList = electMonthDataList;
    }
}
