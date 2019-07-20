package com.xm.service.apiimpl.pc.cim.goodinprocess.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.RandomUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by luokaiming on 2017/11/22.
 */
public class GoodInProcessWipDataDTO implements Serializable{
    @ApiResultFieldDesc(desc = "返回数据详情列表")
    private List<GoodInProcessWipDetailData> wipDetailDataList;

    /*@ApiResultFieldDesc(desc = "站点ID")
    private String setepId;*/
    @ApiResultFieldDesc(desc = "横坐标时间")
    private String dataDate;
    @ApiResultFieldDesc(desc = "所有厂别在库量上限汇总")
    private BigDecimal storeMaxSum;
    @ApiResultFieldDesc(desc = "所有厂别在库量下限汇总")
    private BigDecimal storeMinSum;


    public static class GoodInProcessWipDetailData implements Serializable{
        boolean showDemoData=false;
        public GoodInProcessWipDetailData(){};
        /*public GoodInProcessWipDetailData(String stepId,String factory){
            this.stepId=stepId;
            this.factory=factory;
        }*/
        public GoodInProcessWipDetailData(String dataDate,String factory){
            this.dataDate=dataDate;
            this.factory=factory;
        }
        public String key(){
            return dataDate+" "+factory;
        }

        @ApiResultFieldDesc(desc = "厂别,如ARRAY,CELL,CF,SL-OC")
        private String factory;
        /*@ApiResultFieldDesc(desc = "站点ID")
        private String stepId;*/
        @ApiResultFieldDesc(desc = "横坐标时间")
        private String dataDate;
        @ApiResultFieldDesc(desc = "在制品数量")
        private BigDecimal quantity;
        @ApiResultFieldDesc(desc = "在库量上限")
        private BigDecimal storeMax;
        @ApiResultFieldDesc(desc = "在库量下限")
        private BigDecimal storeMin;

        public String getFactory() {
            return factory;
        }

        public void setFactory(String factory) {
            this.factory = factory;
        }

        public String getDataDate() {
            return dataDate;
        }

        public void setDataDate(String dataDate) {
            this.dataDate = dataDate;
        }

        public BigDecimal getQuantity() {
            if (quantity!=null){
                return quantity;
            }
            if (!showDemoData){
                return quantity = BigDecimal.ZERO;
            }
            if ("CF".equals(factory)){
                return quantity = new BigDecimal(RandomUtils.randomInt(1400,1600));
            }
            return quantity = new BigDecimal(RandomUtils.randomInt(2300,2500));
        }

        public void setQuantity(BigDecimal quantity) {
            this.quantity = quantity;
        }

        public BigDecimal getStoreMax() {
            if (storeMax!=null){
                return storeMax;
            }
            return storeMax = getQuantity().multiply(new BigDecimal("1.1")).setScale(0,BigDecimal.ROUND_HALF_UP);
        }

        public void setStoreMax(BigDecimal storeMax) {
            this.storeMax = storeMax;
        }

        public BigDecimal getStoreMin() {
            if (storeMin!=null){
                return storeMin;
            }
            return storeMin = getQuantity().multiply(new BigDecimal("0.9")).setScale(0,BigDecimal.ROUND_HALF_UP);
        }

        public void setStoreMin(BigDecimal storeMin) {
            this.storeMin = storeMin;
        }
    }

    public List<GoodInProcessWipDetailData> getWipDetailDataList() {
        return wipDetailDataList;
    }

    public void setWipDetailDataList(List<GoodInProcessWipDetailData> wipDetailDataList) {
        this.wipDetailDataList = wipDetailDataList;
    }

    public String getDataDate() {
        return dataDate;
    }

    public void setDataDate(String dataDate) {
        this.dataDate = dataDate;
    }

    public BigDecimal getStoreMaxSum() {
        BigDecimal storeMaxSum=new BigDecimal(0);
        List<GoodInProcessWipDetailData> wipDetailDataList=getWipDetailDataList();
        for (GoodInProcessWipDetailData detailData:wipDetailDataList){
            BigDecimal storeMax=detailData.getStoreMax();
            storeMaxSum=storeMaxSum.add(storeMax);
        }
        return storeMaxSum;
    }

    public void setStoreMaxSum(BigDecimal storeMaxSum) {
        this.storeMaxSum = storeMaxSum;
    }

    public BigDecimal getStoreMinSum() {
        BigDecimal storeMinSum=new BigDecimal(0);
        List<GoodInProcessWipDetailData> wipDetailDataList=getWipDetailDataList();
        for (GoodInProcessWipDetailData detailData:wipDetailDataList){
            BigDecimal storeMax=detailData.getStoreMin();
            storeMinSum=storeMinSum.add(storeMax);
        }
        return storeMinSum;
    }

    public void setStoreMinSum(BigDecimal storeMinSum) {
        this.storeMinSum = storeMinSum;
    }
}
