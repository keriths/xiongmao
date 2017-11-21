package com.xm.service.apiimpl.pc.cim.eqp;

import com.google.common.collect.Lists;
import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.DateUtils;
import com.xm.platform.util.LogUtils;
import com.xm.platform.util.MapUtils;
import com.xm.service.apiimpl.pc.cim.eqp.dto.ActivationDate;
import com.xm.service.apiimpl.pc.cim.eqp.dto.ActivationEQPIdListRetDTO;
import com.xm.service.apiimpl.pc.cim.eqp.dto.ActivationEQPStatusListRetDTO;
import com.xm.service.apiimpl.pc.cim.eqp.dto.ActivationStatusDate;
import com.xm.service.constant.Constant;
import com.xm.service.dao.cim.DwrEqpOeeFidsDAO;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by fanshuai on 17/10/24.
 */

@Service("ActivationService")
@ApiServiceDoc(name = "CIM_稼动率(完成)")
public class ActivationServiceImpl {

    @Resource(name="activationDAO")
    private DwrEqpOeeFidsDAO activationDAO;


    @ApiMethodDoc(apiCode = "CIM_ActivationStatusNum",name="EQP类型的状态值显示(完成)")
    public ActivationEQPStatusListRetDTO activationStatusNumList(@ApiParamDoc(desc = "厂别：如Array Cell") String factory, @ApiParamDoc(desc = "EQP类型，如PHOTO PVD") String eqpId){
        ActivationEQPStatusListRetDTO actType = new ActivationEQPStatusListRetDTO();

        try {
            List<String> eqpIdList = Constant.factoryEQPStatusListMap.get(factory);
            if (CollectionUtils.isEmpty(eqpIdList)) {
                actType.setSuccess(false);
                actType.setErrorMsg("factory参数错误,请传入【" + Constant.factoryEQPStatusListMap.keySet() + "】");
                return actType;
            }
            if (!eqpIdList.contains(eqpId)){
                actType.setSuccess(false);
                actType.setErrorMsg("eqpId参数错误,请传入【" + eqpIdList + "】");
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
                for (String status : Constant.statusList) {
                    String key = status + " " + hour;
                    ActivationStatusDate.StatusNumberList statusNumber = queryMap.get(key);
                    if (statusNumber == null) {
                        statusNumber = new ActivationStatusDate.StatusNumberList(status,hour);
                    }
                    list.add(statusNumber);
                }
                activationStatusDate.setStatusNumberLists(list);
                dtList.add(activationStatusDate);
            }
            actType.setActivationStatusDateList(dtList);
            return actType;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            actType.setSuccess(false);
            actType.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return actType;
        }

    }

   @ApiMethodDoc(apiCode = "CIM_ActivationEQPId",name="重点设备稼动显示(完成)")
    public ActivationEQPIdListRetDTO activationIdList(@ApiParamDoc(desc = "厂别：如Array Cell") String factory){
       ActivationEQPIdListRetDTO actType = new ActivationEQPIdListRetDTO();
        try {
            List<String> eqpIdList = Constant.factoryEQPStatusListMap.get(factory);
            if (CollectionUtils.isEmpty(eqpIdList)) {
                actType.setSuccess(false);
                actType.setErrorMsg("factory参数错误,请传入【" + Constant.factoryEQPStatusListMap.keySet() + "】");
                return actType;
            }
            Date beginDate = DateUtils.getBeforHourStartDay(0);
            Date endDate = new Date();
            List<ActivationDate.StatusDateList> activationIdList = activationDAO.queryActivationEQPId(factory, eqpIdList, beginDate, endDate);
            if (CollectionUtils.isEmpty(activationIdList)){
                //如果这一小时数据还没有出来，取上一小时的数据
                beginDate = DateUtils.getBeforHourStartDay(1);
                endDate = DateUtils.getBeforHourEndDay(1);
                activationIdList = activationDAO.queryActivationEQPId(factory, eqpIdList, beginDate, endDate);
            }
            Map<String, ActivationDate.StatusDateList> queryMap = MapUtils.listToMap(activationIdList, "key");
            List<ActivationDate> dateList = new ArrayList<ActivationDate>();
            for (String eqpId:eqpIdList) {
                ActivationDate activationDate = new ActivationDate();
                activationDate.setEqpId(eqpId);
                List<ActivationDate.StatusDateList> dtList = new ArrayList<ActivationDate.StatusDateList>();
                for (String stType : Constant.statusList) {
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
            LogUtils.error(getClass(), e);
            actType.setSuccess(false);
            actType.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return actType;
        }

    }

}
