package com.xm.service.apiimpl.pc.fmcs.spg_ccss;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.LogUtils;
import com.xm.service.dao.fmcs.MAUSystemDataDAO;
import com.xm.service.dto.CCSSDataDTO;
import com.xm.service.dto.KeyValueDTO;
import com.xm.service.dto.KeyValueRetDTO;
import com.xm.service.dto.ListValueRetDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fanshuai on 18/8/26.
 */
@Service("SPG_CCSS_Service")
@ApiServiceDoc(name = "SPG/CCSS图接口")
public class SPG_CCSS_Service {
    @Resource(name="mauSystemDataDAO")
    private MAUSystemDataDAO mauSystemDataDAO;
    @ApiMethodDoc(apiCode = "spg-001",name = "PV图接口")
    public KeyValueRetDTO querySPGDatas(){
        KeyValueRetDTO resultDto = new KeyValueRetDTO();
        try {
            List<KeyValueDTO> valueList = mauSystemDataDAO.querySPGDatas();
            resultDto.setValueList(valueList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }

    @ApiMethodDoc(apiCode = "ccss-001",name = "ccss图接口")
    public ListValueRetDTO queryCCSSDatas(){
        ListValueRetDTO resultDto = new ListValueRetDTO();
        try {
            List<CCSSDataDTO> valueList = mauSystemDataDAO.queryCCSSDatas();
            resultDto.setValueList(valueList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }

    @ApiMethodDoc(apiCode = "ccss-002",name = "ccss02图接口")
    public KeyValueRetDTO queryCCSS02Datas(){
        KeyValueRetDTO resultDto = new KeyValueRetDTO();
        try {
            List<KeyValueDTO> valueList = mauSystemDataDAO.queryCCSS02Datas();
            resultDto.setValueList(valueList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }
}
