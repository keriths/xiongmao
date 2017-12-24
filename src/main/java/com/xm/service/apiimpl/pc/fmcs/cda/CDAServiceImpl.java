package com.xm.service.apiimpl.pc.fmcs.cda;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.LogUtils;
import com.xm.platform.util.MapUtils;
import com.xm.service.apiimpl.pc.fmcs.cda.dto.CdaData;
import com.xm.service.apiimpl.pc.fmcs.cda.dto.CdaDataRetDTO;
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

    @ApiMethodDoc(apiCode = "FMCS_CDAData",name = "空压系统数据接口")
    public CdaDataRetDTO cdaDataDataRetDto(){
        CdaDataRetDTO resultDto = new CdaDataRetDTO();
        try {
            List<CdaData> queryList = cdaDataDAO.queryCdaData();
            resultDto.setCdaDataList(queryList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(this.getClass(),"CDADate eclipse",e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }
}
