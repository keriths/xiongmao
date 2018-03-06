package com.xm.service.apiimpl.pc.integrateData.realTimeStatus.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wangshuna on 2018/2/28.
 */
public class OutputCollectDataRetDTO extends BaseRetDTO {
    @ApiResultFieldDesc(desc="昨日产能数据返回")
    private List<CollectDataList> collectDayDataRetDTOList;

    @ApiResultFieldDesc(desc="本月产能数据返回")
    private List<CollectDataList> collectMonthDataRetDTOList;

    public static class CollectDataList{

        @ApiResultFieldDesc(desc="产品ID")
        private String productId;
        @ApiResultFieldDesc(desc = "产出数")
        private BigDecimal outputNum;
        @ApiResultFieldDesc(desc = "时间")
        private String periodDate;

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public BigDecimal getOutputNum() {
            /*if (outputNum==null){
                if (Constant.showDemoData){
                    outputNum = new BigDecimal(RandomUtils.randomInt(100,200));
                }
            }*/
            if (outputNum==null){
                outputNum = new BigDecimal(0);
            }
            return outputNum;
        }

        public void setOutputNum(BigDecimal outputNum) {
            this.outputNum = outputNum;
        }

        public String getPeriodDate() {
            return periodDate;
        }

        public void setPeriodDate(String periodDate) {
            this.periodDate = periodDate;
        }
    }

    public List<CollectDataList> getCollectDayDataRetDTOList() {
        return collectDayDataRetDTOList;
    }

    public void setCollectDayDataRetDTOList(List<CollectDataList> collectDayDataRetDTOList) {
        this.collectDayDataRetDTOList = collectDayDataRetDTOList;
    }

    public List<CollectDataList> getCollectMonthDataRetDTOList() {
        return collectMonthDataRetDTOList;
    }

    public void setCollectMonthDataRetDTOList(List<CollectDataList> collectMonthDataRetDTOList) {
        this.collectMonthDataRetDTOList = collectMonthDataRetDTOList;
    }
}
