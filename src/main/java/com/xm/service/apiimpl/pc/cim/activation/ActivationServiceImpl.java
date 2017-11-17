package com.xm.service.apiimpl.pc.cim.activation;

import com.google.common.collect.Lists;
import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.DateUtils;
import com.xm.platform.util.MapUtils;
import com.xm.service.apiimpl.pc.cim.activation.dto.ActivationEQPTypeListRetDTO;
import com.xm.service.dao.cim.ActivationDAO;
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
@ApiServiceDoc(name = "CIM_稼动率")
public class ActivationServiceImpl {

    private static Map<String,List<String>> factoryEQPStatusListMap = MapUtils.newMap(
            "Array", Lists.newArrayList("PHOTO","PVD","CVD","WET","DE"),
            "Cell",Lists.newArrayList("PI","FDV","ODF","HSW","KOL"),
            "CF",Lists.newArrayList("BM","ITO","PS","RGB","RML"),
            "SL-OC",Lists.newArrayList("MBD","POL","OLB","MLB","Aging")
    );

    @Resource(name="activationDAO")
    private ActivationDAO activationDAO;

    /*@ApiMethodDoc(apiCode = "CIM_ActivationStatus",name = "EQP类型的状态接口")
    public ActivationDetailDTO activationStatus(@ApiParamDoc(desc = "状态如PHOTO PVD")String status){

        ActivationDetailDTO actD = new ActivationDetailDTO();
        List<ActivationStatusDTO> actStatus = activationDAO.ActivationStatus(status);
        actD.setStatusDTOList(actStatus);
        return actD;
        //return new ActivationStatusDTO();
    }

    @ApiMethodDoc(apiCode = "CIM_Activation",name = "设备稼动率接口")
    public ActivationRetDTO activationRet(@ApiParamDoc(desc = "厂别如Array Cell")String factory,@ApiParamDoc(desc = "状态如PHOTO PVD")String status){

        ActivationRetDTO actRet = new ActivationRetDTO();
        List<ActivationDetailDTO> actDetail = activationDAO.ActivationDetail(factory,status);
        actRet.setDetailDTOList(actDetail);
        return actRet;
        //return new ActivationRetDTO();
    }*/

    @ApiMethodDoc(apiCode = "CIM_ActivationStatusNum",name="EQP类型的状态值显示")
    public ActivationEQPTypeListRetDTO activationStatusNumList(@ApiParamDoc(desc = "厂别：如Array Cell") String factory, @ApiParamDoc(desc = "EQP类型，如PHOTO PVD") String eqpType){
        ActivationEQPTypeListRetDTO actType = new ActivationEQPTypeListRetDTO();
        try{
            List<String> eqpTypeList = factoryEQPStatusListMap.get(factory);
            if (eqpTypeList==null) {
                actType.setSuccess(false);
                actType.setErrorMsg("factory参数错误,请传入【"+factoryEQPStatusListMap.keySet()+ "】");
                return actType;
            }
            if(!eqpTypeList.contains(eqpType)){
                actType.setSuccess(false);
                actType.setErrorMsg("eqpId参数错误,请传入【"+eqpTypeList+"】");
                return actType;
            }
            Date startTime = DateUtils.getBeforHourStartDay(11);
            Date endTime = new Date();
            List<String> hourList = DateUtils.getHourStrList(startTime,endTime);
            List<ActivationEQPTypeListRetDTO.ActivationStatusNumDetail> queryStatusNumList = activationDAO.queryActivationStatusNum(factory,eqpType,startTime,endTime);
            Map<String,ActivationEQPTypeListRetDTO.ActivationStatusNumDetail> queryStatusNumMap = MapUtils.listToMap(queryStatusNumList,"getDateTime");
            List<ActivationEQPTypeListRetDTO.ActivationStatusNumDetail> activationStatusNumDetailList = new ArrayList<ActivationEQPTypeListRetDTO.ActivationStatusNumDetail>();
            for(String hour:hourList){
                ActivationEQPTypeListRetDTO.ActivationStatusNumDetail statusNumDetail = null;
                if(!CollectionUtils.isEmpty(queryStatusNumMap)){
                    statusNumDetail = queryStatusNumMap.get(hour);
                }
                if(statusNumDetail==null){
                    statusNumDetail = new ActivationEQPTypeListRetDTO.ActivationStatusNumDetail(hour);
                }
                activationStatusNumDetailList.add(statusNumDetail);
            }
            actType.setActivationStatusNumDetailList(activationStatusNumDetailList);
            return actType;
        }catch (Exception e){
            actType.setSuccess(false);
            actType.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return actType;
        }


    }

   @ApiMethodDoc(apiCode = "CIM_ActivationEQPType",name="设备稼动率显示")
    public ActivationEQPTypeListRetDTO activationTypeList(@ApiParamDoc(desc = "厂别：如Array Cell") String factory){
        ActivationEQPTypeListRetDTO actType = new ActivationEQPTypeListRetDTO();
        try{
            List<String> eqpTypeList = factoryEQPStatusListMap.get(factory);
            if (CollectionUtils.isEmpty(eqpTypeList)) {
                actType.setSuccess(false);
                actType.setErrorMsg("factory参数错误,请传入【"+factoryEQPStatusListMap.keySet()+ "】");
                return actType;
            }
            Date beginDate = DateUtils.getBeforHourStartDay(1);
            Date endDate = new Date();
            List<ActivationEQPTypeListRetDTO.ActivationTypeDetail> activationTypeList = activationDAO.queryActivationEQPType(factory,eqpTypeList,beginDate,endDate);
            Map<String,ActivationEQPTypeListRetDTO.ActivationTypeDetail> queryMap = MapUtils.listToMap(activationTypeList,"getEqpType");
            List<ActivationEQPTypeListRetDTO.ActivationTypeDetail> activationTypeDetailList = new ArrayList<ActivationEQPTypeListRetDTO.ActivationTypeDetail>();
            for(String eqpType:eqpTypeList){
                ActivationEQPTypeListRetDTO.ActivationTypeDetail detail = null;
                if(!CollectionUtils.isEmpty(queryMap)){
                    detail = queryMap.get(eqpType);
                }
                if(detail==null){
                    detail = new ActivationEQPTypeListRetDTO.ActivationTypeDetail(eqpType);
                }
                activationTypeDetailList.add(detail);
            }
            actType.setActivationTypeDetailList(activationTypeDetailList);
        }catch (Exception e){
            actType.setSuccess(false);
            actType.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return actType;
        }
        return actType;

    }



}
