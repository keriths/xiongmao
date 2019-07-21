package com.xm.service.apiimpl.pc.sap.PanelProductionLineCost;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.LogUtils;
import com.xm.service.apiimpl.pc.sap.PanelProductionLineCost.dto.PanelProductLineCostDTO;
import com.xm.service.dao.cim.PanelProductLineCostDAO;
import com.xm.service.dao.cim.PanelSalesIncomeCostDAO;
import com.xm.webservice.client.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;
import java.lang.reflect.Field;
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
        PanelProductLineCostDTO resultDTO = new PanelProductLineCostDTO();

        ZHBTPROFIT zhbtprofit = new ZHBTPROFIT();
        ZHBTSRCB zhbtsrcb = new ZHBTSRCB();
        try {
            Holder<ZHBTPROFIT> zhbtprofitholder = new Holder<ZHBTPROFIT>();
            Holder<ZHBTSRCB> zhbtsrcbholder = new Holder<ZHBTSRCB>();
            ZHBSENDRESULT2_Service servicefactory = new ZHBSENDRESULT2_Service();
            ZHBSENDRESULT2 zhbsendresult2 = servicefactory.getZHBSENDRESULT2();



            zhbsendresult2.zhbSENDRESULT2(_year, _month, zhbtprofitholder, zhbtsrcbholder);
            zhbtprofit = zhbtprofitholder.value;
            zhbtsrcb = zhbtsrcbholder.value;
        } catch (Exception ex) {

            LogUtils.error(this.getClass(), ex.getMessage());
        }
        CheckProductlineCostDATA(zhbtprofit, _year, _month);

        CheckSaleIncomeCostDATA(zhbtsrcb, _year, _month);


        return resultDTO;
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
