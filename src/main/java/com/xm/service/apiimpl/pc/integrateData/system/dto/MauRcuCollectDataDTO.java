package com.xm.service.apiimpl.pc.integrateData.system.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wangshuna on 2018/3/6.
 */
public class MauRcuCollectDataDTO extends BaseRetDTO{

    @ApiResultFieldDesc(desc="MAU数据列表")
    private List<MauRcuCollectData> mauCollectDataList;
    @ApiResultFieldDesc(desc="RCU数据列表")
    private List<MauRcuCollectData> rcuCollectDataList;

    public static class MauRcuCollectData{
        @ApiResultFieldDesc(desc = "系统名称")
        private String systemType;
        @ApiResultFieldDesc(desc = "系统状态")
        private String status;
        @ApiResultFieldDesc(desc = "温度")
        private BigDecimal temperature;
        @ApiResultFieldDesc(desc = "露点")
        private BigDecimal dewPoint;

        public String getSystemType() {
            return systemType;
        }

        public void setSystemType(String systemType) {
            this.systemType = systemType;
        }

        public String getStatus() {
            return "1";
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public BigDecimal getTemperature() {
            if(temperature==null){
                return temperature;
            }else{
                temperature = temperature.setScale(1,BigDecimal.ROUND_HALF_UP);
            }
            return temperature;
        }

        public void setTemperature(BigDecimal temperature) {
            this.temperature = temperature;
        }

        public BigDecimal getDewPoint() {
            if(dewPoint==null){
                return dewPoint;
            }else{
                dewPoint = dewPoint.setScale(1,BigDecimal.ROUND_HALF_UP);
            }
            return dewPoint;
        }

        public void setDewPoint(BigDecimal dewPoint) {
            this.dewPoint = dewPoint;
        }
    }

    public List<MauRcuCollectData> getMauCollectDataList() {
        return mauCollectDataList;
    }

    public void setMauCollectDataList(List<MauRcuCollectData> mauCollectDataList) {
        this.mauCollectDataList = mauCollectDataList;
    }

    public List<MauRcuCollectData> getRcuCollectDataList() {
        return rcuCollectDataList;
    }

    public void setRcuCollectDataList(List<MauRcuCollectData> rcuCollectDataList) {
        this.rcuCollectDataList = rcuCollectDataList;
    }
}
