package com.xm.service.apiimpl.pc.fmcs.electricity;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.LogUtils;
import com.xm.service.apiimpl.pc.fmcs.electricity.dto.ElectricityRetDTO;
import com.xm.service.dao.fmcs.ElecEveryHourDataDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by fanshuai on 17/10/24.
 */
@Service("ElectricityService")
@ApiServiceDoc(name = "FMCS2_电")
public class ElectricityServiceImpl {
    @Resource(name="elecEveryHourDataDAO")
    private ElecEveryHourDataDAO elecEveryHourDataDAO;
    @ApiMethodDoc(apiCode = "FMCS_ElecHourData",name = "电每小时每天每月统计数据接口")
    public ElectricityRetDTO electricityRetDTO(@ApiParamDoc(desc = "地点类型，如(4A,4B)")String placeType,@ApiParamDoc(desc = "统计时间类型天day月month(必填)")String dateType){
        ElectricityRetDTO eleRet = new ElectricityRetDTO();
        try{
            return eleRet;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            eleRet.setSuccess(false);
            eleRet.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return eleRet;
        }
    }

}
