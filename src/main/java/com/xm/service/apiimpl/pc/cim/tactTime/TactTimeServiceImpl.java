package com.xm.service.apiimpl.pc.cim.tactTime;

import com.xm.service.annotations.ApiMethodDoc;
import com.xm.service.annotations.ApiParamDoc;
import com.xm.service.annotations.ApiServiceDoc;
import org.springframework.stereotype.Service;

/**
 * Created by fanshuai on 17/10/24.
 */

@Service("TactTimeService")
@ApiServiceDoc(name = "CIM_TactTime")
public class TactTimeServiceImpl {

    @ApiMethodDoc(apiCode = "Tact_time_onthlyMean",name = "设备Tact_time月度平均值")
    public TactTimeRetDto onthlyMean(@ApiParamDoc(desc = "厂别：如array") String status, @ApiParamDoc(desc = "类型：如PHOTO、PVD") String factory){
        TactTimeRetDto dto=new TactTimeRetDto();
        return dto;
    }

    @ApiMethodDoc(apiCode = "Tact_time_Query",name = "特定厂别特定产品类型设备Tact_time")
    public TactTimeRetDto tactTimeQuery(@ApiParamDoc(desc = "厂别：如array") String status, @ApiParamDoc(desc = "类型：如PHOTO、PVD") String factory){
        TactTimeRetDto dto=new TactTimeRetDto();
        return dto;
    }
}
