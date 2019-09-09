package com.xm.service.apiimpl.pc.sap.PanelProductionLineCost;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.LogUtils;
import com.xm.platform.util.MapUtils;
import com.xm.platform.util.PrettyPrintingMap;
import com.xm.service.apiimpl.pc.sap.PanelProductionLineCost.dto.PanelProductLineCostDTO;
import com.xm.service.dao.cim.PanelProductLineCostDAO;
import com.xm.service.dao.cim.PanelSalesIncomeCostDAO;
import com.xm.webservice.client.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.ws.Holder;
import java.lang.reflect.Field;
import java.net.Authenticator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("GetDataFromWebService")
@ApiServiceDoc(name = "SAP数据手动采集")
public class GetDataFromWebService {

    @Resource(name = "panelProductLineCostDAO")
    private PanelProductLineCostDAO panelProductLineCostDAO;
    @Resource(name = "panelSalesIncomeCostDAO")
    private PanelSalesIncomeCostDAO panelSalesIncomeCostDAO;

    @ApiMethodDoc(apiCode = "SAP_GETDATABYMANUAL", name = "手动从Webservice采集数据")
    public PanelProductLineCostDTO getdatabymanualfromweb(
            @ApiParamDoc(desc = "年份") String _year,
            @ApiParamDoc(desc = "月份  两位") String _month
    ) {
        LogUtils.info(this.getClass(), "开始执行接口");
        PanelProductLineCostDTO resultDTO = new PanelProductLineCostDTO();

        ZHBTPROFIT zhbtprofit = new ZHBTPROFIT();
        ZHBTSRCB zhbtsrcb = new ZHBTSRCB();
        try {
            Authenticator.setDefault(new MyAuthenticator());
            Holder<ZHBTPROFIT> zhbtprofitholder = new Holder<ZHBTPROFIT>();
            Holder<ZHBTSRCB> zhbtsrcbholder = new Holder<ZHBTSRCB>();
            ZHBSENDRESULT2_Service servicefactory = new ZHBSENDRESULT2_Service();
            ZHBSENDRESULT2 zhbsendresult2 = servicefactory.getZHBSENDRESULT2();

            LogUtils.info(this.getClass(), "获取Webservice数据");

            zhbsendresult2.zhbSENDRESULT2(_year, _month, zhbtprofitholder, zhbtsrcbholder);
            zhbtprofit = zhbtprofitholder.value;
            zhbtsrcb = zhbtsrcbholder.value;


            int i1 = zhbtprofitholder.value.getItem().size();
            int i2 = zhbtsrcbholder.value.getItem().size();

            LogUtils.info(this.getClass(), "zhbtprofitholder长度 " + String.valueOf(i1));
            LogUtils.info(this.getClass(), "zhbtsrcbholder长度 " + String.valueOf(i2));

        } catch (Exception ex) {
            LogUtils.info(this.getClass(), "错误 + " + ex.getMessage());
            LogUtils.error(this.getClass(), ex.getMessage());
        }
        LogUtils.info(this.getClass(), "准备写入数据库");

        CheckProductlineCostDATA(zhbtprofit, _year, _month);

        CheckSaleIncomeCostDATA(zhbtsrcb, _year, _month);

        LogUtils.info(this.getClass(), "写入数据库完成");

        return resultDTO;
    }


    void CheckProductlineCostDATA(ZHBTPROFIT zhbtprofit, String _year, String _month) {
        List<ZHBSPROFIT> zhbsprofitList = zhbtprofit.getItem();
        LogUtils.info(this.getClass(), "ProductlineCostDATA长度 " + String.valueOf(zhbsprofitList.size()));
        List<Map<String, Object>> dateIndatabase = panelProductLineCostDAO.getData(_year, _month);

        for (ZHBSPROFIT _zhbtprofit : zhbsprofitList) {
            //当月有数据
            if (dateIndatabase.size() > 0) {
                for (Map<String, Object> datarow : dateIndatabase) {
                    if (_zhbtprofit.getZROWID().equals(datarow.get("ZROWID"))) {

                        try {
                            Map<String, Object> datamap = Obj2Map(_zhbtprofit);
                            datamap.put("Year", _year);
                            datamap.put("Month", _month);
                            String mapstr = new PrettyPrintingMap<String, Object>(datamap).toString();
                            LogUtils.info(this.getClass(), "转化后map  " + mapstr);

                            panelProductLineCostDAO.updateData(datamap);

                        } catch (Exception ex) {
                            LogUtils.error(this.getClass(), ex.getMessage());
                            LogUtils.info(this.getClass(), "错误+" + ex.getMessage());
                        }
                    }
                }
            } else {
                //当月没有数据
                try {
                    Map<String, Object> datamap = Obj2Map(_zhbtprofit);
                    datamap.put("Year", _year);
                    datamap.put("Month", _month);
                    String mapstr = new PrettyPrintingMap<String, Object>(datamap).toString();
                    LogUtils.info(this.getClass(), "转化后map  " + mapstr);
                    panelProductLineCostDAO.addData(datamap);
                } catch (Exception ex) {
                    LogUtils.error(this.getClass(), ex.getMessage());
                    LogUtils.info(this.getClass(), "错误+" + ex.getMessage());
                }
            }
        }
    }


    void CheckSaleIncomeCostDATA(ZHBTSRCB zhbtsrcb, String _year, String _month) {
        List<ZHBSSRCB> zhbssrcbList = zhbtsrcb.getItem();
        LogUtils.info(this.getClass(), "SaleIncomeCostDATA长度 " + String.valueOf(zhbssrcbList.size()));

        for (ZHBSSRCB _zhbssrcb : zhbssrcbList) {

            Map<String, Object> mapdata = MapUtils.newMap(
                    "Year", _year,
                    "Month", _month,
                    "CPBM", _zhbssrcb.getCPBM()
            );

            Map<String, Object> dateIndatabase = panelSalesIncomeCostDAO.getOneData(mapdata);
            if (dateIndatabase != null) {
                String mapstr = new PrettyPrintingMap<String, Object>(dateIndatabase).toString();
                LogUtils.info(this.getClass(), "转化后结果map   " + mapstr);
            }
            if (dateIndatabase == null) {
                LogUtils.info(this.getClass(), "取出的单条数据为空");
                String mapstr1 = new PrettyPrintingMap<String, Object>(mapdata).toString();
                LogUtils.info(this.getClass(), "条件map为  " + mapstr1);
            }
            if (dateIndatabase != null) {
                //当月此产品有数据
                try {
                    Map<String, Object> datamap = Obj2Map(_zhbssrcb);
                    datamap.put("Year", _year);
                    datamap.put("Month", _month);
                    String mapstr = new PrettyPrintingMap<String, Object>(datamap).toString();
                    LogUtils.info(this.getClass(), "转化后map  " + mapstr);
                    panelSalesIncomeCostDAO.updateData(datamap);

                } catch (Exception ex) {
                    LogUtils.error(this.getClass(), ex.getMessage());
                }
            } else {
                //当月此产品没有数据
                try {
                    Map<String, Object> datamap = Obj2Map(_zhbssrcb);
                    datamap.put("Year", _year);
                    datamap.put("Month", _month);
                    String mapstr = new PrettyPrintingMap<String, Object>(datamap).toString();
                    LogUtils.info(this.getClass(), "转化后map  " + mapstr);

                    try {
                        panelSalesIncomeCostDAO.addData(datamap);
                    } catch (Exception ex) {
                        panelSalesIncomeCostDAO.updateData(datamap);
                        LogUtils.info(this.getClass(), "执行更新sql");
                        LogUtils.info(this.getClass(), ex.getMessage());
                    }

                } catch (Exception ex) {
                    LogUtils.error(this.getClass(), ex.getMessage());
                }
            }
        }
    }


    public boolean notEquals(Object o1, Object o2) {
        if (o1 == null) {
            return o2 != null;
        } else {
            return !o1.equals(o2);
        }
    }

    public Map<String, Object> Obj2Map(Object obj) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }
        return map;
    }


}
