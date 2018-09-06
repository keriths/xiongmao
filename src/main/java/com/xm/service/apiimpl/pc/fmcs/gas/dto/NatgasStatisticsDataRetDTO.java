package com.xm.service.apiimpl.pc.fmcs.gas.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.DateUtils;
import com.xm.platform.util.RandomUtils;
import com.xm.service.constant.Constant;
import com.xm.service.dto.BaseRetDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by luokaiming on 2017/11/30 0030.
 */
public class NatgasStatisticsDataRetDTO extends BaseRetDTO {
    @ApiResultFieldDesc(desc = "气体统计数据列表")
    private List<GsaStatisticsData> dataList;

    public static class GsaStatisticsData implements Serializable{
        boolean showDemoData = false;
//        public GsaStatisticsData(){}
//        public GsaStatisticsData(String periodDate){
//            this.periodDate=periodDate;
//        }
        public GsaStatisticsData(String gasType,String dateType,Date today,BigDecimal todayNum,Date tomorrow,BigDecimal tomorrowNum){
            this.gasType = gasType;
            this.dateType = dateType;
            this.today =today;
            this.todayNum = todayNum;
            this.tomorrow = tomorrow;
            this.tomorrowNum = tomorrowNum;
        }

        @ApiResultFieldDesc(desc = "使用量")
        private BigDecimal totalNum;
        @ApiResultFieldDesc(desc = "横坐标时间")
        private String periodDate;

        @ApiResultFieldDesc(desc = "气体类型")
        private String gasType;
        @ApiResultFieldDesc(desc = "查询类型")
        private String dateType;
        @ApiResultFieldDesc(desc = "当前数据0点时间")
        private Date today;
        @ApiResultFieldDesc(desc = "当前数据第二天0点时间")
        private Date tomorrow;
        @ApiResultFieldDesc(desc = "当前数据0点时的总量")
        private BigDecimal todayNum;
        @ApiResultFieldDesc(desc = "当前数据第二天0点时的总量")
        private BigDecimal tomorrowNum;


        public String getGasType() {
            return gasType;
        }

        public void setGasType(String gasType) {
            this.gasType = gasType;
        }

        public BigDecimal getTotalNum() {
            if (totalNum!=null){
                return totalNum;
            }
            if (todayNum!=null && tomorrowNum!=null){
                totalNum = tomorrowNum.subtract(todayNum);
                while (totalNum.intValue()<0){
                    totalNum = totalNum.add(new BigDecimal("1000000000"));
                }
                return totalNum;
            }
            if (showDemoData){
                totalNum = RandomUtils.randomIntBigDecimal(1000, 3000);
            }else {
                totalNum=new BigDecimal(0);
            }
            return totalNum;
        }

        public String getPeriodDate() {
            if (periodDate!=null){
                return periodDate;
            }
            if (dateType.equals(Constant.day)){
                periodDate=DateUtils.getStrDate(today, "MM/dd");
                return periodDate;
            }
            if (dateType.equals(Constant.month)){
                periodDate = DateUtils.getStrDate(today,"MM月");
                return periodDate;
            }
            return periodDate;
        }
    }

    public List<GsaStatisticsData> getDataList() {
        return dataList;
    }

    public void setDataList(List<GsaStatisticsData> dataList) {
        this.dataList = dataList;
    }
}
