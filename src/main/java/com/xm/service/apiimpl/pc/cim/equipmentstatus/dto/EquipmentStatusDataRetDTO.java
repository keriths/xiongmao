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
    @ApiResultFieldDesc(desc = "PM设备数")
    BigDecimal pmNum;
    @ApiResultFieldDesc(desc = "可稼动设备数")
    BigDecimal oeeNum;
    @ApiResultFieldDesc(desc = "故障中的设备数")
    BigDecimal failNum;
    @ApiResultFieldDesc(desc = "AMHS连接状态 1 0")
    Integer amhs = 1;

    public List<EquipmentStatusData> getEquipmentStatusDataList() {
        return equipmentStatusDataList;
    }

    public void setEquipmentStatusDataList(List<EquipmentStatusData> equipmentStatusDataList) {
        this.equipmentStatusDataList = equipmentStatusDataList;
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


    public void mathNum(){
        eqptTotalNum=new BigDecimal("0");
        pmNum=new BigDecimal("0");
        oeeNum=new BigDecimal("0");
        failNum=new BigDecimal("0");
        amhs  = 1;//TODO 这块还要看怎么取，先默认1
        if (CollectionUtils.isEmpty(equipmentStatusDataList)){
           return;
        }
        for (EquipmentStatusData data:equipmentStatusDataList){
            if ("TRB".equals(data.getVal())){
                failNum = failNum.add(BigDecimal.ONE);
                eqptTotalNum = eqptTotalNum.add(BigDecimal.ONE);
            }
            if ("RUN".equals(data.getVal()) || "WAT".equals(data.getVal())){
                oeeNum=oeeNum.add(BigDecimal.ONE);
                eqptTotalNum = eqptTotalNum.add(BigDecimal.ONE);
            }
            if ("MAN".equals(data.getVal()) || "MNT".equals(data.getVal())){
                pmNum=pmNum.add(BigDecimal.ONE);
                eqptTotalNum = eqptTotalNum.add(BigDecimal.ONE);
            }
        }
    }
}
