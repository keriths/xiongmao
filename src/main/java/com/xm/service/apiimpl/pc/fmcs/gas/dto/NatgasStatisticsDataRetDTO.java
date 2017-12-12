package com.xm.service.apiimpl.pc.fmcs.gas.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by luokaiming on 2017/11/30 0030.
 */
public class NatgasStatisticsDataRetDTO extends BaseRetDTO {
    @ApiResultFieldDesc(desc = "气体统计数据列表")
    private List<GsaStatisticsData> dataList;

    public static class GsaStatisticsData implements Serializable{
        public GsaStatisticsData(){}
        public GsaStatisticsData(String periodDate){
            this.periodDate=periodDate;
        }
        @ApiResultFieldDesc(desc = "气体类型")
        private String gasType;

        @ApiResultFieldDesc(desc = "使用量")
        private BigDecimal totalNum;

        @ApiResultFieldDesc(desc = "横坐标时间")
        private String periodDate;


        public String getGasType() {
            return gasType;
        }

        public void setGasType(String gasType) {
            this.gasType = gasType;
        }

        public BigDecimal getTotalNum() {
            if(totalNum==null){
                totalNum=new BigDecimal(0);
            }
            return totalNum;
        }

        public void setTotalNum(BigDecimal totalNum) {
            this.totalNum = totalNum;
        }

        public String getPeriodDate() {
            return periodDate;
        }

        public void setPeriodDate(String periodDate) {
            this.periodDate = periodDate;
        }
    }

    public List<GsaStatisticsData> getDataList() {
        return dataList;
    }

    public void setDataList(List<GsaStatisticsData> dataList) {
        this.dataList = dataList;
    }
}
