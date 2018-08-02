package com.xm.service.apiimpl.pc.fmcs.gas.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.RandomUtils;
import com.xm.service.constant.Constant;
import com.xm.service.dto.BaseRetDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wangshuna on 2017/12/4.
 */
public class BigGasStatisticsDateRetDTO extends BaseRetDTO{

    @ApiResultFieldDesc(desc = "大宗气体统计数据列表")
    private List<BigGasStatisticsDate> bigGasStatisticsDateList;

    public static class BigGasStatisticsDate implements Serializable{
        public BigGasStatisticsDate(){}
        public BigGasStatisticsDate(String periodDate){
            this.periodDate = periodDate;
        }

        @ApiResultFieldDesc(desc = "气体名称")
        private String gasName;
        @ApiResultFieldDesc(desc = "使用量")
        private BigDecimal totalNum;
        @ApiResultFieldDesc(desc = "横坐标时间")
        private String periodDate;

        public String getGasName() {
            return gasName;
        }

        public void setGasName(String gasName) {
            this.gasName = gasName;
        }

        public BigDecimal getTotalNum() {
            if (totalNum==null){
                if (Constant.showDemoData){
                    if("GN2".equals(getGasName())){
                        totalNum = (RandomUtils.randomFloat(80f,100f));
                    }else if ("PHe".equals(getGasName())){
                        totalNum = (RandomUtils.randomFloat(1.5f,2.5f));
                    }else if ("PN2".equals(getGasName()) || "PO2".equals(getGasName())){
                        totalNum = (RandomUtils.randomIntBigDecimal(300,500));
                    }else if ("PAr".equals(getGasName()) || "PH2".equals(getGasName())){
                        totalNum = (RandomUtils.randomIntBigDecimal(150,250));
                    }else{
                        totalNum = (RandomUtils.randomIntBigDecimal(3000,3900));
                    }

                }else {
                    totalNum=new BigDecimal(0);
                }
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

    public List<BigGasStatisticsDate> getBigGasStatisticsDateList() {
        return bigGasStatisticsDateList;
    }

    public void setBigGasStatisticsDateList(List<BigGasStatisticsDate> bigGasStatisticsDateList) {
        this.bigGasStatisticsDateList = bigGasStatisticsDateList;
    }
}
