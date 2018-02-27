package com.xm.service.apiimpl.pc.cim.equipmentstatus.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dao.cim.DwrEquipmentStatusFidsDAO;
import com.xm.service.dto.BaseRetDTO;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Created by wangshuna on 2017/12/26.
 */
public class EquipmentStatusDataRetDTO extends BaseRetDTO{

    @ApiResultFieldDesc(desc = "返回数据列表")
    List<EquipmentStatusData> equipmentStatusDataList;
    @ApiResultFieldDesc(desc = "设备总数")
    BigDecimal eqptTotalNum;
    @ApiResultFieldDesc(desc = "PM设备")
    BigDecimal pmNum;
    @ApiResultFieldDesc(desc = "可稼动设备")
    BigDecimal oeeNum;
    @ApiResultFieldDesc(desc = "故障中的设备")
    BigDecimal failNum;
    @ApiResultFieldDesc(desc = "AMHS连接状态 1 0")
    Integer amhs;

    public List<EquipmentStatusData> getEquipmentStatusDataList() {
        return equipmentStatusDataList;
    }

    public void setEquipmentStatusDataList(List<EquipmentStatusData> equipmentStatusDataList) {
        this.equipmentStatusDataList = equipmentStatusDataList;
    }

    /*public Integer getEqptTotalNum() {
        if (CollectionUtils.isEmpty(equipmentStatusDataList)){
            return 0;
        }
        return equipmentStatusDataList.size();
    }*/

    public BigDecimal getEqptTotalNum() {
        eqptTotalNum = new BigDecimal(0);
        for (EquipmentStatusData data:equipmentStatusDataList){
            eqptTotalNum=eqptTotalNum.add(new BigDecimal(1));
        }
        return eqptTotalNum;
    }

    public BigDecimal getPmNum() {
        pmNum = new BigDecimal(0);
        for (EquipmentStatusData data:equipmentStatusDataList){
            BigDecimal total=getEqptTotalNum();
            BigDecimal counts = new BigDecimal(0);
            if("MAN".equals(data.getVal())){
                counts = counts.add(new BigDecimal(1));
            }
            BigDecimal pmNum1 = counts.multiply(new BigDecimal("100")).divide(total,2, RoundingMode.HALF_UP);
            pmNum = pmNum.add(pmNum1);
        }
        return pmNum;
    }

    public BigDecimal getOeeNum() {
        oeeNum = new BigDecimal(0);
        for (EquipmentStatusData data:equipmentStatusDataList){
            BigDecimal total = getEqptTotalNum();
            BigDecimal counts = new BigDecimal(0);
            if("WAT".equals(data.getVal())||"RUN".equals(data.getVal())){
                counts = counts.add(new BigDecimal(1));
            }
            BigDecimal oeeNum1 = counts.multiply(new BigDecimal("100")).divide(total,2, RoundingMode.HALF_UP);
            oeeNum = oeeNum.add(oeeNum1);
        }
        return oeeNum;
    }

    public BigDecimal getFailNum() {
        failNum = new BigDecimal(0);
        for (EquipmentStatusData data:equipmentStatusDataList){
            BigDecimal total = getEqptTotalNum();
            BigDecimal counts = new BigDecimal(0);
            if("TRB".equals(data.getVal())){
                counts = counts.add(new BigDecimal(1));
            }
            BigDecimal failNum1 = counts.multiply(new BigDecimal("100")).divide(total,2, RoundingMode.HALF_UP);
            failNum = failNum.add(failNum1);
        }
        return failNum;
    }

    /*public void mathNum(){
        if (CollectionUtils.isEmpty(equipmentStatusDataList)){
            eqptTotalNum=0;
            pmNum=0;
            oeeNum=0;
            failNum=0;
            amhs=0;
        }
        pmNum=0;
        oeeNum=0;
        failNum=0;
        amhs=1;
        eqptTotalNum=equipmentStatusDataList.size();
        for (EquipmentStatusData data:equipmentStatusDataList){
            if ("TRB".equals(data.getVal())){
                failNum++;
            }
            if ("RUN".equals(data.getVal())||
                    "MAN".equals(data.getVal())||
                    "WAT".equals(data.getVal())){
                oeeNum++;
            }
            if ("MNT".equals(data.getVal())){
                pmNum++;
            }
        }
    }*/
}
