package com.xm.job;

import com.xm.platform.util.LogUtils;
import com.xm.webservice.client.*;
import javafx.beans.binding.ObjectExpression;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.xml.ws.Holder;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.xm.service.apiimpl.pc.sap.*;
import com.xm.service.dao.sap.PanelProductLineCostDAO;
import com.xm.service.dao.sap.PanelSalesIncomeCostDAO;

/**
 * Created by fanshuai on 18/1/19.
 */
@Component
public class SAPDataSyncTask {
    @Resource(name = "panelProductLineCostDAO")
    private PanelProductLineCostDAO panelProductLineCostDAO;
    @Resource(name = "panelSalesIncomeCostDAO")
    private PanelSalesIncomeCostDAO panelSalesIncomeCostDAO;

    @Scheduled(cron = "0 0 8,15,23 * * *")
    public void SendWebservice() {

        Calendar now = Calendar.getInstance();

        String year = String.valueOf(now.get(Calendar.YEAR));

        String month = "";

        if (now.get(Calendar.MONTH) + 1 >= 10) {
            month = String.valueOf(now.get(Calendar.MONTH) + 1);
        } else {
            month = "0" + String.valueOf(now.get(Calendar.MONTH) + 1);
        }


        ZHBTPROFIT zhbtprofit=new ZHBTPROFIT();
        ZHBTSRCB zhbtsrcb=new ZHBTSRCB();
        try {
            Holder<ZHBTPROFIT> zhbtprofitholder = new Holder<ZHBTPROFIT>();
            Holder<ZHBTSRCB> zhbtsrcbholder = new Holder<ZHBTSRCB>();
            ZHBSENDRESULT2_Service servicefactory = new ZHBSENDRESULT2_Service();
            ZHBSENDRESULT2 zhbsendresult2 = servicefactory.getZHBSENDRESULT2();
            zhbsendresult2.zhbSENDRESULT2(year, month, zhbtprofitholder, zhbtsrcbholder);
            zhbtprofit = zhbtprofitholder.value;
            zhbtsrcb = zhbtsrcbholder.value;
        } catch (Exception ex) {

            LogUtils.error(this.getClass(), ex.getMessage());
        }
        CheckProductlineCostDATA(zhbtprofit,year,month);

        CheckSaleIncomeCostDATA(zhbtsrcb,year,month);

    }

    void CheckProductlineCostDATA(ZHBTPROFIT zhbtprofit, String _year, String _month) {
        List<ZHBSPROFIT> zhbsprofitList = zhbtprofit.getItem();
        for (ZHBSPROFIT _zhbtprofit : zhbsprofitList) {
            List<Map<String, Object>> dateIndatabase = panelProductLineCostDAO.getData(_year, _month);
            if (dateIndatabase != null) {
                //当月有数据
                for (Map<String, Object> datarow : dateIndatabase) {
                    if (_zhbtprofit.getZROWID().equals(datarow.get("ZROWID"))) {

                        try {
                            Map<String, Object> datamap = Obj2Map(_zhbtprofit);
                            datamap.put("Year", _year);
                            datamap.put("Month", _month);
                            panelProductLineCostDAO.updateData(datamap);

                        } catch (Exception ex) {
                            LogUtils.error(this.getClass(), ex.getMessage());
                        }

                    }
                }
            } else {
                //当月没有数据
                try {
                    Map<String, Object> datamap = Obj2Map(_zhbtprofit);
                    datamap.put("Year", _year);
                    datamap.put("Month", _month);
                    panelProductLineCostDAO.addData(datamap);

                } catch (Exception ex) {
                    LogUtils.error(this.getClass(), ex.getMessage());
                }

            }
        }
    }


    void CheckSaleIncomeCostDATA(ZHBTSRCB zhbtsrcb, String _year, String _month) {
        List<ZHBSSRCB> zhbssrcbList = zhbtsrcb.getItem();
        for (ZHBSSRCB _zhbssrcb : zhbssrcbList) {
            Map<String, Object> dateIndatabase = panelSalesIncomeCostDAO.getOneData(_year, _month, _zhbssrcb.getCPBM());
            if (dateIndatabase != null) {
                //当月此产品有数据
                try {
                    Map<String, Object> datamap = Obj2Map(_zhbssrcb);
                    datamap.put("Year", _year);
                    datamap.put("Month", _month);
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
                    panelSalesIncomeCostDAO.addData(datamap);
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
