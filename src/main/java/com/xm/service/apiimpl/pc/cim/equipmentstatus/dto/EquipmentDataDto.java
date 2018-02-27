package com.xm.service.apiimpl.pc.cim.equipmentstatus.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.ReturnDataUtils;
import com.xm.service.constant.Constant;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wangshuna on 2018/2/27.
 */
public class EquipmentDataDto {

    @ApiResultFieldDesc(desc = "设备数据列表")
    private List<EquipmentData> equipmentDataList;
    @ApiResultFieldDesc(desc = "设备总数")
    BigDecimal eqptTotalNum;
    @ApiResultFieldDesc(desc = "PM设备数")
    BigDecimal pmNum;
    @ApiResultFieldDesc(desc = "可稼动设备数")
    BigDecimal oeeNum;
    @ApiResultFieldDesc(desc = "故障中的设备数")
    BigDecimal failNum;
    @ApiResultFieldDesc(desc = "厂别")
    private String factory;


    public static class EquipmentData implements Serializable {

        @ApiResultFieldDesc(desc = "厂别")
        private String factory;
        @ApiResultFieldDesc(desc = "设备状态值(MAN手动操作,WAT等待;RUN生产中,TRB故障,MNT维护保养)")
        private String val;
        @ApiResultFieldDesc(desc = "某个工厂某个状态的数量")
        private String count;

        public String getFactory() {
            return factory;
        }

        public void setFactory(String factory) {
            this.factory = factory;
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }
    }

    public List<EquipmentData> getEquipmentDataList() {
        return equipmentDataList;
    }

    public void setEquipmentDataList(List<EquipmentData> equipmentDataList) {
        this.equipmentDataList = equipmentDataList;
        mathNum();
    }

    public BigDecimal getEqptTotalNum() {
        return eqptTotalNum;
    }


    public BigDecimal getPmNum() {
        return pmNum;
    }


    public BigDecimal getOeeNum() {
        return oeeNum;
    }


    public BigDecimal getFailNum() {
        return failNum;
    }

    public String getFactory() {
        for (EquipmentData data:equipmentDataList){
            factory = data.getFactory();
        }
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public void mathNum(){
        eqptTotalNum=new BigDecimal("0");
        pmNum=new BigDecimal("0");
        oeeNum=new BigDecimal("0");
        failNum=new BigDecimal("0");
        if (CollectionUtils.isEmpty(equipmentDataList)){
            return;
        }
        eqptTotalNum=new BigDecimal(equipmentDataList.size());
        for (EquipmentData data:equipmentDataList){
            if ("TRB".equals(data.getVal())){
                failNum = failNum.add(BigDecimal.ONE);
            }
            if ("RUN".equals(data.getVal())|| "WAT".equals(data.getVal())){
                oeeNum=oeeNum.add(BigDecimal.ONE);
            }
            if ("MAN".equals(data.getVal())){
                pmNum=pmNum.add(BigDecimal.ONE);
            }
        }
    }
}
