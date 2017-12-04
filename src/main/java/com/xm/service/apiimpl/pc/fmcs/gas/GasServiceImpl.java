package com.xm.service.apiimpl.pc.fmcs.gas;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.LogUtils;
import com.xm.service.apiimpl.pc.fmcs.gas.dto.NatgasTimeDataRetDTO;
import com.xm.service.apiimpl.pc.fmcs.gas.dto.NatgasStatisticsDataRetDTO;
import org.springframework.stereotype.Service;

/**
 * Created by fanshuai on 17/10/24.
 */
@Service("GasService")
@ApiServiceDoc(name = "FMCS_气、汽")
public class GasServiceImpl {

    @ApiMethodDoc(apiCode = "FMCS_gasRealTime",name = "FMCS气、汽实时数据接口")
    public NatgasTimeDataRetDTO gasRealTime(){
        NatgasTimeDataRetDTO resultDto=new NatgasTimeDataRetDTO();
        try {
            return resultDto;
        }catch (Exception e){
            LogUtils.error(this.getClass(),"gasRealTime eclipse",e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }

    @ApiMethodDoc(apiCode = "FMCS_gsaStatistics",name = "FMCS气、汽统计数据接口（按天、按月）")
    public NatgasStatisticsDataRetDTO gsaStatistics() {
        NatgasStatisticsDataRetDTO resultDto = new NatgasStatisticsDataRetDTO();
        try {
            return resultDto;
        } catch (Exception e) {
            LogUtils.error(this.getClass(), "gsaStatistics eclipse", e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }


}
