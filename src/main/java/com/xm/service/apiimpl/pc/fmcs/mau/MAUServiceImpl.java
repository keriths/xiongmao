package com.xm.service.apiimpl.pc.fmcs.mau;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.DateUtils;
import com.xm.platform.util.LogUtils;
import com.xm.platform.util.MapUtils;
import com.xm.service.apiimpl.pc.fmcs.mau.dto.MauEquipmentData;
import com.xm.service.apiimpl.pc.fmcs.mau.dto.MauEquipmentDataRetDTO;
import com.xm.service.apiimpl.pc.fmcs.mau.dto.MauRealTimeData;
import com.xm.service.apiimpl.pc.fmcs.mau.dto.MauRealTimeDataRetDTO;
import com.xm.service.constant.Constant;
import com.xm.service.dao.fmcs.MAUADataDAO;
import com.xm.service.dao.fmcs.MAUBDataDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by fanshuai on 17/10/24.
 */
@Service("MAUService")
@ApiServiceDoc(name = "FMCS_新风空调系统(MAU)")
public class MAUServiceImpl {

    @Resource(name="mauEquipmentDataDAO")
    private MAUADataDAO mauADataDAO;
    @Resource(name="mauRealTimeDataDAO")
    private MAUBDataDAO mauBDataDAO;

    @ApiMethodDoc(apiCode = "FMCS_MAUEquipment",name = "新风空调系统设备接口")
    public MauEquipmentDataRetDTO mauEuipmentData(){
        MauEquipmentDataRetDTO resultDto = new MauEquipmentDataRetDTO();
        try {
            List<MauEquipmentData> queryList = mauADataDAO.queryMAUEquipmentData();
            resultDto.setMauEquipmentDataList(queryList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(this.getClass(),"CDADate eclipse",e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }

    @ApiMethodDoc(apiCode = "FMCS_MAURealTime",name = "新风空调系统实时数据接口")
    public MauRealTimeDataRetDTO mauRealTimeData(@ApiParamDoc(desc = "实时数据名称如“温度,露点”") String name){
        MauRealTimeDataRetDTO resultDto = new MauRealTimeDataRetDTO();
        try{
            if(!Constant.TemperaturePointList.contains(name)) {
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("dateType参数错误,请传入【" + Constant.gasNamelist + "】");
                return resultDto;
            }
            List<String> secondList = null;
            Date beginDate = null;
            beginDate = DateUtils.getBeforMinuteStartDay(5);
            Date endDate = new Date();
            secondList = DateUtils.getSecondStrList(beginDate,endDate);
            List<MauRealTimeData.MauRealTimeDetailData> queryList = mauBDataDAO.queryMAURealTimeData(name,beginDate,endDate);
            Map<String,MauRealTimeData.MauRealTimeDetailData> queryMap = MapUtils.listToMap(queryList,"getSecondDate");
            List<MauRealTimeData> mauRealTimeList = new ArrayList<MauRealTimeData>();
            Map<String,MauRealTimeData> minuteDataMap = new HashMap<String, MauRealTimeData>();
            for (String strSecond:secondList){
                String minute=strSecond.substring(0,2)+":00";
                MauRealTimeData minuteData = minuteDataMap.get(minute);
                if (minuteData==null){
                    minuteData=new MauRealTimeData();
                    minuteData.setPeriodDate(minute);
                    minuteData.setMauRealTimeDetailDataList(new ArrayList<MauRealTimeData.MauRealTimeDetailData>());
                    minuteDataMap.put(minute,minuteData);
                    mauRealTimeList.add(minuteData);
                }
                MauRealTimeData.MauRealTimeDetailData mauData = queryMap.get(strSecond);
                if (mauData == null) {
                    mauData = new MauRealTimeData.MauRealTimeDetailData(minute,strSecond);
                }
                minuteData.getMauRealTimeDetailDataList().add(mauData);
            }
            resultDto.setMauRealTimeDataList(mauRealTimeList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(this.getClass(),"pcwRealTime eclipse",e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }
}
