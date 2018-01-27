package com.xm.service.apiimpl.pc.cim.equipmentstatus.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by wangshuna on 2017/12/26.
 */
public class EquipmentStatusDataRetDTO extends BaseRetDTO{

    @ApiResultFieldDesc(desc = "返回数据列表")
    List<EquipmentStatusData> equipmentStatusDataList;
    @ApiResultFieldDesc(desc = "设备总数")
    Integer eqptTotalNum;
    @ApiResultFieldDesc(desc = "PM设备数")
    Integer pmNum;
    @ApiResultFieldDesc(desc = "可稼动设备数")
    Integer oeeNum;
    @ApiResultFieldDesc(desc = "故障中的设备数")
    Integer failNum;
    @ApiResultFieldDesc(desc = "AMHS连接状态 1 0")
    Integer amhs;
    public List<EquipmentStatusData> getEquipmentStatusDataList() {
        return equipmentStatusDataList;
    }

    public void setEquipmentStatusDataList(List<EquipmentStatusData> equipmentStatusDataList) {
        this.equipmentStatusDataList = equipmentStatusDataList;
    }

    public Integer getEqptTotalNum() {
        if (CollectionUtils.isEmpty(equipmentStatusDataList)){
            return 0;
        }
        return equipmentStatusDataList.size();
    }



    public Integer getPmNum() {
        if (pmNum!=null){
            return pmNum;
        }
        return pmNum;
    }


    public Integer getOeeNum() {
        return oeeNum;
    }



    public Integer getFailNum() {
        return failNum;
    }

    public void mathNum(){
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
    }
}
