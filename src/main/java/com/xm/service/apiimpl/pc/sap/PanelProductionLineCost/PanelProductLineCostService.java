package com.xm.service.apiimpl.pc.sap.PanelProductionLineCost;


import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.LogUtils;

import com.xm.service.dao.cim.PanelProductLineCostDAO;


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
            @ApiParamDoc(desc = "月份") String _month
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
    public PanelProductLineCostDTO getPanelProductLineCostByYear(
            @ApiParamDoc(desc = "年份") String _year

    ) {
        PanelProductLineCostDTO resultDTO = new PanelProductLineCostDTO();

        try {


            List<PanelProductLineCostData> panelProductLineCostDataList = getPanelProductLineCostDataByYear(_year);
            resultDTO.setProductLineDetailDataList(panelProductLineCostDataList);
            return resultDTO;
        } catch (Exception e) {
            LogUtils.error(getClass(), e);
            resultDTO.setSuccess(false);
            resultDTO.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDTO;
        }

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

