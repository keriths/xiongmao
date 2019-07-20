package com.xm.service.apiimpl.pc.sap.PanelSalesIncomeAndCost;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.DateUtils;
import com.xm.platform.util.LogUtils;
import com.xm.platform.util.MapUtils;
import com.xm.platform.util.RandomUtils;
import com.xm.service.constant.Constant;

import com.xm.service.dao.sap.PanelProductLineCostDAO;



import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

import   com.xm.service.apiimpl.pc.sap.PanelSalesIncomeAndCost.dto.*;
import com.xm.service.dao.sap.PanelSalesIncomeCostDAO;

/**
 * Created by suziyue on 19/07/15.
 */
@Service("PanelSalesIncomeCostService")
@ApiServiceDoc(name = "SAP_面板销售收入及成本数据")
public class PanelSalesIncomeCostService {
    @Resource(name = "panelSalesIncomeCostDAO")
    private PanelSalesIncomeCostDAO panelSalesIncomeCostDAO;

    @ApiMethodDoc(apiCode = "SAP_PanelSalesIncomeCostByYearMonthCPBM", name = "面板销售收入及成本数据年月产品编号")
    public PanelSalesIncomeCostDTO getPanelProductLineCostByYearMonthCPBM(
            @ApiParamDoc(desc = "年份") String _year,
            @ApiParamDoc(desc = "月份") String _month,
            @ApiParamDoc(desc = "产品编号,必填") String _cpbm
    ) {
        PanelSalesIncomeCostDTO resultDTO = new PanelSalesIncomeCostDTO();
        try {
            List<PanelSalesIncomeCostData> panelProductLineCostDataList = getPanelSalesIncomeCostData3para(_year, _month,_cpbm);
            resultDTO.setProductLineDetailDataList(panelProductLineCostDataList);
            return resultDTO;
        } catch (Exception e) {
            LogUtils.error(getClass(), e);
            resultDTO.setSuccess(false);
            resultDTO.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDTO;
        }
    }

    @ApiMethodDoc(apiCode = "SAP_PanelSalesIncomeCostByYearMonth", name = "面板销售收入及成本数据年月参数")
    public PanelSalesIncomeCostDTO getPanelProductLineCostByYearMonth(
            @ApiParamDoc(desc = "年份") String _year,
            @ApiParamDoc(desc = "月份") String _month
    ) {
        PanelSalesIncomeCostDTO resultDTO = new PanelSalesIncomeCostDTO();
        try {
            List<PanelSalesIncomeCostData> panelProductLineCostDataList = getPanelSalesIncomeCostData2para(_year, _month);
            resultDTO.setProductLineDetailDataList(panelProductLineCostDataList);
            return resultDTO;
        } catch (Exception e) {
            LogUtils.error(getClass(), e);
            resultDTO.setSuccess(false);
            resultDTO.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDTO;
        }
    }

    @ApiMethodDoc(apiCode = "SAP_PanelSalesIncomeCostByYear", name = "面板销售收入及成本数据年为参数 ")
    public PanelSalesIncomeCostDTO getPanelProductLineCostByYearMonth(
            @ApiParamDoc(desc = "年份") String _year

    ) {
        PanelSalesIncomeCostDTO resultDTO = new PanelSalesIncomeCostDTO();
        try {
            List<PanelSalesIncomeCostData> panelProductLineCostDataList = getPanelSalesIncomeCostData1para(_year);
            resultDTO.setProductLineDetailDataList(panelProductLineCostDataList);
            return resultDTO;
        } catch (Exception e) {
            LogUtils.error(getClass(), e);
            resultDTO.setSuccess(false);
            resultDTO.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDTO;
        }
    }


    @ApiMethodDoc(apiCode = "SAP_PanelSalesIncomeCostYearData", name = "面板销售收入年度数据（包括半年）")
    public PanelSalesIncomeCostDTO getPanelProductLineCostYearData(
            @ApiParamDoc(desc = "年份") String _year

    ) {
        PanelSalesIncomeCostDTO resultDTO = new PanelSalesIncomeCostDTO();
        try {
            List<PanelSalesIncomeCostData> panelProductLineCostDataList = getPanelSalesIncomeCostYearDataByYear();
            resultDTO.setProductLineDetailDataList(panelProductLineCostDataList);
            return resultDTO;
        } catch (Exception e) {
            LogUtils.error(getClass(), e);
            resultDTO.setSuccess(false);
            resultDTO.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDTO;
        }
    }


    public List<PanelSalesIncomeCostData> getPanelSalesIncomeCostData3para(String _year, String _month,String _cpbm) {

        List<PanelSalesIncomeCostData> panelSalesIncomeCostDataList = null;

        panelSalesIncomeCostDataList = panelSalesIncomeCostDAO.getPanelSalesIncomeCostData(_year, _month,_cpbm);

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
