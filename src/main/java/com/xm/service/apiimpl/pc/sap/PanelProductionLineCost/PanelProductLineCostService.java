package com.xm.service.apiimpl.pc.sap.PanelProductionLineCost;


import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.LogUtils;

import com.xm.service.dao.cim.PanelProductLineCostDAO;


import javafx.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;


import com.xm.service.apiimpl.pc.sap.PanelProductionLineCost.dto.*;

import javax.xml.ws.*;

/**
 * Created by suziyue on 19/07/15.
 */

@Service("PanelProductLineCostService")
@ApiServiceDoc(name = "SAP_面板产线成本费用月度数据采集")
public class PanelProductLineCostService {
    @Resource(name = "panelProductLineCostDAO")
    private PanelProductLineCostDAO productLineCostDAO;

    @ApiMethodDoc(apiCode = "SAP_PanelProductLineCostByYearMonth", name = "面板产线成本费用月度数据采集单月数据")
    public PanelProductLineCostDTO getPanelProductLineCostByYearMonth(
            @ApiParamDoc(desc = "年份") String _year,
            @ApiParamDoc(desc = "月份  两位") String _month
    ) {
        PanelProductLineCostDTO resultDTO = new PanelProductLineCostDTO();

        try {
            List<PanelProductLineCostData> panelProductLineCostDataList = getPanelProductLineCostData(_year, _month);
            resultDTO.setProductLineDetailDataList(panelProductLineCostDataList);
            return resultDTO;
        } catch (Exception e) {
            LogUtils.error(getClass(), e);
            resultDTO.setSuccess(false);
            resultDTO.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDTO;
        }

    }

    @ApiMethodDoc(apiCode = "SAP_PanelProductLineCostByYear", name = "面板产线成本费用月度数据采集年数据聚合")
    public PanelProductLineCostDTO getPanelProductLineCostByYear() {
        PanelProductLineCostDTO resultDTO = new PanelProductLineCostDTO();

        try {
            Calendar now = Calendar.getInstance();
            String _year = String.valueOf(now.get(Calendar.YEAR));

            List<Pair<String, String>> monthlist = getmonthlist();
            List<PanelProductLineCostData> panelProductLineCostDataList = new ArrayList<PanelProductLineCostData>();
            for (Pair<String, String> _month : monthlist) {
                List<PanelProductLineCostData> _panelProductLineCostDataList = getPanelProductLineCostData(_month.getKey().trim(), _month.getValue().trim());
                if (_panelProductLineCostDataList == null) {
                    LogUtils.error(getClass(), _month.getKey().trim() + " " + _month.getValue().trim() + "数据为空");
                    continue;
                }
                panelProductLineCostDataList.addAll(_panelProductLineCostDataList);
            }
            resultDTO.setProductLineDetailDataList(panelProductLineCostDataList);
            return resultDTO;
        } catch (Exception e) {
            LogUtils.error(getClass(), e);
            resultDTO.setSuccess(false);
            resultDTO.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDTO;
        }

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


    public List<PanelProductLineCostData> getPanelProductLineCostData(String _year, String _month) {

        List<PanelProductLineCostData> PanelProductLineCostDataList = null;

        PanelProductLineCostDataList = productLineCostDAO.queryPanelProductLineCostByYearMonth(_year, _month);

        return PanelProductLineCostDataList;

    }


    public List<PanelProductLineCostData> getPanelProductLineCostDataByYear(String _year) {

        List<PanelProductLineCostData> PanelProductLineCostDataList = null;

        PanelProductLineCostDataList = productLineCostDAO.queryPanelProductLineCostByYear(_year);

        return PanelProductLineCostDataList;

    }


}

