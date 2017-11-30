package com.xm.service.apiimpl.pc.fmcs.humiture;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.LogUtils;
import com.xm.service.apiimpl.pc.fmcs.humiture.dto.HumitureDataRetDTO;
import org.springframework.stereotype.Service;

/**
 * Created by fanshuai on 17/10/24.
 */
@Service("HumitureService")
@ApiServiceDoc(name = "FMCS_温湿度")
public class HumitureServiceImpl {

    @ApiMethodDoc(apiCode = "FMCS_humitureData",name = "FMCS温度湿度洁净度接口")
    public HumitureDataRetDTO humitureData(){
        HumitureDataRetDTO resultDto=new HumitureDataRetDTO();
        try {
            return resultDto;
        }catch (Exception e){
            LogUtils.error(this.getClass(),"humitureData eclipse",e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }
}
