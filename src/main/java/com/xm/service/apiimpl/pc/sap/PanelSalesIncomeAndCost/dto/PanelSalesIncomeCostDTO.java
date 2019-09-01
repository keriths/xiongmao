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
public class PanelSalesIncomeCostDTO extends BaseRetDTO {

    @ApiResultFieldDesc(desc = "产品销售收入清单，按月聚合")
    public List<IncomeDataByMonth> IncomeDataByMonthList;

  //  public List<DDEMonthGroup> ddeMonthGroupList;
    @ApiResultFieldDesc(desc = "9个字段年、半年累计数据")
    public DDEhalfYearAndYearGroup ddEhalfYearAndYearGroup;

    public void setDDEhalfYearAndYearGroup(DDEhalfYearAndYearGroup _ddehalfYearAndYearGroup)
    {
        this.ddEhalfYearAndYearGroup=_ddehalfYearAndYearGroup;
    }

    public void SetIncomeDataByMonthList(List<IncomeDataByMonth> _IncomeDataByMonthList)
    {
        this.IncomeDataByMonthList =_IncomeDataByMonthList;
    }

    public List<IncomeDataByMonth> getIncomeDataByMonthList()
    {
        return IncomeDataByMonthList;
    }
}
