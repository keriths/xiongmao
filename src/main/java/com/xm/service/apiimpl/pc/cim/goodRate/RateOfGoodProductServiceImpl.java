package com.xm.service.apiimpl.pc.cim.goodRate;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.service.apiimpl.pc.cim.inputCompletion.dto.InputCompletionRetDTO;
import org.springframework.stereotype.Service;

/**
 * Created by fanshuai on 17/10/24.
 */

@Service("RateOfGoodProductService")
@ApiServiceDoc(name = "CIM5_良品率（****不可用数据还没确认好****）")
public class RateOfGoodProductServiceImpl {
    @ApiMethodDoc(apiCode = "CIM_GoodRate" , name = "良品率接口")
    public Object goodRate() {
        return null;
    }
}
