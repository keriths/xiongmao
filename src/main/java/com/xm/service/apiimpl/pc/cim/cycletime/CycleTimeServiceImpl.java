package com.xm.service.apiimpl.pc.cim.cycletime;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.service.apiimpl.pc.cim.cycletime.dto.CycleTimeRetDTO;
import org.springframework.stereotype.Service;

/**
 * Created by fanshuai on 17/10/24.
 */
@Service("CycleTimeService")
@ApiServiceDoc(name = "CIM_CycleTime")
public class CycleTimeServiceImpl {
    @ApiMethodDoc(apiCode = "CIM_CycleTime",name = "Cycle Time 显示数据接口")
    private CycleTimeRetDTO cycleTime(@ApiParamDoc(desc = "统计时间类型天d周w月m周w")String dateType,@ApiParamDoc(desc = "产品如40，50")String product){
        return null;
    }
}
