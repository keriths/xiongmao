package com.xm.service.apiimpl.pc.fmcs.wwt.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.ReturnDataUtils;
import com.xm.service.constant.Constant;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by luokaiming on 2017/12/18 0018.
 * 废水处理系统wwt 设备状态
 */
public class WwtaData {

    @ApiResultFieldDesc(desc = "设备名称")
    private String key;

    @ApiResultFieldDesc(desc = "设备状态值")
    private String value;

    @ApiResultFieldDesc(desc = "数据值说明")
    private String keyDesc;

    @ApiResultFieldDesc(desc = "数据时间")
    private String dataDate;

    @ApiResultFieldDesc(desc = "样列数据类型")
    private String dataType;
    @ApiResultFieldDesc(desc = "样列数据范围")
    private String demoData;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        String dataType=getDataType();
        String demoData=getDemoData();
        if (value==null){
            if (dataType!=null && demoData!=null && Constant.showDemoData){
                return ReturnDataUtils.demoData(dataType,demoData).toString();
            }else{
                return "0";
            }
        }else{
            return value;
        }
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKeyDesc() {
        return keyDesc;
    }

    public void setKeyDesc(String keyDesc) {
        this.keyDesc = keyDesc;
    }

    public String getDataDate() {
        return dataDate;
    }

    public void setDataDate(String dataDate) {
        this.dataDate = dataDate;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDemoData() {
        return demoData;
    }

    public void setDemoData(String demoData) {
        this.demoData = demoData;
    }
}
