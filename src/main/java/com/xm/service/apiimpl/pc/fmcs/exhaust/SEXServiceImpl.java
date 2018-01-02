package com.xm.service.apiimpl.pc.fmcs.exhaust;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.LogUtils;
import com.xm.service.apiimpl.pc.fmcs.exhaust.dto.ExhaustAData;
import com.xm.service.apiimpl.pc.fmcs.exhaust.dto.ExhaustADataRetDTO;
import com.xm.service.apiimpl.pc.fmcs.exhaust.dto.ExhaustBData;
import com.xm.service.apiimpl.pc.fmcs.exhaust.dto.ExhaustBDataRetDTO;
import com.xm.service.constant.Constant;
import com.xm.service.dao.fmcs.ExhaustADataDAO;
import com.xm.service.dao.fmcs.ExhaustBDataDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fanshuai on 17/10/24.
 */
@Service("SEXService")
@ApiServiceDoc(name = "FMCS9_排气系统(SEX,AEX,VOC)")
public class SEXServiceImpl {

    @Resource(name="exhaustADataDAO")
    private ExhaustADataDAO exhaustADataDAO;
    @Resource(name="exhaustBDataDAO")
    private ExhaustBDataDAO exhaustBDataDAO;

    @ApiMethodDoc(apiCode = "FMCS_ExhaustAData",name = "4A-VOC-S数据接口")
    public ExhaustADataRetDTO exhaustADataRetDTO(@ApiParamDoc(desc = "系统名称,如“F-101A,F-101B,F-101C”") String name){
        ExhaustADataRetDTO resultDto = new ExhaustADataRetDTO();
        try {
            if(!Constant.ExhaustTypeList.contains(name)) {
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("ExhaustType参数错误,请传入【" + Constant.ExhaustTypeList + "】");
                return resultDto;
            }
            List<ExhaustAData> queryList = exhaustADataDAO.queryExhaustAData(name);
            resultDto.setExhaustADataList(queryList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }

    @ApiMethodDoc(apiCode = "FMCS_ExhaustBData",name = "排气系统数据接口")
    public ExhaustBDataRetDTO exhaustBDataRetDTO(){
        ExhaustBDataRetDTO resultDto = new ExhaustBDataRetDTO();
        try {
            List<ExhaustBData> queryList = exhaustBDataDAO.queryExhaustBData();
            resultDto.setExhaustBDataList(queryList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }
}
