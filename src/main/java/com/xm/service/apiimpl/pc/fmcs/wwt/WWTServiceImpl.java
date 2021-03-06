package com.xm.service.apiimpl.pc.fmcs.wwt;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.DateUtils;
import com.xm.platform.util.LogUtils;
import com.xm.platform.util.MapUtils;
import com.xm.service.apiimpl.pc.fmcs.water.dto.TapWaterRealTimeData;
import com.xm.service.apiimpl.pc.fmcs.wwt.dto.*;
import com.xm.service.constant.Constant;
import com.xm.service.dao.factory.fmcs.FactoryWwtaDataDAO;
import com.xm.service.dao.fmcs.WwtaDataDAO;
import com.xm.service.dao.fmcs.WwtbDataDAO;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by fanshuai on 17/10/24.
 */
@Service("WWTService")
@ApiServiceDoc(name = "FMCS11_废水处理系统(WWT)")
public class WWTServiceImpl {
    @Resource
    private WwtaDataDAO wwtaDataDAO;
    @Resource
    private WwtbDataDAO wwtbDataDAO;
    @Resource
    private FactoryWwtaDataDAO factoryWwtaDataDAO;

    @ApiMethodDoc(apiCode = "FMCS_wwtaDataList",name = "设备状态接口")
    public WwtaDataRetDTO wwtaDataList(){
        WwtaDataRetDTO retDTO=new WwtaDataRetDTO();
        try {
            List<WwtaData> dataList = wwtaDataDAO.queryWwtaDataList();
            retDTO.setWwtaDataList(dataList);
            return retDTO;
        }catch (Exception e) {
            LogUtils.error(getClass(), e);
            retDTO.setSuccess(false);
            retDTO.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return retDTO;
        }
    }

    @ApiMethodDoc(apiCode = "FMCS_wwtbDataList",name = "实时数据接口")
    public WwtbDataRetDTO wwtbDataList(@ApiParamDoc(desc = "编码如：PH,F,PO4-P（必填）")String code){
        WwtbDataRetDTO retDTO=new WwtbDataRetDTO();
        try {
            if (!Constant.wwtbDataCodeList.contains(code)){
                retDTO.setSuccess(false);
                retDTO.setErrorMsg("code参数错误,请传入【" + Constant.wwtbDataCodeList + "】");
                return retDTO;
            }

            List<String> dateSecondList = null;
            Date beginDate = null;
            Date endDate = new Date();
            beginDate = DateUtils.getBeforMinuteStartDay(5);
            int midint = 20;
            float midfloat = 7;
            String dataType ="integer";
            if ("PH".equals(code)){
                midfloat=7f;
                dataType="float";
            }else if ("F".equals(code)){
                midfloat=4.5f;
                dataType="float";
            }else if ("PO4-P".equals(code)){
                midfloat=0.5f;
                dataType="float";
            }else if ("CODcr".equals(code)){
                midint=60;
                dataType="integer";
            }else if ("T-N".equals(code)){
                midint=20;
                dataType="integer";
            }else if ("C1".equals(code)){
                midint=50;
                dataType="integer";
            }else if ("Cu".equals(code)){
                midfloat=1f;
                dataType="float";
            }

            dateSecondList = DateUtils.getSecondStrList(beginDate, endDate);

            List<WwtbData.WwtbDetailData> dataList = wwtbDataDAO.queryWwtbDataList(code,beginDate,endDate);
            Map<String, WwtbData.WwtbDetailData> dataMap = MapUtils.listToMap(dataList, "getDataDate");
            List<WwtbData> wwtbDataList = new ArrayList<WwtbData>();
            Map<String,WwtbData> minuteDataMap = new HashMap<String, WwtbData>();
            for (String strSecond:dateSecondList){
                String minute=strSecond.substring(0,2)+":00";
                WwtbData minuteData = minuteDataMap.get(minute);
                if (minuteData==null){
                    minuteData=new WwtbData();
                    minuteData.setPeriodDate(minute);
                    minuteData.setWwtbDetailDataList(new ArrayList<WwtbData.WwtbDetailData>());
                    minuteDataMap.put(minute,minuteData);
                    wwtbDataList.add(minuteData);
                }
                WwtbData.WwtbDetailData wwtbDetailData=dataMap.get(strSecond);
                if (wwtbDetailData == null) {
                    //wwtbDetailData = new WwtbData.WwtbDetailData(minute,strSecond);
                    DateTime d = new DateTime();
                    int curMinuteNum = d.getMinuteOfHour();
                    int curSecondNum = d.getSecondOfMinute();
                    int dataMinuteNum = Integer.parseInt(strSecond.substring(0,2));
                    int dataSecondNum = Integer.parseInt(strSecond.substring(3,5));
                    if (curMinuteNum == dataMinuteNum && dataSecondNum>curSecondNum){
                        continue;
                    }
                    wwtbDetailData = new WwtbData.WwtbDetailData(minute,strSecond,midint,midfloat,dataType);
                }
                minuteData.getWwtbDetailDataList().add(wwtbDetailData);
            }
            retDTO.setWwtbDataList(wwtbDataList);
            return retDTO;

        } catch (Exception e) {
            LogUtils.error(getClass(), e);
            retDTO.setSuccess(false);
            retDTO.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return retDTO;
        }

    }

//
//    @ApiMethodDoc(apiCode = "FMCS_SyncWwtaData",name = "同步设备实时状态")
//    public void syncWwtaData(){
//        try {
//            List<SyncWwtaData> queryList = factoryWwtaDataDAO.queryWwtaDataList();
//            for(SyncWwtaData wwtaData:queryList){
//                SyncWwtaData data=wwtaDataDAO.queryStatusByKey(wwtaData.getKey());
//                if(data==null){
//                    wwtaDataDAO.insertStatusData(wwtaData);
//                }else {
//                    wwtaDataDAO.updateStatusData(wwtaData);
//                }
//            }
//
//        }catch (Exception e) {
//            LogUtils.error(this.getClass(), e);
//        }
//    }

}
