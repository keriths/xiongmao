package com.xm.service.apiimpl.pc.cim.equipmentstatus.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

public class EquipmentCollectDataDTO extends BaseRetDTO {
    @ApiResultFieldDesc(desc = "工厂状态数据列表")
    private List<FactoryEquiStatusNumCollectDTO> factoryStatusNumList ;

    public List<FactoryEquiStatusNumCollectDTO> getFactoryStatusNumList() {
        return factoryStatusNumList;
    }

    public void setFactoryStatusNumList(List<FactoryEquiStatusNumCollectDTO> factoryStatusNumList) {
        this.factoryStatusNumList = factoryStatusNumList;
    }

    public static class FactoryEquiStatusNumCollectDTO {
        @ApiResultFieldDesc(desc = "工厂")
        public String factory;
        @ApiResultFieldDesc(desc = "设备总数")
        public Integer totalNum = 0;
        @ApiResultFieldDesc(desc = "PM中设备总数")
        public Integer pmNum = 0;
        @ApiResultFieldDesc(desc = "可稼动设备总数")
        public Integer oeeNum = 0;
        @ApiResultFieldDesc(desc = "故障设备总数")
        public Integer failNum = 0;

        public String getFactory() {
            return factory;
        }

        public Integer getTotalNum() {
            return totalNum;
        }

        public Integer getPmNum() {
            return pmNum;
        }

        public Integer getOeeNum() {
            return oeeNum;
        }

        public Integer getFailNum() {
            return failNum;
        }
    }
}
