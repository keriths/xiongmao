package com.xm.service.apiimpl.pc.fmcs.upw;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.LogUtils;
import com.xm.service.apiimpl.pc.fmcs.upw.dto.*;
import com.xm.service.dao.factory.fmcs.FactoryUpwaDataDAO;
import com.xm.service.dao.fmcs.UpwaDataDAO;
import com.xm.service.dao.fmcs.UpwbDataDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fanshuai on 17/10/24.
 */
@Service("UPWService")
@ApiServiceDoc(name = "FMCS10_纯水制造系统(UPW)")
public class UPWServiceImpl {
    @Resource
    private UpwaDataDAO upwaDataDAO;
    @Resource
    private UpwbDataDAO upwbDataDAO;
    @Resource
    private FactoryUpwaDataDAO factoryUpwaDataDAO;

    @ApiMethodDoc(apiCode = "FMCS_upwaDataList",name = "设备状态接口")
    public UpwaDataRetDTO upwaDataList(){
        UpwaDataRetDTO retDTO=new UpwaDataRetDTO();
        try {
            List<UpwaData> dataList = upwaDataDAO.queryUpwaDataList();
            retDTO.setUpwaDataList(dataList);
            return retDTO;
        }catch (Exception e) {
            LogUtils.error(getClass(), e);
            retDTO.setSuccess(false);
            retDTO.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return retDTO;
        }
    }

    @ApiMethodDoc(apiCode = "FMCS_upwbDataList",name = "设备状态、温度、电阻率接口")
    public UpwbDataRetDTO upwbDataList(){
        UpwbDataRetDTO retDTO=new UpwbDataRetDTO();
        try {
            List<UpwbData> dataList = upwbDataDAO.queryUpwbDataList();
            retDTO.setUpwbDataList(dataList);
            return retDTO;
        }catch (Exception e) {
            LogUtils.error(getClass(), e);
            retDTO.setSuccess(false);
            retDTO.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return retDTO;
        }
    }

    @ApiMethodDoc(apiCode = "FMCS_SyncUpwaData",name = "同步设备实时状态")
    public void syncUpwaData(){
        try {
            List<SyncUpwaData> queryList = factoryUpwaDataDAO.queryUpwaDataList();
            for(SyncUpwaData upwaData:queryList){
                SyncUpwaData data=upwaDataDAO.queryStatusByKey(upwaData.getKey());
                if(data==null){
                    upwaDataDAO.insertStatusData(upwaData);
                }else {
                    upwaDataDAO.updateStatusData(upwaData);
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
