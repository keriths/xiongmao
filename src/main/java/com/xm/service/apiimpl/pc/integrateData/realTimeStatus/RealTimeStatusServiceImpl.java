package com.xm.service.apiimpl.pc.integrateData.realTimeStatus;

import com.google.common.collect.Lists;
import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.DateUtils;
import com.xm.platform.util.LogUtils;
import com.xm.service.apiimpl.pc.cim.goodRate.dto.ProductLineData;
import com.xm.service.apiimpl.pc.cim.goodRate.dto.ProductLineDetailData;
import com.xm.service.apiimpl.pc.cim.goodRate.dto.ProductOcData;
import com.xm.service.apiimpl.pc.cim.goodRate.dto.ProductOcDetailData;
import com.xm.service.apiimpl.pc.cim.oee.ActivationServiceImpl;
import com.xm.service.apiimpl.pc.cim.oee.dto.ActivationEQPIdListRetDTO;
import com.xm.service.apiimpl.pc.cim.tactTime.TactTimeServiceImpl;
import com.xm.service.apiimpl.pc.cim.tactTime.dto.TactTimeMonthAvgRetDTO;
import com.xm.service.apiimpl.pc.integrateData.realTimeStatus.dto.*;
import com.xm.service.constant.Constant;
import com.xm.service.dao.cim.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by wangshuna on 2018/3/6.
 */
@Service("RealTimeStatusService")
@ApiServiceDoc(name = "综合数据_实时状态")
public class RealTimeStatusServiceImpl {

    @Resource(name = "dwrEquipmentStatusFidsDAO")
    public DwrEquipmentStatusFidsDAO dwrEquipmentStatusFidsDAO;
    @Resource
    private DwsProductOutputFidsDAO outputcompletionDAO;
    @Resource(name = "dwsProductLineYieldFidsDAO")
    private DwsProductLineYieldFidsDAO dwsProductLineYieldFidsDAO;
    @Resource(name = "dwsProductOcYieldFidsDAO")
    private DwsProductOcYieldFidsDAO dwsProductOcYieldFidsDAO;

    @Resource
    public TactTimeServiceImpl tactTimeService;
    @Resource
    public ActivationServiceImpl activationService;

    @ApiMethodDoc(apiCode = "CIM_EquipmentData",name = "设备实时状态")
    public EquipmentCollectDataDTO equipmentData(){
        EquipmentCollectDataDTO resultDto = new EquipmentCollectDataDTO();
        try {
            List<EquipmentDataDto.EquipmentData> queryList = dwrEquipmentStatusFidsDAO.queryStatus(Constant.allSingleFactoryLists);
            Map<String,EquipmentCollectDataDTO.FactoryEquiStatusNumCollectDTO> factoryStatusNumMap = new HashMap<String, EquipmentCollectDataDTO.FactoryEquiStatusNumCollectDTO>();
            for (EquipmentDataDto.EquipmentData equipmentData : queryList){
                EquipmentCollectDataDTO.FactoryEquiStatusNumCollectDTO factoryEquiStatusNumCollectDTO = factoryStatusNumMap.get(equipmentData.getFactory());
                if (factoryEquiStatusNumCollectDTO ==null){
                    factoryEquiStatusNumCollectDTO = new EquipmentCollectDataDTO.FactoryEquiStatusNumCollectDTO();
                    factoryEquiStatusNumCollectDTO.factory = equipmentData.getFactory();
                    factoryStatusNumMap.put(equipmentData.getFactory(), factoryEquiStatusNumCollectDTO);
                }

                if ("MAN".equals(equipmentData.getVal())){
                    factoryEquiStatusNumCollectDTO.pmNum = factoryEquiStatusNumCollectDTO.pmNum+equipmentData.getCount();
                    factoryEquiStatusNumCollectDTO.totalNum = factoryEquiStatusNumCollectDTO.totalNum+equipmentData.getCount();
                }
                if ("WAT".equals(equipmentData.getVal())){
                    factoryEquiStatusNumCollectDTO.oeeNum = factoryEquiStatusNumCollectDTO.oeeNum+equipmentData.getCount();
                    factoryEquiStatusNumCollectDTO.totalNum = factoryEquiStatusNumCollectDTO.totalNum+equipmentData.getCount();
                }
                if ("RUN".equals(equipmentData.getVal())){
                    factoryEquiStatusNumCollectDTO.oeeNum = factoryEquiStatusNumCollectDTO.oeeNum+equipmentData.getCount();
                    factoryEquiStatusNumCollectDTO.totalNum = factoryEquiStatusNumCollectDTO.totalNum+equipmentData.getCount();
                }
                if ("TRB".equals(equipmentData.getVal())){
                    factoryEquiStatusNumCollectDTO.failNum = factoryEquiStatusNumCollectDTO.failNum+equipmentData.getCount();
                    factoryEquiStatusNumCollectDTO.totalNum = factoryEquiStatusNumCollectDTO.totalNum+equipmentData.getCount();
                }
                if ("MNT".equals(equipmentData.getVal())){
                    factoryEquiStatusNumCollectDTO.pmNum = factoryEquiStatusNumCollectDTO.pmNum+equipmentData.getCount();
                    factoryEquiStatusNumCollectDTO.totalNum = factoryEquiStatusNumCollectDTO.totalNum+equipmentData.getCount();
                }
            }
            List<EquipmentCollectDataDTO.FactoryEquiStatusNumCollectDTO> factoryEquiStatusNumCollectDTOList = new ArrayList<EquipmentCollectDataDTO.FactoryEquiStatusNumCollectDTO>();
            for (String factory :Constant.showFactoryList){
                factoryEquiStatusNumCollectDTOList.add(factoryStatusNumMap.get(factory));
            }
            resultDto.setFactoryStatusNumList(factoryEquiStatusNumCollectDTOList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }

    @ApiMethodDoc(apiCode = "CIM_outputCollectData" , name = "产能")
    public OutputCollectDataRetDTO outCollectRetDTO(){
        OutputCollectDataRetDTO resultDto = new OutputCollectDataRetDTO();
        try {
//            List<String> productNameList=new ArrayList<String>();
//            Map<String,String> dataMap=Constant.outProductIdNameMap;
//            Iterator<Map.Entry<String, String>> it = dataMap.entrySet().iterator();
//            while (it.hasNext()) {
//                Map.Entry<String, String> entry = it.next();
//                productNameList.add(entry.getKey());
//            }
            List<String> productTypeList=Constant.productTypeTestList;
            Date todayStart = DateUtils.getBeforDayStartDay(1);
            Date todayEnd = DateUtils.getBeforDayEndDay(1);
            Date curMonthStart = DateUtils.getBeforMonthStartDay(0);
            Date curMonthEnd = DateUtils.getBeforMonthEndDay(0);
            List<OutputCollectDataRetDTO.CollectDataList> dayDataList = outputcompletionDAO.queryTotalOutputByDateAndProductIdList(todayStart,todayEnd,productTypeList);
            List<OutputCollectDataRetDTO.CollectDataList> monthDataList = outputcompletionDAO.queryTotalOutputByDateAndProductIdList(curMonthStart,curMonthEnd,productTypeList);
//            List<OutputCollectDataRetDTO.CollectDataList> dayDataList = outputcompletionDAO.outputDayData(productIdList);
//            List<OutputCollectDataRetDTO.CollectDataList> monthDataList = outputcompletionDAO.outputMonthData(productIdList);
            if (CollectionUtils.isEmpty(dayDataList)){
                dayDataList = Lists.newArrayList("50","58").stream().map(product -> new OutputCollectDataRetDTO.CollectDataList(product,DateUtils.getStrDate(todayEnd,"MM/dd"))).collect(Collectors.toList());
            }
            if (CollectionUtils.isEmpty(monthDataList)){
                monthDataList = Lists.newArrayList("50","58").stream().map(product -> new OutputCollectDataRetDTO.CollectDataList(product,DateUtils.getStrDate(curMonthEnd,"yyyy/MM"))).collect(Collectors.toList());
            }
            resultDto.setCollectDayDataRetDTOList(dayDataList);
            resultDto.setCollectMonthDataRetDTOList(monthDataList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(this.getClass(),"outCollectRetDTO eclipse",e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }


    @ApiMethodDoc(apiCode = "CIM_lineCollectData" , name = "指定工厂的良品率")
    public ProductLineData lineCollectRetDTO(){
        ProductLineData resultDto = new ProductLineData();
        try {
            List<String> factoryList = Constant.allSingleFactoryLists;
            List<String> productTypeList=Constant.productTypeTestList;
            Date todayStart = DateUtils.getBeforDayStartDay(1);
            Date todayEnd = DateUtils.getBeforDayEndDay(1);
            Date curMonthStart = DateUtils.getBeforMonthStartDay(0);
            Date curMonthEnd = DateUtils.getBeforMonthEndDay(0);
            List<ProductLineDetailData> dayDataList = dwsProductLineYieldFidsDAO.queryTotalProductLineByDateAndFactoryList(factoryList,todayStart,todayEnd,productTypeList);
            List<ProductLineDetailData> monthDataList = dwsProductLineYieldFidsDAO.queryTotalProductLineByDateAndFactoryList(factoryList,curMonthStart,curMonthEnd,productTypeList);
            if (CollectionUtils.isEmpty(dayDataList)){
                dayDataList = factoryList.stream().map(factory -> new ProductLineDetailData(DateUtils.getStrDate(todayStart,"MM/dd"),factory)).collect(Collectors.toList());
            }
            if (CollectionUtils.isEmpty(monthDataList)){
                monthDataList = factoryList.stream().map(factory -> new ProductLineDetailData(DateUtils.getStrDate(curMonthStart,"yyyy/MM"), factory)).collect(Collectors.toList());
            }
            resultDto.setProductLineCollectDayDataList(dayDataList);
            resultDto.setProductLineCollectMonthDataList(monthDataList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(this.getClass(),"lineCollectRetDTO eclipse",e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }
    //这个只取50的
    @ApiMethodDoc(apiCode = "CIM_ocCollectData" , name = "指定产品的良品率")
    public ProductOcData ocCollectRetDTO(){
        ProductOcData resultDto = new ProductOcData();
        try {
            List<String> productNameList=new ArrayList<String>();
            Map<String,String> dataMap=Constant.outProductIdNameMap;
            Iterator<Map.Entry<String, String>> it = dataMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> entry = it.next();
                productNameList.add(entry.getKey());
            }
            List<String> productTypeList=Constant.productTypeTestList;
            Date todayStart = DateUtils.getBeforDayStartDay(1);
            Date todayEnd = DateUtils.getBeforDayEndDay(1);
            Date curMonthStart = DateUtils.getBeforMonthStartDay(0);
            Date curMonthEnd = DateUtils.getBeforMonthEndDay(0);
            List<ProductOcDetailData> dayDataList = dwsProductOcYieldFidsDAO.queryTotalProductLineOCByDateAndProductList(productNameList,todayStart,todayEnd,productTypeList);
            List<ProductOcDetailData> monthDataList = dwsProductOcYieldFidsDAO.queryTotalProductLineOCByDateAndProductList(productNameList,curMonthStart,curMonthEnd,productTypeList);
            if (CollectionUtils.isEmpty(dayDataList)){
                dayDataList = Lists.newArrayList(new ProductOcDetailData("50","20180705"));
            }
            if (CollectionUtils.isEmpty(monthDataList)){
                monthDataList = Lists.newArrayList(new ProductOcDetailData("50","201807"));
            }
            resultDto.setProductLineOCCollectDayDataList(dayDataList);
            resultDto.setProductLineOCCollectMonthDataList(monthDataList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(this.getClass(),"ocCollectRetDTO eclipse",e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }

    @ApiMethodDoc(apiCode = "TactTimeCollectMonthAvg",name = "TactTime月度平均值")
    public TactTimeDataDTO tactTimeDataDTO(@ApiParamDoc(desc = "厂别：如ARRAY必填") String factory){
        TactTimeDataDTO resultDto = new TactTimeDataDTO();
        try {
            if (!Constant.factoryLists.contains(factory)){
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("factory参数错误,请传入【" + Constant.factoryLists + "】");
                return resultDto;
            }
            TactTimeMonthAvgRetDTO tactTimeMonthAvgRetDTO = tactTimeService.monthAvg(factory);
            resultDto.setTactTimeMonthAvgDataList(tactTimeMonthAvgRetDTO.getTactTimeMonthAvgDataDTOList());
            return resultDto;
        }catch (Exception e){
            LogUtils.error(this.getClass(),"tactTimeDataDTO eclipse",e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }

    @ApiMethodDoc(apiCode = "activationCollectData",name = "稼动率")
    public ActivationDataDTO activationDataDTO(@ApiParamDoc(desc = "厂别：如ARRAY必填") String factory){
        ActivationDataDTO resultDto = new ActivationDataDTO();
        try {
            if (!Constant.factoryLists.contains(factory)){
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("factory参数错误,请传入【" + Constant.factoryLists + "】");
                return resultDto;
            }
            ActivationEQPIdListRetDTO activationEQPIdListRetDTO = activationService.activationIdList(factory);

            resultDto.setActivationDateList(activationEQPIdListRetDTO.getActivationDateList());
            return resultDto;
        }catch (Exception e){
            LogUtils.error(this.getClass(),"activationDataDTO eclipse",e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }
}
