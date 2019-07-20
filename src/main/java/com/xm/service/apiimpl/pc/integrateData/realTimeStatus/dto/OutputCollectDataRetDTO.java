package com.xm.service.apiimpl.pc.integrateData.realTimeStatus.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.RandomUtils;
import com.xm.service.constant.Constant;
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
        boolean showDemoData=false;
        public CollectDataList(){}
        public CollectDataList(String productName,String periodDate){
            this.periodDate = periodDate;
            this.productName = productName;
        }

        @ApiResultFieldDesc(desc="产品名称")
        private String productName;
        @ApiResultFieldDesc(desc = "产出数")
        private BigDecimal outputNum;
        @ApiResultFieldDesc(desc = "时间")
        private String periodDate;

        public String getProductName() {
            String name = Constant.productIdNameMap.get(productName);
            if (name!=null){
                return name;
            }
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public BigDecimal getOutputNum() {
            if (outputNum==null){
                if (showDemoData){
                    outputNum = new BigDecimal(RandomUtils.randomInt(100, 200));
                }
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
