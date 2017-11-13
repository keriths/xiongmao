package com.xm.service.apiimpl.pc.cim.Outputcompletion;

import com.xm.service.annotations.ApiMethodDoc;
import com.xm.service.annotations.ApiParamDoc;
import com.xm.service.annotations.ApiServiceDoc;
import org.springframework.stereotype.Service;

/**
 * Created by fanshuai on 17/10/24.
 */
@Service("OutputCompletionRateService")
@ApiServiceDoc(name = "CIM_产出达成率")
public class OutputCompletionRateServiceImpl {
    @ApiMethodDoc(apiCode = "CIM_outputCompletionRate" , name = "产出达成率接口")
    public OutputCompletionRetDTO OutputCompletionRate(@ApiParamDoc(desc = "厂别：SL、OC") String factor, @ApiParamDoc(desc = "统计时间类型天d周w月m")String dateType){
        OutputCompletionRetDTO dto=new OutputCompletionRetDTO();
        return dto;
    }
}
