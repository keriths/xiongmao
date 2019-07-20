package com.xm.service.apiimpl.pc.sap.PanelSalesIncomeAndCost.dto;
import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.DateUtils;
import com.xm.platform.util.RandomUtils;
import com.xm.service.constant.Constant;
import com.xm.service.dto.BaseRetDTO;

import java.math.BigDecimal;
import java.util.*;


/**
 * Created by suziyue on 19/07/15.
 */
public class PanelSalesIncomeCostDTO  extends  BaseRetDTO{


    @ApiResultFieldDesc(desc = "返回数据详情")
    public List<PanelSalesIncomeCostData>  PanelSalesIncomeCostDatalist ;

    public List<PanelSalesIncomeCostData> getPanelSalesIncomeCostDatalist() {
        return PanelSalesIncomeCostDatalist;
    }

    public void setProductLineDetailDataList(List<PanelSalesIncomeCostData> _PanelSalesIncomeCostDatalist) {
        this.PanelSalesIncomeCostDatalist = _PanelSalesIncomeCostDatalist;
    }
}
