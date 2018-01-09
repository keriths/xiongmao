package com.xm.service.apiimpl.pc.fmcs.rcu;

import com.google.common.collect.Lists;
import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.DateUtils;
import com.xm.platform.util.LogUtils;
import com.xm.platform.util.MapUtils;
import com.xm.service.apiimpl.pc.fmcs.rcu.dto.*;
import com.xm.service.constant.Constant;
import com.xm.service.dao.fmcs.RcuRealTimeDataDAO;
import com.xm.service.dao.fmcs.RcuSystemDataDAO;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by fanshuai on 17/10/24.
 */
@Service("RCUService")
@ApiServiceDoc(name = "FMCS08_热回收空调系统(RCU)(完成)")
public class RCUServiceImpl {

    @Resource(name="rcuRealTimeDataDAO")
    private RcuRealTimeDataDAO rcuRealTimeDataDAO;
    @Resource(name="rcuSystemDataDAO")
    private RcuSystemDataDAO rcuSystemDataDAO;

    @ApiMethodDoc(apiCode = "FMCS_RCUSystem",name = "热回收空调系统接口")
    public RcuSystemDataRetDTO rcuSystemData(@ApiParamDoc(desc = "厂别,如RCU,4A,4B") String systemType){
        RcuSystemDataRetDTO resultDto = new RcuSystemDataRetDTO();
        try {
            List<String> nameList = Constant.rcuSystemListMap.get(systemType);
            if(!Constant.rcuSystemListMap.containsKey(systemType)){
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("systemType参数错误,请传入【" + Constant.rcuSystemListMap.keySet() + "】");
                return resultDto;
            }

            List<RcuSystemData> queryList = rcuSystemDataDAO.queryRCUSystemData(systemType);
            Map<String,RcuSystemData> queryMap = MapUtils.listToMap(queryList,"getSystemName");
            List<RcuSystemData> systemDateList = new ArrayList<RcuSystemData>();
            for(String name:nameList){
                //RcuSystemData rcuData = new RcuSystemData();
                RcuSystemData rcuData = queryMap.get(name);
                /*if(!CollectionUtils.isEmpty(queryMap)){
                    rcuData = queryMap.get(name);
                }*/
                if(rcuData == null){
                    rcuData = new RcuSystemData(name);
                }
                systemDateList.add(rcuData);
            }
            resultDto.setRcuSystemDataList(systemDateList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }

    @ApiMethodDoc(apiCode = "FMCS_RCURealTime",name = "热回收空调系统实时数据接口")
    public RcuRealTimeDataRetDTO rcuRealTimeData(){
        RcuRealTimeDataRetDTO resultDto = new RcuRealTimeDataRetDTO();
        try{
            List<String> secondList = null;
            Date beginDate = null;
            beginDate = DateUtils.getBeforMinuteStartDay(5);
            Date endDate = new Date();
            secondList = DateUtils.getSecondStrList(beginDate,endDate);
            List<String> systemTypeList = Lists.newArrayList("RCU");
            List<RcuRealTimeData.RcuRealTimeDetailData> queryList = rcuRealTimeDataDAO.queryRCURealTimeData(systemTypeList,beginDate,endDate);
            Map<String,RcuRealTimeData.RcuRealTimeDetailData> queryMap = MapUtils.listToMap(queryList,"getSecondDate");
            List<RcuRealTimeData> rcuRealTimeList = new ArrayList<RcuRealTimeData>();
            Map<String,RcuRealTimeData> minuteDataMap = new HashMap<String, RcuRealTimeData>();
            for (String strSecond:secondList){
                String minute=strSecond.substring(0,2)+":00";
                RcuRealTimeData minuteData = minuteDataMap.get(minute);
                if (minuteData==null){
                    minuteData=new RcuRealTimeData();
                    minuteData.setPeriodDate(minute);
                    minuteData.setRcuRealTimeDetailDataList(new ArrayList<RcuRealTimeData.RcuRealTimeDetailData>());
                    minuteDataMap.put(minute,minuteData);
                    rcuRealTimeList.add(minuteData);
                }
                RcuRealTimeData.RcuRealTimeDetailData rcuData = queryMap.get(strSecond);
                if (rcuData == null) {
                    rcuData = new RcuRealTimeData.RcuRealTimeDetailData(minute,strSecond);
                }
                minuteData.getRcuRealTimeDetailDataList().add(rcuData);
            }
            resultDto.setRcuRealTimeDataList(rcuRealTimeList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }

    @ApiMethodDoc(apiCode = "FMCS_RCUData",name = "热回收空调系统所有接口数据返回汇总")
    public RcuDataRetDTO mauData(@ApiParamDoc(desc = "厂别,如RCU,4A,4B") String systemType){
        RcuDataRetDTO retDTO=new RcuDataRetDTO();
        try {
            if(!Constant.rcuSystemListMap.containsKey(systemType)){
                retDTO.setSuccess(false);
                retDTO.setErrorMsg("systemType参数错误,请传入【" + Constant.rcuSystemListMap.keySet() + "】");
                return retDTO;
            }

            RcuRealTimeDataRetDTO rcuRealTimeDataRetDTO=rcuRealTimeData();
            RcuSystemDataRetDTO rcuSystemDataRetDTO=rcuSystemData(systemType);

            retDTO.setRcuRealTimeDataList(rcuRealTimeDataRetDTO.getRcuRealTimeDataList());
            retDTO.setRcuSystemDataList(rcuSystemDataRetDTO.getRcuSystemDataList());

            return retDTO;

        }catch (Exception e){
            LogUtils.error(getClass(), e);
            retDTO.setSuccess(false);
            retDTO.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return retDTO;
        }
    }
}
