package com.xm.service.apiimpl.pc.integrateData.system;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.LogUtils;
import com.xm.service.apiimpl.pc.integrateData.system.dto.MauRcuCollectDataDTO;
import com.xm.service.constant.Constant;
import com.xm.service.dao.fmcs.MAUSystemDataDAO;
import com.xm.service.dao.fmcs.RcuSystemDataDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangshuna on 2018/3/6.
 */
@Service("SystemService")
@ApiServiceDoc(name = "PCW,CDA,MAU,RCU,SEX,UPW,WWT综合数据")
public class SystemServiceImpl {

    @Resource(name = "mauSystemDataDAO")
    public MAUSystemDataDAO mauSystemDataDAO;
    @Resource(name = "rcuSystemDataDAO")
    public RcuSystemDataDAO rcuSystemDataDAO;

    @ApiMethodDoc(apiCode = "mauRcuCollectData" , name = "新风空调系统,热回收空提噢系统")
    public MauRcuCollectDataDTO mauRcuCollectDataDTO(){
        MauRcuCollectDataDTO resultDto = new MauRcuCollectDataDTO();
        try {
            List<MauRcuCollectDataDTO.MauRcuCollectData> mauDataList = mauSystemDataDAO.queryMAUData(Constant.systemTypeList);
            List<MauRcuCollectDataDTO.MauRcuCollectData> rcuDataList = rcuSystemDataDAO.queryRCUData(Constant.systemTypeList);
            resultDto.setMauCollectDataList(mauDataList);
            resultDto.setRcuCollectDataList(mauDataList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(this.getClass(),"lineCollectRetDTO eclipse",e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }
}
