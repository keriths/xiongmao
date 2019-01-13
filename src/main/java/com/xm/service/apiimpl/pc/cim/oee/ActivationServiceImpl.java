package com.xm.service.apiimpl.pc.cim.oee;

import com.google.common.collect.Lists;
import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.DateUtils;
import com.xm.platform.util.LocalCacheUtils;
import com.xm.platform.util.LogUtils;
import com.xm.platform.util.MapUtils;
import com.xm.service.apiimpl.pc.cim.oee.dto.ActivationDate;
import com.xm.service.apiimpl.pc.cim.oee.dto.ActivationEQPIdListRetDTO;
import com.xm.service.apiimpl.pc.cim.oee.dto.ActivationEQPStatusListRetDTO;
import com.xm.service.apiimpl.pc.cim.oee.dto.ActivationStatusDate;
import com.xm.service.constant.Constant;
import com.xm.service.dao.cim.DwrEqpOeeFidsDAO;
import org.joda.time.DateTime;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by fanshuai on 17/10/24.
 */

@Service("ActivationService")
@ApiServiceDoc(name = "CIM7_稼动率（完成-工厂数据已验证）")
public class ActivationServiceImpl {

    @Resource(name="activationDAO")
    private DwrEqpOeeFidsDAO activationDAO;


    @ApiMethodDoc(apiCode = "CIM_ActivationStatusNum",name="EQP类型的状态值显示（完成-工厂数据已验证）")
    public ActivationEQPStatusListRetDTO activationStatusNumList(
            @ApiParamDoc(desc = "厂别：ARRAY,CELL") String factory,
            @ApiParamDoc(desc = "EQP类型，如PHOTO PVD") String eqpId){
        ActivationEQPStatusListRetDTO actType = new ActivationEQPStatusListRetDTO();
//        String cacheKey = "apiCode(CIM_ActivationStatusNum)_factory("+factory+")"+"_"+"eqpId("+eqpId+")";
//        ActivationEQPStatusListRetDTO cacheValue =(ActivationEQPStatusListRetDTO) LocalCacheUtils.getCacheValue(cacheKey);
//        if (cacheValue!=null){
//            return cacheValue;
//        }
        try {
            if (factory!=null){
                factory = factory.toUpperCase();
            }
            List<String> factoryList = Constant.factoryMap.get(factory);
            List<String> showEqpIdList = activationDAO.queryBigEqpTypes(factory);
            if (CollectionUtils.isEmpty(factoryList)) {
                actType.setSuccess(false);
                actType.setErrorMsg("factory参数错误,请传入【" + Constant.factoryMap.keySet() + "】");
                return actType;
            }
            if (!showEqpIdList.contains(eqpId)){
                actType.setSuccess(false);
                actType.setErrorMsg("eqpId参数错误,请传入【" + showEqpIdList + "】");
                return actType;
            }
            String bigEqpType = eqpId;
//            List<String> eqpIdList=Constant.eqpIdMap.get(eqpId);
            Date nowTime = new Date();
            Date beginDate = DateUtils.getBeforDayStartDay(7);
            Date endDate = DateUtils.getBeforDayEndDay(1);
            String beginDateStr = DateUtils.getStrDate(beginDate, "yyyy-MM-dd HH:mm:ss");
            String endDateStr =DateUtils.getStrDate( endDate,"yyyy-MM-dd HH:mm:ss");
            List<String> dayList = DateUtils.getDayStrList(beginDate, endDate);
            long t1  = System.currentTimeMillis();
            List<String> eqpIdList = getEqpIdList(factory, bigEqpType);
            String maxEqpIdList =getMaxEqpId(factoryList,eqpIdList,beginDate, endDate);
            List<ActivationStatusDate.StatusNumberList> activationNumList = activationDAO.queryActivationStatusNumByDay(factoryList, Lists.newArrayList(maxEqpIdList), bigEqpType,beginDateStr, endDateStr);
            long t2 = System.currentTimeMillis();
            LogUtils.info(this.getClass(),"queryActivationStatusNum_usetime["+(t2-t1)+"毫秒factoryList["+factoryList+"]eqpId["+eqpId+"]beginDateStr["+beginDateStr+"]endDateStr["+endDateStr+"]");
//            long t3 = System.currentTimeMillis();
//            int eqpCount = activationDAO.queryActivationStatusNum_dqpidNum(factoryList,eqpId,beginDateStr,endDateStr);
//            LogUtils.info(this.getClass(),"queryActivationStatusNum_dqpidNum_usetime["+(t3-t2)+"毫秒factoryList["+factoryList+"]eqpId["+eqpId+"]beginDateStr["+beginDateStr+"]endDateStr["+endDateStr+"]");
//            if (!CollectionUtils.isEmpty(activationNumList)){
//                for (ActivationStatusDate.StatusNumberList statusDateList : activationNumList){
//                    BigDecimal statusNum = statusDateList.getStatusNum();
//                    statusDateList.setStatusNum(statusNum.divide(new BigDecimal(eqpCount),5,BigDecimal.ROUND_HALF_UP));
//                }
//            }
            Map<String, ActivationStatusDate.StatusNumberList> queryMap = MapUtils.listToMap(activationNumList, "key");
            List<ActivationStatusDate> dtList = new ArrayList<ActivationStatusDate>();
            for (String dayStr:dayList) {
                ActivationStatusDate activationStatusDate= new ActivationStatusDate();
                activationStatusDate.setPeriodDate(dayStr);
                List<ActivationStatusDate.StatusNumberList> list = new ArrayList<ActivationStatusDate.StatusNumberList>();
                for (String status : Constant.statusList) {
                    String key = status + " " + dayStr;
                    ActivationStatusDate.StatusNumberList statusNumber = queryMap.get(key);
                    if (statusNumber == null) {
                        statusNumber = new ActivationStatusDate.StatusNumberList(status,dayStr);
                    }
                    list.add(statusNumber);
                }
                activationStatusDate.setStatusNumberLists(list);
                dtList.add(activationStatusDate);
            }
            actType.setActivationStatusDateList(dtList);
//            if (new DateTime(nowTime).getHourOfDay()<2){
//                LocalCacheUtils.setCacheValue(cacheKey,actType,new DateTime(nowTime).withMillisOfDay(0).withHourOfDay(3).toDate());
//            }else {
//                LocalCacheUtils.setCacheValue(cacheKey,actType,new DateTime(nowTime).plusDays(1).withMillisOfDay(0).toDate());
//            }
            return actType;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            actType.setSuccess(false);
            actType.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return actType;
        }

    }

   @ApiMethodDoc(apiCode = "CIM_ActivationEQPId",name="重点设备稼动显示（完成-工厂数据已验证）")
    public ActivationEQPIdListRetDTO activationIdList(@ApiParamDoc(desc = "厂别：ARRAY,CELL") String factory){
//       String cacheKey = "apiCode(CIM_ActivationEQPId)_factory("+factory+")";
//       ActivationEQPIdListRetDTO cacheValue =(ActivationEQPIdListRetDTO) LocalCacheUtils.getCacheValue(cacheKey);
//       if (cacheValue!=null){
//           return cacheValue;
//       }
       ActivationEQPIdListRetDTO actType = new ActivationEQPIdListRetDTO();
        try {
            List<String> factoryList = Constant.factoryMap.get(factory);
            List<String> showEqpIdList = activationDAO.queryBigEqpTypes(factory);

            if (CollectionUtils.isEmpty(showEqpIdList)) {
                actType.setSuccess(false);
                actType.setErrorMsg("factory参数错误,请传入【" + Constant.factoryEQPStatusListMap.keySet() + "】");
                return actType;
            }
//            Date beginDate = DateUtils.getBeforDayStartDay(0);
//            Date endDate = new Date();
            Date beginDate = DateUtils.getBeforMonthStartDay(1);
            Date endDate = DateUtils.getBeforMonthEndDay(1);
            List<ActivationDate> dateList = new ArrayList<ActivationDate>();

            for (String groupName: showEqpIdList){
                ActivationDate activationDate=new ActivationDate();
                activationDate.setEqpId(groupName);

                List<String> eqpIdList = getEqpIdList(factory,groupName);
                List<ActivationDate.StatusDateList> activationIdList = getStatusDateLists(factoryList, beginDate, endDate, eqpIdList);
                Map<String, ActivationDate.StatusDateList> queryMap = MapUtils.listToMap(activationIdList, "key");

                List<ActivationDate.StatusDateList> dtList = new ArrayList<ActivationDate.StatusDateList>();
                for (String stType : Constant.statusList) {
                    String key = stType;
                    ActivationDate.StatusDateList statusNumber = queryMap.get(key);
                    if (statusNumber == null) {
                        statusNumber = new ActivationDate.StatusDateList(stType);
                    }
                    dtList.add(statusNumber);
                }
                activationDate.setStatusDateList(dtList);
                dateList.add(activationDate);
            }
            actType.setActivationDateList(dateList);
//            if (new DateTime().getDayOfMonth()==1){
//                LocalCacheUtils.setCacheValue(cacheKey,actType,new DateTime().plusDays(1).withMillisOfDay(0).toDate());
//            }else {
//                LocalCacheUtils.setCacheValue(cacheKey,actType,new DateTime().plusMonths(1).withDayOfMonth(1).withMillisOfDay(0).toDate());
//            }
            return actType;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            actType.setSuccess(false);
            actType.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return actType;
        }
    }
    private List<String> getEqpIdList(String factory,String groupName){
        String eqpIdStr = activationDAO.queryEqpIdStr(factory,groupName);
        String [] eqpIdArray = eqpIdStr.split(",");
        List<String> eqpIdList = Lists.newArrayList(eqpIdArray);
        return eqpIdList;
    }

    private List<ActivationDate.StatusDateList> getStatusDateLists(List<String> factoryList, Date beginDate, Date endDate, List<String> eqpIdList) {
        long t1 = System.currentTimeMillis();
        String maxEqpId = getMaxEqpId(factoryList, eqpIdList,beginDate, endDate);
        List<ActivationDate.StatusDateList> ret =activationDAO.queryActivationByEQPIdListAndFactory(factoryList, Lists.newArrayList(maxEqpId), DateUtils.getStrDate(beginDate, "yyyy-MM-dd HH:mm:ss"), DateUtils.getStrDate(endDate, "yyyy-MM-dd HH:mm:ss"));
        long t2 = System.currentTimeMillis();
        LogUtils.info(this.getClass(),"queryActivationEQPId_usetime["+(t2-t1)+"]factoryList["+factoryList+"]groupName["+eqpIdList+"]beginDateStr["+DateUtils.getStrDate(beginDate, "yyyy-MM-dd HH:mm:ss")+"]endDateStr["+DateUtils.getStrDate(endDate,"yyyy-MM-dd HH:mm:ss") +"]");
//        if (CollectionUtils.isEmpty(ret)){
//            return ret;
//        }
//        int eqpCount = activationDAO.queryActivationEQPId_eqpidNum(factoryList,groupName,DateUtils.getStrDate(beginDate, "yyyy-MM-dd HH:mm:ss") ,DateUtils.getStrDate(endDate,"yyyy-MM-dd HH:mm:ss"));
//        long t3 = System.currentTimeMillis();
//        LogUtils.info(this.getClass(),"queryActivationEQPId_eqpidNum_usetime["+(t3-t2)+"]factoryList["+factoryList+"]groupName["+groupName+"]beginDateStr["+DateUtils.getStrDate(beginDate, "yyyy-MM-dd HH:mm:ss")+"]endDateStr["+DateUtils.getStrDate(endDate,"yyyy-MM-dd HH:mm:ss") +"]");
//        for (ActivationDate.StatusDateList statusDateList : ret){
//            BigDecimal statusNum = statusDateList.getStatusNum();
//            statusDateList.setStatusNum(statusNum.divide(new BigDecimal(eqpCount),5,BigDecimal.ROUND_HALF_UP));
//        }
        return ret;
    }

    private String getMaxEqpId(List<String> factoryList, List<String> eqpIdList,Date beginDate, Date endDate) {
        String maxEqpId = "";
        List<String> maxEqpIdList = activationDAO.maxStatusEqpIdList(factoryList, eqpIdList, "", DateUtils.getStrDate(beginDate, "yyyy-MM-dd HH:mm:ss"), DateUtils.getStrDate(endDate, "yyyy-MM-dd HH:mm:ss"), "RUN");
        if(!CollectionUtils.isEmpty(maxEqpIdList)){
            if (maxEqpIdList.size()==1){
                maxEqpId = maxEqpIdList.get(0);
            }else {
                maxEqpIdList = activationDAO.maxStatusEqpIdList(factoryList, eqpIdList, "", DateUtils.getStrDate(beginDate, "yyyy-MM-dd HH:mm:ss"), DateUtils.getStrDate(endDate, "yyyy-MM-dd HH:mm:ss"), "WAT");
                if (!CollectionUtils.isEmpty(maxEqpIdList)){
                    maxEqpId = maxEqpIdList.get(0);
                }
            }
        }
        return maxEqpId;
    }

}
