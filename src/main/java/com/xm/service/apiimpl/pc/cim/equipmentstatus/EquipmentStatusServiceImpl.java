package com.xm.service.apiimpl.pc.cim.equipmentstatus;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.xm.platform.util.DateUtils;
import com.xm.platform.util.LogUtils;
import com.xm.platform.util.MapUtils;
import com.xm.service.apiimpl.pc.cim.equipmentstatus.dto.*;
import com.xm.service.constant.Constant;
import com.xm.service.dao.cim.DwrEquipmentStatusFidsDAO;
import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.service.dao.cim.DwsProductOutputFidsHDAO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by fanshuai on 17/10/24.
 */
@Service("EquipmentRealTimeStatusService")
@ApiServiceDoc(name = "CIM1_设备实时状态（设备比例公式还没提供，目前数据随便用了一个公式,还需要和消息对接联调）")
public class EquipmentStatusServiceImpl {
    @Resource(name = "dwrEquipmentStatusFidsDAO")
    public DwrEquipmentStatusFidsDAO dwrEquipmentStatusFidsDAO;
    @Resource
    public DwsProductOutputFidsHDAO dwsProductOutputFidsHDAO;

    @ApiMethodDoc(apiCode = "CIM_EquipmentStatus",name = "设备状态接口")
    public EquipmentStatusDataRetDTO equipmentStatus(@ApiParamDoc(desc = "厂别名称如ARRAY,CELL,CF,SL-OC")String factory){
        EquipmentStatusDataRetDTO resultDto = new EquipmentStatusDataRetDTO();
        try {
            Map<String,List<String>> factoryMap = MapUtils.newMap(
                    "ARRAY", Lists.newArrayList("ARRAY"),
                    "CELL", Lists.newArrayList("CELL"),
                    "CF", Lists.newArrayList("CF"),
                    "SL-OC", Lists.newArrayList("SL","OC")
            );
            List<String> factoryList = factoryMap.get(factory);
            if (factoryList==null){
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("factory参数错误,请传入【" + factoryMap.keySet() + "】");
                return resultDto;
            }
            List<EquipmentStatusData> queryList = dwrEquipmentStatusFidsDAO.queryStatusData(factoryList);
            resultDto.setEquipmentStatusDataList(queryList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }
    @ApiMethodDoc(apiCode = "CIM_ThroughputData",name = "过货量推移数据接口(OK 几个数值的公式还要替换)")
    public EquipmentThroughputDataRetDTO equipmentThroughput(@ApiParamDoc(desc = "厂别名称如ARRAY,CELL,CF,SL-OC")String factory){
        EquipmentThroughputDataRetDTO resultDto = new EquipmentThroughputDataRetDTO();
        try{
            List<String> showFactoryList = Lists.newArrayList("ARRAY","CELL","CF","SL-OC");
            Map<String,List<String>> factoryMap = MapUtils.newMap(
                    "ARRAY", Lists.newArrayList("ARRAY"),
                    "CELL", Lists.newArrayList("CELL"),
                    "CF", Lists.newArrayList("CF"),
                    "SL-OC", Lists.newArrayList("OC")
            );
            if (!showFactoryList.contains(factory)){
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("factory参数错误,请传入【" + Constant.showFactoryList + "】");
                return resultDto;
            }
            List<String> factoryList = factoryMap.get(factory);
            List<String> dateList = null;
            Date beginDate = DateUtils.getBeforHourStartDay(11);
            Date endDate = new Date();
            dateList = DateUtils.getHourStrList(beginDate,endDate);
//            List<EquipmentThroughputData> dataList= dwsProductOutputFidsHDAO.queryThroughputData(null,factoryList,beginDate,endDate,null);
            String queryFactory = factory;
            if ("SL-OC".equals(factory)){
                queryFactory="SL/OC";
            }
            List<EquipmentThroughputData> dataList= dwsProductOutputFidsHDAO.queryOutPutH(queryFactory);
            Map<String,EquipmentThroughputData> dataMap= MapUtils.listToMap(dataList,"getDataDate");
            List<EquipmentThroughputData> throughputList =new ArrayList<EquipmentThroughputData>();
            for (String str:dateList){
                EquipmentThroughputData throughputDate =dataMap.get(str);
                if(throughputDate ==null){
                    throughputDate =new EquipmentThroughputData(str);
                }
                throughputList.add(throughputDate);
            }
            resultDto.setEquipmentThroughputDataList(throughputList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }


    //同步数据使用的接口
    public void equipmentStatusUpdate(String msgContext){
        try {
//            LogUtils.info("TibcoLog",msgContext);
            Document document= Jsoup.parse(msgContext);
            String MESSAGENAME = document.getElementsByTag("MESSAGENAME").text();
            if (!"EQStateReport".equals(MESSAGENAME)){
//                LogUtils.info(this.getClass(),"receivednotstate :"+msgContext);
                return;
            }
//            LogUtils.info(Tibrvlisten.class,msgContext);
            String factoryName = document.getElementsByTag("ORGNAME").text();
            String eqptId = document.getElementsByTag("EQPTID").text();
            String eqptState = document.getElementsByTag("EQPTSTATE").text();
            String state = document.getElementsByTag("STATE").text();
            String eqptType = document.getElementsByTag("EQPTTYPE").text();
            if (!StringUtils.hasText(eqptId)){
                eqptId = document.getElementsByTag("PORTID").text();
            }
            if (!StringUtils.hasText(eqptState)){
                eqptState = state;
            }
            if ( !StringUtils.hasText(factoryName) || !StringUtils.hasText(eqptId) || !StringUtils.hasText(eqptState)){
//                LogUtils.info(Tibrvlisten.class,"receivednullmsg :"+msgContext);
                return;
            }
            factoryName = factoryName.toUpperCase();
            eqptId = eqptId.toUpperCase();
            EquipmentStatusData eqptData = null;
            try {
                eqptData=dwrEquipmentStatusFidsDAO.queryStatusByKey(factoryName,eqptId);
            }catch (Exception e){
                LogUtils.error(this.getClass(),"[DWR_EQUIPMENT_STATUS_FIDS]queryStatusByKey factoryName"+factoryName+" eqptId"+eqptId+" exception",e);
            }
            if (eqptData==null){
                eqptData = new EquipmentStatusData();
                eqptData.setFactory(factoryName);
                eqptData.setKey(eqptId);
                eqptData.setVal(eqptState);
                eqptData.setEqptType(eqptType);
                try {
                    dwrEquipmentStatusFidsDAO.insertStatusData(eqptData);
                }catch (Exception e){
                    LogUtils.error(this.getClass(),"[DWR_EQUIPMENT_STATUS_FIDS]insertStatusData exception document="+document+" eqptData"+ JSONObject.toJSONString(eqptData),e);
                }
            }else {
                eqptData.setVal(eqptState);
                eqptData.setEqptType(eqptType);
                try {
                    dwrEquipmentStatusFidsDAO.updateStatusData(eqptData);
                }catch (Exception e){
                    LogUtils.error(this.getClass(),"[DWR_EQUIPMENT_STATUS_FIDS]updateStatusData exception document="+document+" eqptData"+ JSONObject.toJSONString(eqptData),e);
                }
            }
        } catch (Exception e) {
            LogUtils.error(this.getClass(),"",e);
        }
    }

    public static void main(String[] args){
        String msg = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<Request>\n" +
                "  <Header>\n" +
                "    <MESSAGENAME>EQStateReport</MESSAGENAME>\n" +
                "    <TRANSACTIONID>3d60e55a-b836-4032-8982-07e343b031c7</TRANSACTIONID>\n" +
                "    <ORGRRN>20</ORGRRN>\n" +
                "    <ORGNAME>SL</ORGNAME>\n" +
                "    <USERNAME>eis</USERNAME>\n" +
                "  </Header>\n" +
                "  <Body>\n" +
                "    <EQPTID>BMR304NM</EQPTID>\n" +
                "    <EQPTSTATE>MAN</EQPTSTATE>\n" +
                "    <EQPTTYPE>EQ</EQPTTYPE>\n" +
                "  </Body>\n" +
                "</Request>";
        new EquipmentStatusServiceImpl().equipmentStatusUpdate(msg);
    }
}
