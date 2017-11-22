package com.xm.service.apiimpl.pc.cim.goodinprocess.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by luokaiming on 2017/11/22.
 */
public class GoodInProcessWipRetDTO extends BaseRetDTO {

    @ApiResultFieldDesc(desc = "在制品WIP推移数据集合")
    private List<GoodInProcessWipDataDTO> wipDataDtoList;


}
