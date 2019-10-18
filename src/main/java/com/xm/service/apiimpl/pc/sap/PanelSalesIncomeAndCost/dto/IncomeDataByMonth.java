package com.xm.service.apiimpl.pc.sap.PanelSalesIncomeAndCost.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;

import java.io.Serializable;
import java.util.List;

public class IncomeDataByMonth implements Serializable {

    @ApiResultFieldDesc(desc = "当月产品尺寸具体数据")
    public List<PanelSalesIncomeCostData> panelSalesIncomeCostData;
    @ApiResultFieldDesc(desc = "年份")
    public String Year;
    @ApiResultFieldDesc(desc = "月份")
    public String Month;
    @ApiResultFieldDesc(desc = "月度累计数据")
    public DDEMonthGroup ddeMonthGroup;

    public void SetPanelSalesIncomeCostDataList(List<PanelSalesIncomeCostData> _panelSalesIncomeCostData) {
        this.panelSalesIncomeCostData = _panelSalesIncomeCostData;
    }

    public List<PanelSalesIncomeCostData> getPanelSalesIncomeCostDataList() {
        return panelSalesIncomeCostData;
    }

    public void SetDDEMonthGroup(DDEMonthGroup _ddeMonthGroup) {
        this.ddeMonthGroup = _ddeMonthGroup;
    }

    public void SetDDEMonthGroupFromList(List<PanelSalesIncomeCostData> _panelSalesIncomeCostData)
    {
        DDEMonthGroup ddeMonthGroup = new DDEMonthGroup();
        //每月统计
        for (PanelSalesIncomeCostData panelSalesIncomeCostData : _panelSalesIncomeCostData) {
            ddeMonthGroup.DDE01Group = ddeMonthGroup.DDE01Group.add(panelSalesIncomeCostData.DDE01);

            ddeMonthGroup.DDL01Group = ddeMonthGroup.DDL01Group.add(panelSalesIncomeCostData.DDL01);

            ddeMonthGroup.XSL01Group = ddeMonthGroup.XSL01Group.add(panelSalesIncomeCostData.XSL01);

            ddeMonthGroup.XSL02Group = ddeMonthGroup.XSL02Group.add(panelSalesIncomeCostData.XSL02);

            ddeMonthGroup.KCL01Group = ddeMonthGroup.KCL01Group.add(panelSalesIncomeCostData.KCL01);

            ddeMonthGroup.KCL02Group = ddeMonthGroup.KCL02Group.add(panelSalesIncomeCostData.KCL02);

            ddeMonthGroup.KCZE01Group = ddeMonthGroup.KCZE01Group.add(panelSalesIncomeCostData.KCZE01);

            ddeMonthGroup.XSSR01Group = ddeMonthGroup.XSSR01Group.add(panelSalesIncomeCostData.XSSR01);

            ddeMonthGroup.CKSR01Group = ddeMonthGroup.CKSR01Group.add(panelSalesIncomeCostData.CKSR01);

            ddeMonthGroup.CCL01Group = ddeMonthGroup.CCL01Group.add(panelSalesIncomeCostData.CCL01);

            ddeMonthGroup.CKL01Group =ddeMonthGroup.CKL01Group.add(panelSalesIncomeCostData.CKL01);

            ddeMonthGroup.Month = panelSalesIncomeCostData.MONTH;
        }
        this.ddeMonthGroup = ddeMonthGroup;
    }

    public DDEMonthGroup getDdeMonthGroup()
    {
        return ddeMonthGroup;
    }
}
