package com.xm.service.apiimpl.pc.sap.PanelSalesIncomeAndCost;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.LogUtils;


import javafx.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

import com.xm.service.apiimpl.pc.sap.PanelSalesIncomeAndCost.dto.*;
import com.xm.service.dao.cim.PanelSalesIncomeCostDAO;

/**
 * Created by suziyue on 19/07/15.
 */
@Service("PanelSalesIncomeCostService")
@ApiServiceDoc(name = "SAP_面板销售收入及成本数据")
public class PanelSalesIncomeCostService {
    @Resource(name = "panelSalesIncomeCostDAO")
    private PanelSalesIncomeCostDAO panelSalesIncomeCostDAO;

    @ApiMethodDoc(apiCode = "SAP_PanelSalesIncomeCostYearData", name = "面板销售收入年度数据（包括半年）")
    public PanelSalesIncomeCostDTO getPanelProductLineCostYearData() {
        PanelSalesIncomeCostDTO resultDTO = new PanelSalesIncomeCostDTO();
        try {
            List<Pair<String, String>> monthlist = getCurrentmonthlist();//获取今年的所有月份
            //  List<PanelSalesIncomeCostData> panelSalesIncomeCostData = new ArrayList<PanelSalesIncomeCostData>();
            List<IncomeDataByMonth> IncomeDataByMonthList = new ArrayList<IncomeDataByMonth>();
            for (Pair<String, String> _month : monthlist) {
                List<PanelSalesIncomeCostData> _panelProductLineCostDataList = getPanelSalesIncomeCostData2para(_month.getKey().trim(), _month.getValue().trim());
                if (_panelProductLineCostDataList == null) {
                    continue;
                }
                IncomeDataByMonth incomeDataByMonth = new IncomeDataByMonth();

                incomeDataByMonth.Year = _month.getKey().trim();
                incomeDataByMonth.Month = _month.getValue().trim();
                incomeDataByMonth.SetPanelSalesIncomeCostDataList(_panelProductLineCostDataList);

                incomeDataByMonth.SetDDEMonthGroupFromList(_panelProductLineCostDataList);

                IncomeDataByMonthList.add(incomeDataByMonth);

            }

            resultDTO.SetIncomeDataByMonthList(IncomeDataByMonthList);

            SetHalfyearData(resultDTO);

            return resultDTO;

        } catch (Exception e) {
            LogUtils.error(getClass(), e);
            resultDTO.setSuccess(false);
            resultDTO.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDTO;
        }
    }

    void SetHalfyearData(PanelSalesIncomeCostDTO panelSalesIncomeCostDTO) {

        List<IncomeDataByMonth> incomeDataByMonthList = panelSalesIncomeCostDTO.getIncomeDataByMonthList();
        if (incomeDataByMonthList == null) {
            return;
        }
        DDEhalfYearAndYearGroup ddEhalfYearAndYearGroup = new DDEhalfYearAndYearGroup();

        int count = 0;
        //上半年全年统计
        for (int i = incomeDataByMonthList.size() - 1; i >= 0; i--) {

            if (count < 6) {
                ddEhalfYearAndYearGroup.CCL01halfYear = ddEhalfYearAndYearGroup.CCL01halfYear.add(incomeDataByMonthList.get(i).getDdeMonthGroup().CCL01Group);

                ddEhalfYearAndYearGroup.XSSR01halfYear = ddEhalfYearAndYearGroup.XSSR01halfYear.add(incomeDataByMonthList.get(i).getDdeMonthGroup().XSSR01Group);

                ddEhalfYearAndYearGroup.KCZE01halfYear = ddEhalfYearAndYearGroup.KCZE01halfYear.add(incomeDataByMonthList.get(i).getDdeMonthGroup().KCZE01Group);

                ddEhalfYearAndYearGroup.KCL02halfYear = ddEhalfYearAndYearGroup.KCL02halfYear.add(incomeDataByMonthList.get(i).getDdeMonthGroup().KCL02Group);

                ddEhalfYearAndYearGroup.KCL01halfYear = ddEhalfYearAndYearGroup.KCL01halfYear.add(incomeDataByMonthList.get(i).getDdeMonthGroup().KCL01Group);

                ddEhalfYearAndYearGroup.XSL02halfYear = ddEhalfYearAndYearGroup.XSL02halfYear.add(incomeDataByMonthList.get(i).getDdeMonthGroup().XSL02Group);

                ddEhalfYearAndYearGroup.XSL01halfYear = ddEhalfYearAndYearGroup.XSL01halfYear.add(incomeDataByMonthList.get(i).getDdeMonthGroup().XSL01Group);

                ddEhalfYearAndYearGroup.DDE01halfYear = ddEhalfYearAndYearGroup.DDE01halfYear.add(incomeDataByMonthList.get(i).getDdeMonthGroup().DDE01Group);

                ddEhalfYearAndYearGroup.DDL01halfYear = ddEhalfYearAndYearGroup.DDL01halfYear.add(incomeDataByMonthList.get(i).getDdeMonthGroup().DDL01Group);

                ddEhalfYearAndYearGroup.CKSR01halfYear = ddEhalfYearAndYearGroup.CKSR01halfYear.add(incomeDataByMonthList.get(i).getDdeMonthGroup().CKSR01Group);
            }
            ddEhalfYearAndYearGroup.CCL01Year = ddEhalfYearAndYearGroup.CCL01Year.add(incomeDataByMonthList.get(i).getDdeMonthGroup().CCL01Group);

            ddEhalfYearAndYearGroup.XSSR01Year = ddEhalfYearAndYearGroup.XSSR01Year.add(incomeDataByMonthList.get(i).getDdeMonthGroup().XSSR01Group);

            ddEhalfYearAndYearGroup.KCZE01Year = ddEhalfYearAndYearGroup.KCZE01Year.add(incomeDataByMonthList.get(i).getDdeMonthGroup().KCZE01Group);

            ddEhalfYearAndYearGroup.KCL02Year = ddEhalfYearAndYearGroup.KCL02Year.add(incomeDataByMonthList.get(i).getDdeMonthGroup().KCL02Group);

            ddEhalfYearAndYearGroup.KCL01Year = ddEhalfYearAndYearGroup.KCL01Year.add(incomeDataByMonthList.get(i).getDdeMonthGroup().KCL01Group);

            ddEhalfYearAndYearGroup.XSL02Year = ddEhalfYearAndYearGroup.XSL02Year.add(incomeDataByMonthList.get(i).getDdeMonthGroup().XSL02Group);

            ddEhalfYearAndYearGroup.XSL01Year = ddEhalfYearAndYearGroup.XSL01Year.add(incomeDataByMonthList.get(i).getDdeMonthGroup().XSL01Group);

            ddEhalfYearAndYearGroup.DDE01Year = ddEhalfYearAndYearGroup.DDE01Year.add(incomeDataByMonthList.get(i).getDdeMonthGroup().DDE01Group);

            ddEhalfYearAndYearGroup.DDL01Year = ddEhalfYearAndYearGroup.DDL01Year.add(incomeDataByMonthList.get(i).getDdeMonthGroup().DDL01Group);

            ddEhalfYearAndYearGroup.CKSR01Year = ddEhalfYearAndYearGroup.CKSR01Year.add(incomeDataByMonthList.get(i).getDdeMonthGroup().CKSR01Group);

            count++;
        }

        panelSalesIncomeCostDTO.setDDEhalfYearAndYearGroup(ddEhalfYearAndYearGroup);
    }

    List<Pair<String, String>> getmonthlist() {

        List<Pair<String, String>> pairList = new ArrayList<Pair<String, String>>();

        Calendar now = Calendar.getInstance();
        int yearint = now.get(Calendar.YEAR);
        String year = String.valueOf(now.get(Calendar.YEAR));
        String month = "";
        int monthint = now.get(Calendar.MONTH) + 1;//获取当月月份数字
        for (int i = 0; i < 12; i++) {
            String _month;
            if (monthint >= 10) {
                _month = String.valueOf(monthint);
            } else {
                _month = "0" + String.valueOf(monthint);
            }
            Pair<String, String> pair = new Pair<String, String>(String.valueOf(yearint), _month);
            pairList.add(pair);
            monthint--;

            if (monthint == 0) {
                monthint = 12;
                yearint--;
            }
        }
        return pairList;
    }

    List<Pair<String, String>> getCurrentmonthlist() {

        List<Pair<String, String>> pairList = new ArrayList<Pair<String, String>>();

        Calendar now = Calendar.getInstance();
        int yearint = now.get(Calendar.YEAR);
        String year = String.valueOf(now.get(Calendar.YEAR));
        String month = "";
        int monthint = now.get(Calendar.MONTH) ;//获取上月月份数字
        for (int i = 0; i < 12; i++) {
            String _month;
            if (monthint == 0) {
                break;
            }
            if (monthint >= 10) {
                _month = String.valueOf(monthint);
            } else {
                _month = "0" + String.valueOf(monthint);
            }
            Pair<String, String> pair = new Pair<String, String>(String.valueOf(yearint), _month);
            pairList.add(pair);
            monthint--;

            if (monthint == 0) {
                break;
            }
        }
        return pairList;
    }


    public List<PanelSalesIncomeCostData> getPanelSalesIncomeCostData3para(String _year, String _month, String _cpbm) {

        List<PanelSalesIncomeCostData> panelSalesIncomeCostDataList = null;

        panelSalesIncomeCostDataList = panelSalesIncomeCostDAO.getPanelSalesIncomeCostData(_year, _month, _cpbm);

        return panelSalesIncomeCostDataList;
    }

    public List<PanelSalesIncomeCostData> getPanelSalesIncomeCostData2para(String _year, String _month) {

        List<PanelSalesIncomeCostData> panelSalesIncomeCostDataList = null;

        panelSalesIncomeCostDataList = panelSalesIncomeCostDAO.getPanelSalesIncomeCostDataByYearAndMonth(_year, _month);

        return panelSalesIncomeCostDataList;
    }

    public List<PanelSalesIncomeCostData> getPanelSalesIncomeCostData1para(String _year) {

        List<PanelSalesIncomeCostData> panelSalesIncomeCostDataList = null;

        panelSalesIncomeCostDataList = panelSalesIncomeCostDAO.getPanelSalesIncomeCostDataByYear(_year);

        return panelSalesIncomeCostDataList;
    }


    public List<PanelSalesIncomeCostData> getPanelSalesIncomeCostYearDataByYear() {

        List<PanelSalesIncomeCostData> panelSalesIncomeCostDataList = null;

        panelSalesIncomeCostDataList = panelSalesIncomeCostDAO.getPanelSalesIncomeCostYearDataByYear();

        return panelSalesIncomeCostDataList;
    }


}
