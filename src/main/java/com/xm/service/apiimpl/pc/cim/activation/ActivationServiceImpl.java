package com.xm.service.apiimpl.pc.cim.activation;

import com.google.common.collect.Lists;
import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.DateUtils;
import com.xm.platform.util.MapUtils;
import com.xm.service.apiimpl.pc.cim.activation.dto.ActivationDate;
import com.xm.service.apiimpl.pc.cim.activation.dto.ActivationEQPIdListRetDTO;
import com.xm.service.apiimpl.pc.cim.activation.dto.ActivationEQPStatusListRetDTO;
import com.xm.service.apiimpl.pc.cim.activation.dto.ActivationStatusDate;
import com.xm.service.dao.cim.ActivationDAO;
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
@ApiServiceDoc(name = "CIM_稼动率")
public class ActivationServiceImpl {

    private List<String> statusList = Lists.newArrayList("RUN","TRB","WAIT","MAN","MNT");

    private static Map<String,List<String>> factoryEQPStatusListMap = MapUtils.newMap(
            "Array", Lists.newArrayList("PHOTO","PVD","CVD","WET","DE"),
            "Cell",Lists.newArrayList("PI","FDV","ODF","HSW","KOL"),
            "CF",Lists.newArrayList("BM","ITO","PS","RGB","RML"),
            "SL-OC",Lists.newArrayList("MBD","POL","OLB","MLB","Aging")
    );

    @Resource(name="activationDAO")
    private ActivationDAO activationDAO;


    @ApiMethodDoc(apiCode = "CIM_ActivationStatusNum",name="EQP类型的状态值显示")
    public ActivationEQPStatusListRetDTO activationStatusNumList(@ApiParamDoc(desc = "厂别：如Array Cell") String factory, @ApiParamDoc(desc = "EQP类型，如PHOTO PVD") String eqpId){
        ActivationEQPStatusListRetDTO actType = new ActivationEQPStatusListRetDTO();

        try {
            List<String> eqpIdList = factoryEQPStatusListMap.get(factory);
            if (CollectionUtils.isEmpty(eqpIdList)) {
                actType.setSuccess(false);
                actType.setErrorMsg("factory参数错误,请传入【" + factoryEQPStatusListMap.keySet() + "】");
                return actType;
            }
            Date beginDate = DateUtils.getBeforHourStartDay(11);
            Date endDate = new Date();
            List<String> hourList = DateUtils.getHourStrList(beginDate,endDate);
            List<ActivationStatusDate.StatusNumberList> activationNumList = activationDAO.queryActivationStatusNum(factory, eqpId, beginDate, endDate);
            Map<String, ActivationStatusDate.StatusNumberList> queryMap = MapUtils.listToMap(activationNumList, "key");
            List<ActivationStatusDate> dtList = new ArrayList<ActivationStatusDate>();
            for (String hour:hourList) {
                ActivationStatusDate activationStatusDate= new ActivationStatusDate();
                activationStatusDate.setPeriodDate(hour);
                List<ActivationStatusDate.StatusNumberList> list = new ArrayList<ActivationStatusDate.StatusNumberList>();
                for (String stNumber : statusList) {
                    String key = stNumber + " " + hour;
                    ActivationStatusDate.StatusNumberList statusNumber = queryMap.get(key);
                    if (statusNumber == null) {
                        statusNumber = new ActivationStatusDate.StatusNumberList(stNumber,hour);
                    }
                    list.add(statusNumber);
                }
                activationStatusDate.setStatusNumberLists(list);
                dtList.add(activationStatusDate);
            }
            actType.setActivationStatusDateList(dtList);
            return actType;
       /* ActivationDate actDate = new ActivationDate();
        try{
            List<String> eqpIdList = factoryEQPStatusListMap.get(factory);
            if (eqpIdList==null) {
                actType.setSuccess(false);
                actType.setErrorMsg("factory参数错误,请传入【"+factoryEQPStatusListMap.keySet()+ "】");
                return actType;
            }
            if(!eqpIdList.contains(eqpId)){
                actType.setSuccess(false);
                actType.setErrorMsg("eqpId参数错误,请传入【"+eqpIdList+"】");
                return actType;
            }
            Date startTime = DateUtils.getBeforHourStartDay(11);
            Date endTime = new Date();
            List<String> hourList = DateUtils.getHourStrList(startTime,endTime);
            List<ActivationDate.StatusDateList> queryStatusNumList = activationDAO.queryActivationStatusNum(factory,eqpId,startTime,endTime);
            Map<String,ActivationDate.StatusDateList> queryStatusNumMap = MapUtils.listToMap(queryStatusNumList,"getPeriodDate");
            List<ActivationDate.StatusDateList> activationStatusNumDetailList = new ArrayList<ActivationDate.StatusDateList>();
            for(String hour:hourList){
                ActivationDate.StatusDateList stNumDetail = null;
                if(!CollectionUtils.isEmpty(queryStatusNumMap)){
                    stNumDetail = queryStatusNumMap.get(hour);
                }
                if(stNumDetail==null){
                    stNumDetail = new ActivationDate.StatusDateList(hour);
                }
                activationStatusNumDetailList.add(stNumDetail);
            }
            actDate.setStatusDateListList(activationStatusNumDetailList);
            return actType;*/

        }catch (Exception e){
            actType.setSuccess(false);
            actType.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return actType;
        }

    }

   @ApiMethodDoc(apiCode = "CIM_ActivationEQPId",name="设备稼动率显示")
    public ActivationEQPIdListRetDTO activationIdList(@ApiParamDoc(desc = "厂别：如Array Cell") String factory){
       ActivationEQPIdListRetDTO actType = new ActivationEQPIdListRetDTO();
        try {
            List<String> eqpIdList = factoryEQPStatusListMap.get(factory);
            if (CollectionUtils.isEmpty(eqpIdList)) {
                actType.setSuccess(false);
                actType.setErrorMsg("factory参数错误,请传入【" + factoryEQPStatusListMap.keySet() + "】");
                return actType;
            }
            Date beginDate = DateUtils.getBeforHourStartDay(0);
            Date endDate = new Date();
            List<ActivationDate.StatusDateList> activationIdList = activationDAO.queryActivationEQPId(factory, eqpIdList, beginDate, endDate);
            Map<String, ActivationDate.StatusDateList> queryMap = MapUtils.listToMap(activationIdList, "key");
            List<ActivationDate> dateList = new ArrayList<ActivationDate>();
            for (String eqpId:eqpIdList) {
                ActivationDate activationDate = new ActivationDate();
                activationDate.setEqpId(eqpId);
                List<ActivationDate.StatusDateList> dtList = new ArrayList<ActivationDate.StatusDateList>();
                for (String stType : statusList) {
                    String key = eqpId + " " + stType;
                    ActivationDate.StatusDateList statusNumber = queryMap.get(key);
                    if (statusNumber == null) {
                        statusNumber = new ActivationDate.StatusDateList(stType, eqpId);
                    }
                    dtList.add(statusNumber);
                }
                activationDate.setStatusDateList(dtList);
                dateList.add(activationDate);
            }
            actType.setActivationDateList(dateList);
            return actType;
        }catch (Exception e){
            actType.setSuccess(false);
            actType.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return actType;
        }

    }



}