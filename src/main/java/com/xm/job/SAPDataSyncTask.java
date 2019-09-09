package com.xm.job;

import com.xm.platform.util.LogUtils;
import com.xm.platform.util.MapUtils;
import com.xm.platform.util.PrettyPrintingMap;
import com.xm.webservice.client.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.xml.ws.Holder;
import java.lang.reflect.Field;
import java.util.*;

import com.xm.service.dao.cim.PanelProductLineCostDAO;
import com.xm.service.dao.cim.PanelSalesIncomeCostDAO;
import java.net.Authenticator;
import javax.xml.ws.BindingProvider;
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
        //Calendar.MONTH 从0开始
        if (now.get(Calendar.MONTH) >= 10) {
            month = String.valueOf(now.get(Calendar.MONTH) );
        } else {
            month = "0" + String.valueOf(now.get(Calendar.MONTH));
        }
        //每个月去获取上月的数据 1月份获取去年12月的数据
        if (now.get(Calendar.MONTH)==0)
        {
            year = String.valueOf(now.get(Calendar.YEAR-1));
            month="12";
            //return;
        }

        ZHBTPROFIT zhbtprofit=new ZHBTPROFIT();
        ZHBTSRCB zhbtsrcb=new ZHBTSRCB();
        try {
            Authenticator.setDefault(new MyAuthenticator());
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
            String mapstr1 = new PrettyPrintingMap<String, Object>(mapdata).toString();


            Map<String, Object> dateIndatabase = panelSalesIncomeCostDAO.getOneData(mapdata);

            if (dateIndatabase != null) {
                String mapstr = new PrettyPrintingMap<String, Object>(dateIndatabase).toString();
                LogUtils.info(this.getClass(), "转化后结果map   " + mapstr);
            }
            if (dateIndatabase != null) {
                //当月此产品有数据
                //  if (dateIndatabase.size() > 0) {
                LogUtils.info(this.getClass(), "SaleIncomeCost当月此产品有数据");
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
                //  }
            } else {
                //当月此产品没有数据
                LogUtils.info(this.getClass(), "SaleIncomeCost当月此产品没有数据");
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
                        LogUtils.info(this.getClass(), "add违反唯一键约束 执行updata sql");
                       // LogUtils.error(this.getClass(), ex.getMessage());
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
