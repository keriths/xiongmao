package com.xm.service.apiimpl.pc.cim.equipmentstatus.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.ReturnDataUtils;
import com.xm.service.constant.Constant;

/**
 * Created by wangshuna on 2017/12/26.
 */
public class EquipmentStatusData {
    @ApiResultFieldDesc(desc = "厂别")
    private String factory;
    @ApiResultFieldDesc(desc = "设备名称")
    private String key;
    @ApiResultFieldDesc(desc = "设备状态值")
    private String val;
    @ApiResultFieldDesc(desc = "设备数据说明")
    private String keyDesc;
    @ApiResultFieldDesc(desc = "数据类型")
    private transient String dataType;
    @ApiResultFieldDesc(desc = "样例数据")
    private transient String demoData;
    @ApiResultFieldDesc(desc = "数据更新时间")
    private String dataDate;

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getVal() {
        if (val==null){
            if (Constant.showDemoData){
                if(dataType!=null && demoData!=null){
                    return ReturnDataUtils.demoData(getDataType(),getDemoData()).toString();
                }else{
                    val = "0";
                }
            }
        }
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getKeyDesc() {
        return keyDesc;
    }

    public void setKeyDesc(String keyDesc) {
        this.keyDesc = keyDesc;
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

    public String getDataDate() {
        return dataDate;
    }

    public void setDataDate(String dataDate) {
        this.dataDate = dataDate;
    }
}
