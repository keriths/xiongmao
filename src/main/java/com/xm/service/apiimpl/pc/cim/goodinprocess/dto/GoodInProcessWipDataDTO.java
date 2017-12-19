package com.xm.service.apiimpl.pc.cim.goodinprocess.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.RandomUtils;
import com.xm.service.constant.Constant;
import com.xm.service.dto.BaseRetDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by luokaiming on 2017/11/22.
 */
public class GoodInProcessWipDataDTO{
    @ApiResultFieldDesc(desc = "返回数据详情列表")
    private List<GoodInProcessWipDetailData> wipDetailDataList;

    @ApiResultFieldDesc(desc = "站点ID")
    private String setepId;
    @ApiResultFieldDesc(desc = "所有厂别在库量上限汇总")
    private BigDecimal storeMaxSum;
    @ApiResultFieldDesc(desc = "所有厂别在库量下限汇总")
    private BigDecimal storeMinSum;


    public static class GoodInProcessWipDetailData{

        public GoodInProcessWipDetailData(){};
        public GoodInProcessWipDetailData(String stepId,String factory){
            this.stepId=stepId;
            this.factory=factory;
        }
        public String key(){
            return stepId+" "+factory;
        }

        @ApiResultFieldDesc(desc = "厂别,如Array,Cell")
        private String factory;
        @ApiResultFieldDesc(desc = "站点ID")
        private String stepId;
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

        public String getSetepId() {
            return stepId;
        }

        public void setSetepId(String setepId) {
            this.stepId = setepId;
        }

        public BigDecimal getQuantity() {
            if (quantity==null){
                return new BigDecimal("0");
            }
            return quantity;
        }

        public void setQuantity(BigDecimal quantity) {
            this.quantity = quantity;
        }

        public BigDecimal getStoreMax() {
            if (storeMax==null){
                if (Constant.showDemoData){
                    return new BigDecimal(RandomUtils.randomInt(4000,6000));
                }else {
                    return new BigDecimal("0");
                }

            }
            return storeMax;
        }

        public void setStoreMax(BigDecimal storeMax) {
            this.storeMax = storeMax;
        }

        public BigDecimal getStoreMin() {
            if (storeMin==null){
                if (Constant.showDemoData){
                    return new BigDecimal(RandomUtils.randomInt(1000,1500));
                }else {
                    return new BigDecimal("0");
                }

            }
            return storeMin;
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

    public String getSetepId() {
        return setepId;
    }

    public void setSetepId(String setepId) {
        this.setepId = setepId;
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
