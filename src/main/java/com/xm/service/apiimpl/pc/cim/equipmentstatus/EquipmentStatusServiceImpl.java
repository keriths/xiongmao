package com.xm.service.apiimpl.pc.cim.equipmentstatus;

import com.xm.platform.util.DateUtils;
import com.xm.platform.util.LogUtils;
import com.xm.platform.util.MapUtils;
import com.xm.service.apiimpl.pc.cim.equipmentstatus.dto.*;
import com.xm.service.apiimpl.pc.cim.oee.dto.ActivationStatusDate;
import com.xm.service.constant.Constant;
import com.xm.service.dao.cim.DwrEquipmentStatusFidsDAO;
import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.service.dao.cim.DwrEquipmentThroughputFidsDAO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by fanshuai on 17/10/24.
 */
@Service("EquipmentRealTimeStatusService")
@ApiServiceDoc(name = "CIM1_设备实时状态（设备比例公式还没提供，目前数据随便用了一个公式,还需要和消息对接联调）")
public class EquipmentStatusServiceImpl {
    @Resource(name = "dwrEquipmentStatusFidsDAO")
    public DwrEquipmentStatusFidsDAO dwrEquipmentStatusFidsDAO;
    @Resource(name = "dwrEquipmentThroughputFidsDAO")
    public DwrEquipmentThroughputFidsDAO dwrEquipmentThroughputFidsDAO;

    @ApiMethodDoc(apiCode = "CIM_EquipmentStatus",name = "设备状态接口")
    public EquipmentStatusDataRetDTO equipmentStatus(@ApiParamDoc(desc = "厂别名称如Array,Cell,CF,SL-OC")String factory){
        EquipmentStatusDataRetDTO resultDto = new EquipmentStatusDataRetDTO();
        try {
            if (!Constant.factoryLists.contains(factory)){
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("factory参数错误,请传入【" + Constant.factoryLists + "】");
                return resultDto;
            }
            List<EquipmentStatusData> queryList = dwrEquipmentStatusFidsDAO.queryStatusData(factory);
            resultDto.setEquipmentStatusDataList(queryList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }

    @ApiMethodDoc(apiCode = "CIM_EquipmentData",name = "设备汇总")
    public EquipmentDataRetDTO equipmentData(){
        EquipmentDataRetDTO resultDto = new EquipmentDataRetDTO();

        try {
            List<EquipmentDataDto> dataList = new ArrayList<EquipmentDataDto>();
            for(String factory:Constant.factoryLists){
                List<EquipmentDataDto.EquipmentData> queryList = dwrEquipmentStatusFidsDAO.queryStatus(factory);
                EquipmentDataDto equipmentDataDto = new EquipmentDataDto();
                equipmentDataDto.setEquipmentDataList(queryList);
                dataList.add(equipmentDataDto);
                resultDto.setEquipmentDataList(dataList);
            }
            return resultDto;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }

    @ApiMethodDoc(apiCode = "CIM_ThroughputData",name = "过货量推移数据接口(OK 几个数值的公式还要替换)")
    public EquipmentThroughputDataRetDTO equipmentThroughput(@ApiParamDoc(desc = "厂别名称如Array,Cell,CF,SL-OC")String factory){
        EquipmentThroughputDataRetDTO resultDto = new EquipmentThroughputDataRetDTO();
        try{
            if (!Constant.factoryLists.contains(factory)){
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("factory参数错误,请传入【" + Constant.factoryLists + "】");
                return resultDto;
            }
            List<String> dateList = null;
            Date beginDate = DateUtils.getBeforHourStartDay(11);
            Date endDate = new Date();
            dateList = DateUtils.getHourStrList(beginDate,endDate);
            List<EquipmentThroughputData> dataList=dwrEquipmentThroughputFidsDAO.queryThroughputData(factory,beginDate,endDate);
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


    @ApiMethodDoc(apiCode = "CIM_SyncEqptState",name = "设备实时状态数据同步(同步数据使用，前端不用)")
    public void equipmentStatusUpdate(String msgContext){
        try {
            EquipmentStatusData equipmentStatusData=new EquipmentStatusData();
            Document document= Jsoup.parse(msgContext);
            String factoryName = document.getElementsByTag("ORGNAME").text();
            String eqptId = document.getElementsByTag("EQPTID").text();
            String eqptState = document.getElementsByTag("EQPTSTATE").text();
            EquipmentStatusData eqptData=dwrEquipmentStatusFidsDAO.queryStatusByKey(eqptId.toUpperCase());
            if (eqptData==null){
                eqptData = new EquipmentStatusData();
                eqptData.setFactory(factoryName);
                eqptData.setKey(eqptId);
                eqptData.setVal(eqptState);
                dwrEquipmentStatusFidsDAO.insertStatusData(eqptData);
            }else {
                eqptData.setVal(eqptState);
                dwrEquipmentStatusFidsDAO.updateStatusData(eqptData);
            }
//            for(Element el:document.select("Beader")){
//                String factory=el.select("ORGNAME").text();
//                equipmentStatusData.setFactory(factory);
//
//            }
//            for(Element el:document.select("Body")){
//                String key=el.select("EQPTID").text();
//                String val=el.select("EQPTSTATE").text();
//                equipmentStatusData.setKey(key.toUpperCase());
//                equipmentStatusData.setVal(val);
//
//                EquipmentStatusData e=dwrEquipmentStatusFidsDAO.queryStatusByKey(key.toUpperCase());
//                if (e==null){
//                    dwrEquipmentStatusFidsDAO.insertStatusData(equipmentStatusData);
//                }else{
//                    dwrEquipmentStatusFidsDAO.updateStatusData(equipmentStatusData);
//                }
//            }

        } catch (Exception e) {
            e.printStackTrace();
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
