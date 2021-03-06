package com.xm.service.apiimpl.pc.fmcs.pcw;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.DateUtils;
import com.xm.platform.util.LogUtils;
import com.xm.platform.util.MapUtils;
import com.xm.service.apiimpl.pc.fmcs.pcw.dto.*;
import com.xm.service.constant.Constant;
import com.xm.service.dao.factory.fmcs.FactoryPCWDataDAO;
import com.xm.service.dao.fmcs.PCWDataDAO;
import com.xm.service.dao.fmcs.PCWHumitureDataDAO;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by fanshuai on 17/10/24.
 */
@Service("PCWService")
@ApiServiceDoc(name = "FMCS05_工艺冷却水系统(PCW)(完成)")
public class PCWServiceImpl {

    @Resource(name="pcwHumitureDataDAO")
    private PCWHumitureDataDAO pcwHumitureDataDAO;
    @Resource(name="pcwDataDAO")
    private PCWDataDAO pcwDataDAO;
    @Resource(name = "factoryPCWDataDAO")
    private FactoryPCWDataDAO factoryPCWDataDAO;

    @ApiMethodDoc(apiCode = "FMCS_PCWData",name = "工艺冷却水系统设备接口")
    public PcwEquipmentDataRetDTO pcwEquipmentData(){
        PcwEquipmentDataRetDTO resultDto = new PcwEquipmentDataRetDTO();
        try {
            List<PcwEquipmentData> queryList = pcwDataDAO.queryPCWData();
            resultDto.setPcwEquipmentDataList(queryList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }

    @ApiMethodDoc(apiCode = "FMCS_PCWRealTime",name = "工艺冷却水系统实时数据接口")
    public HumiturePressureDataRetDTO humiturePressureDataRetDTO(@ApiParamDoc(desc = "系统名称,如“PCW-4A-101,PCW-4A-102”") String system){
        HumiturePressureDataRetDTO resultDto = new HumiturePressureDataRetDTO();
        try{
            if(!Constant.pcwEquipmentList.contains(system)) {
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("system参数错误,请传入【" + Constant.pcwEquipmentList + "】");
                return resultDto;
            }
            //Date beginDate = DateUtils.getBeforMinuteStartDay(5);
            List<String> secondList = null;
            Date beginDate = null;
            beginDate = DateUtils.getBeforMinuteStartDay(5);
            Date endDate = new Date();
            secondList = DateUtils.getSecondStrList(beginDate,endDate);
            List<HumiturePressureData.HumiturePressureRealTimeDate> queryList = pcwHumitureDataDAO.queryPcwRealTimeDate(system,beginDate,endDate);
            Map<String,HumiturePressureData.HumiturePressureRealTimeDate> queryMap = MapUtils.listToMap(queryList,"getSecondDate");
            List<HumiturePressureData> humiturePressureList = new ArrayList<HumiturePressureData>();
            Map<String,HumiturePressureData> minuteDataMap = new HashMap<String, HumiturePressureData>();
            for (String strSecond:secondList){
                String minute=strSecond.substring(0,2)+":00";
                HumiturePressureData minuteData = minuteDataMap.get(minute);
                if (minuteData==null){
                    minuteData=new HumiturePressureData();
                    minuteData.setPeriodDate(minute);
                    minuteData.setHumiturePressureRealTimeDateList(new ArrayList<HumiturePressureData.HumiturePressureRealTimeDate>());
                    minuteDataMap.put(minute,minuteData);
                    humiturePressureList.add(minuteData);
                }
                HumiturePressureData.HumiturePressureRealTimeDate pcwData = queryMap.get(strSecond);
                if (pcwData == null) {
                    DateTime d = new DateTime();
                    int curMinuteNum = d.getMinuteOfHour();
                    int curSecondNum = d.getSecondOfMinute();
                    int dataMinuteNum = Integer.parseInt(strSecond.substring(0,2));
                    int dataSecondNum = Integer.parseInt(strSecond.substring(3,5));
                    if (curMinuteNum == dataMinuteNum && dataSecondNum>curSecondNum){
                        continue;
                    }
                    pcwData = new HumiturePressureData.HumiturePressureRealTimeDate(minute,strSecond);
                }
                minuteData.getHumiturePressureRealTimeDateList().add(pcwData);
            }
            resultDto.setHumiturePressureDataList(humiturePressureList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(this.getClass(),"pcwRealTime eclipse",e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }

    @ApiMethodDoc(apiCode = "FMCS_PCWSystemList",name ="工艺冷却水系统列表" )
    public PcwTabRetDTO queryPcwSystem(){
        PcwTabRetDTO resultDto = new PcwTabRetDTO();
        List<PcwTabRetDTO.PcwSystem> dataDto = new ArrayList<PcwTabRetDTO.PcwSystem>();
        List<String> dataList = Constant.pcwEquipmentList;
        Iterator it = dataList.iterator();
        while(it.hasNext()){
            PcwTabRetDTO.PcwSystem data = new PcwTabRetDTO.PcwSystem();
            String str = (String)it.next();
            data.setSystem(str);
            dataDto.add(data);
        }
        resultDto.setPcwSystemList(dataDto);
        return resultDto;
    }

    @ApiMethodDoc(apiCode = "FMCS_SyncPcwEquipmentData",name = "同步设备实时状态")
    public void syncPcwEquipmentData(){
        try {
            List<SyncPcwEquipmentData> queryList = factoryPCWDataDAO.queryPCWData();
            for(SyncPcwEquipmentData equipmentData:queryList){
                SyncPcwEquipmentData data=pcwDataDAO.queryStatusByKey(equipmentData.getKey());
                if(data==null){

                    pcwDataDAO.insertStatusData(equipmentData);
                }else {
                    pcwDataDAO.updateStatusData(equipmentData);
                }
            }

        }catch (Exception e) {
            LogUtils.error(this.getClass(), e);
        }
    }
}
