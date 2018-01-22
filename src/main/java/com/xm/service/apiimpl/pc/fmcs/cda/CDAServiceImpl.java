package com.xm.service.apiimpl.pc.fmcs.cda;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.LogUtils;
import com.xm.platform.util.MapUtils;
import com.xm.service.apiimpl.pc.fmcs.cda.dto.CdaData;
import com.xm.service.apiimpl.pc.fmcs.cda.dto.CdaDataRetDTO;
import com.xm.service.dao.factory.fmcs.FactoryCDADataDAO;
import com.xm.service.dao.fmcs.CDADataDAO;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by fanshuai on 17/10/24.
 */
@Service("CDAService")
@ApiServiceDoc(name = "FMCS06_空压系统(CDA)")
public class CDAServiceImpl {

    @Resource(name="cdaDataDAO")
    private CDADataDAO cdaDataDAO;
    @Resource(name = "factoryCdaDataDAO")
    private FactoryCDADataDAO factoryCdaDataDAO;

    @ApiMethodDoc(apiCode = "FMCS_CDAData",name = "空压系统数据接口")
    public CdaDataRetDTO cdaDataDataRetDto(){
        CdaDataRetDTO resultDto = new CdaDataRetDTO();
        try {
            List<CdaData> queryList = cdaDataDAO.queryCdaData();
            resultDto.setCdaDataList(queryList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }


    @ApiMethodDoc(apiCode = "FMCS_SyncCdaData",name = "同步设备实时状态")
    public void syncCdaData(){
        try {
            List<CdaData> queryList = factoryCdaDataDAO.queryCdaData();
            for(CdaData cdaData:queryList){
                CdaData data=cdaDataDAO.queryStatusByKey(cdaData.getKey());
                if(data==null){
                    data.setKey(cdaData.getKey());
                    data.setVal(cdaData.getVal());
                    data.setDataDate(cdaData.getDataDate());
                    cdaDataDAO.insertStatusData(data);
                }else {
                    data.setVal(cdaData.getVal());
                    data.setDataDate(cdaData.getDataDate());
                    cdaDataDAO.updateStatusData(data);
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
