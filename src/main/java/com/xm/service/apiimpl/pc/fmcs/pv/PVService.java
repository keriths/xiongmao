package com.xm.service.apiimpl.pc.fmcs.pv;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.LogUtils;
import com.xm.service.dao.fmcs.MAUSystemDataDAO;
import com.xm.service.dto.KeyValueDTO;
import com.xm.service.dto.KeyValueRetDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fanshuai on 18/8/26.
 */
@Service("PVService")
@ApiServiceDoc(name = "PV图接口")
public class PVService {
    @Resource(name="mauSystemDataDAO")
    private MAUSystemDataDAO mauSystemDataDAO;
    @ApiMethodDoc(apiCode = "pv-001",name = "PV图接口")
    public KeyValueRetDTO getPvDataValue(){
        KeyValueRetDTO resultDto = new KeyValueRetDTO();
        try {
            List<KeyValueDTO> valueList = mauSystemDataDAO.queryPVDatas();
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
