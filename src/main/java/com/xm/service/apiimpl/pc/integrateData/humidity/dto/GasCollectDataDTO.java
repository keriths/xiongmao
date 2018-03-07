package com.xm.service.apiimpl.pc.integrateData.humidity.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wangshuna on 2018/3/6.
 */
public class GasCollectDataDTO extends BaseRetDTO{

    @ApiResultFieldDesc(desc="大宗气今日数据返回")
    private List<GasCollectData> bigGasDayCollectDataList;
    @ApiResultFieldDesc(desc="大宗气本月数据返回")
    private List<GasCollectData> bigGasMonthCollectDataList;
    @ApiResultFieldDesc(desc="蒸汽,天然气今日数据返回")
    private List<GasCollectData> natGasDayCollectDataList;
    @ApiResultFieldDesc(desc="蒸汽,天然气本月数据返回")
    private List<GasCollectData> natGasMonthCollectDataList;

    public static class GasCollectData{

        @ApiResultFieldDesc(desc = "气体类型")
        private String gasType;
        @ApiResultFieldDesc(desc = "气体名称")
        private String gasName;
        @ApiResultFieldDesc(desc = "使用量")
        private BigDecimal totalNum;
        @ApiResultFieldDesc(desc = "时间")
        private String dataDate;

        public String getGasType() {
            return gasType;
        }

        public void setGasType(String gasType) {
            this.gasType = gasType;
        }

        public String getGasName() {
            return gasName;
        }

        public void setGasName(String gasName) {
            this.gasName = gasName;
        }

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

    public List<GasCollectData> getBigGasDayCollectDataList() {
        return bigGasDayCollectDataList;
    }

    public void setBigGasDayCollectDataList(List<GasCollectData> bigGasDayCollectDataList) {
        this.bigGasDayCollectDataList = bigGasDayCollectDataList;
    }

    public List<GasCollectData> getBigGasMonthCollectDataList() {
        return bigGasMonthCollectDataList;
    }

    public void setBigGasMonthCollectDataList(List<GasCollectData> bigGasMonthCollectDataList) {
        this.bigGasMonthCollectDataList = bigGasMonthCollectDataList;
    }

    public List<GasCollectData> getNatGasDayCollectDataList() {
        return natGasDayCollectDataList;
    }

    public void setNatGasDayCollectDataList(List<GasCollectData> natGasDayCollectDataList) {
        this.natGasDayCollectDataList = natGasDayCollectDataList;
    }

    public List<GasCollectData> getNatGasMonthCollectDataList() {
        return natGasMonthCollectDataList;
    }

    public void setNatGasMonthCollectDataList(List<GasCollectData> natGasMonthCollectDataList) {
        this.natGasMonthCollectDataList = natGasMonthCollectDataList;
    }
}
