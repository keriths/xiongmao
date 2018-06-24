package com.xm.service.apiimpl.pc.cim.oee;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.DateUtils;
import com.xm.platform.util.LogUtils;
import com.xm.platform.util.MapUtils;
import com.xm.service.apiimpl.pc.cim.oee.dto.ActivationDate;
import com.xm.service.apiimpl.pc.cim.oee.dto.ActivationEQPIdListRetDTO;
import com.xm.service.apiimpl.pc.cim.oee.dto.ActivationEQPStatusListRetDTO;
import com.xm.service.apiimpl.pc.cim.oee.dto.ActivationStatusDate;
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
@ApiServiceDoc(name = "CIM7_稼动率（完成-工厂数据已验证）")
public class ActivationServiceImpl {

    @Resource(name="activationDAO")
    private DwrEqpOeeFidsDAO activationDAO;


    @ApiMethodDoc(apiCode = "CIM_ActivationStatusNum",name="EQP类型的状态值显示（完成-工厂数据已验证）")
    public ActivationEQPStatusListRetDTO activationStatusNumList(@ApiParamDoc(desc = "厂别：ARRAY,CELL") String factory, @ApiParamDoc(desc = "EQP类型，如PHOTO PVD") String eqpId){
        ActivationEQPStatusListRetDTO actType = new ActivationEQPStatusListRetDTO();
        try {
            if (factory!=null){
                factory = factory.toUpperCase();
            }
            List<String> factoryList = Constant.factoryMap.get(factory);
            List<String> showEqpIdList = Constant.factoryEQPStatusListMap.get(factory);
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

            List<String> eqpIdList=Constant.eqpIdMap.get(eqpId);

            Date beginDate = DateUtils.getBeforHourStartDay(11);
            Date endDate = new Date();
            List<String> hourList = DateUtils.getHourStrList(beginDate,endDate);
            //List<ActivationStatusDate.StatusNumberList> activationNumList = activationDAO.queryActivationStatusNum(factory, eqpId, beginDate, endDate);
            List<ActivationStatusDate.StatusNumberList> activationNumList = activationDAO.queryActivationStatusNum(factoryList, eqpIdList, beginDate, endDate);
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

   @ApiMethodDoc(apiCode = "CIM_ActivationEQPId",name="重点设备稼动显示（完成-工厂数据已验证）")
    public ActivationEQPIdListRetDTO activationIdList(@ApiParamDoc(desc = "厂别：ARRAY,CELL") String factory){
       ActivationEQPIdListRetDTO actType = new ActivationEQPIdListRetDTO();
        try {
            List<String> factoryList = Constant.factoryMap.get(factory);
            List<String> showEqpIdList = Constant.factoryEQPStatusListMap.get(factory);
            if (CollectionUtils.isEmpty(showEqpIdList)) {
                actType.setSuccess(false);
                actType.setErrorMsg("factory参数错误,请传入【" + Constant.factoryEQPStatusListMap.keySet() + "】");
                return actType;
            }
            Date beginDate = DateUtils.getBeforHourStartDay(0);
            Date endDate = new Date();

            List<ActivationDate> dateList = new ArrayList<ActivationDate>();

            for (String eqpId: showEqpIdList){
                ActivationDate activationDate=new ActivationDate();
                activationDate.setEqpId(eqpId);

                List<String> eqpIdList=Constant.eqpIdMap.get(eqpId);
                List<ActivationDate.StatusDateList> activationIdList = activationDAO.queryActivationEQPId(factoryList, eqpIdList, beginDate, endDate);
                if (CollectionUtils.isEmpty(activationIdList)){
                    //如果这一小时数据还没有出来，取上一小时的数据
                    beginDate = DateUtils.getBeforHourStartDay(1);
                    endDate = DateUtils.getBeforHourEndDay(1);
                    activationIdList = activationDAO.queryActivationEQPId(factoryList, eqpIdList, beginDate, endDate);
                }
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
            return actType;

        }catch (Exception e){
            LogUtils.error(getClass(), e);
            actType.setSuccess(false);
            actType.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return actType;
        }

    }

}
