package com.xm.service.apiimpl.pc.integrateData.realTimeStatus;

import com.google.common.collect.Lists;
import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.DateUtils;
import com.xm.platform.util.LogUtils;
import com.xm.service.apiimpl.pc.cim.goodRate.RateOfGoodProductServiceImpl;
import com.xm.service.apiimpl.pc.cim.goodRate.dto.ProductLineData;
import com.xm.service.apiimpl.pc.cim.goodRate.dto.ProductLineDetailData;
import com.xm.service.apiimpl.pc.cim.goodRate.dto.ProductOcData;
import com.xm.service.apiimpl.pc.cim.goodRate.dto.ProductOcDetailData;
import com.xm.service.apiimpl.pc.cim.oee.ActivationServiceImpl;
import com.xm.service.apiimpl.pc.cim.oee.dto.ActivationEQPIdListRetDTO;
import com.xm.service.apiimpl.pc.cim.tactTime.TactTimeServiceImpl;
import com.xm.service.apiimpl.pc.cim.tactTime.dto.TactTimeMonthAvgRetDTO;
import com.xm.service.apiimpl.pc.integrateData.realTimeStatus.dto.*;
import com.xm.service.apiimpl.pc.product.ProductServiceImpl;
import com.xm.service.constant.Constant;
import com.xm.service.dao.cim.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
    ProductServiceImpl productService;

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
                else if ("TRB".equals(equipmentData.getVal())){
                    factoryEquiStatusNumCollectDTO.failNum = factoryEquiStatusNumCollectDTO.failNum+equipmentData.getCount();
                    factoryEquiStatusNumCollectDTO.totalNum = factoryEquiStatusNumCollectDTO.totalNum+equipmentData.getCount();
                }
                else if ("RUN".equals(equipmentData.getVal()) || "MAN".equals(equipmentData.getVal()) || "WAT".equals(equipmentData.getVal())){
                    factoryEquiStatusNumCollectDTO.oeeNum = factoryEquiStatusNumCollectDTO.oeeNum+equipmentData.getCount();
                    factoryEquiStatusNumCollectDTO.totalNum = factoryEquiStatusNumCollectDTO.totalNum+equipmentData.getCount();
                }
                else if ("MNT".equals(equipmentData.getVal())){
                    factoryEquiStatusNumCollectDTO.pmNum = factoryEquiStatusNumCollectDTO.pmNum+equipmentData.getCount();
                    factoryEquiStatusNumCollectDTO.totalNum = factoryEquiStatusNumCollectDTO.totalNum+equipmentData.getCount();
                }
                else if ("SL-OC".equals(equipmentData.getFactory())){
                    factoryEquiStatusNumCollectDTO.oeeNum = factoryEquiStatusNumCollectDTO.oeeNum+equipmentData.getCount();
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

    private List<OutputCollectDataRetDTO.CollectDataList> queryTotalOutputByDateAndProductIdList(Date start,Date end){
        List<OutputCollectDataRetDTO.CollectDataList> dataLists = new ArrayList<>();
        Map<String,List<String>> productProductIdMap = productService.allProductProductIdMap();
        for (Map.Entry<String,List<String>> entry : productProductIdMap.entrySet()){
            OutputCollectDataRetDTO.CollectDataList collectData = outputcompletionDAO.queryTotalOutputByDateAndProductIdList(start,end,entry.getValue());
            if (collectData!=null){
                collectData.setProductName(entry.getKey());
            }else{
                collectData = new OutputCollectDataRetDTO.CollectDataList();
                collectData.setOutputNum(BigDecimal.ZERO);
                collectData.setProductName(entry.getKey());
            }
            dataLists.add(collectData);
        }
        return dataLists;
    }

    @ApiMethodDoc(apiCode = "CIM_outputCollectData" , name = "产能")
    public OutputCollectDataRetDTO outCollectRetDTO(){
        OutputCollectDataRetDTO resultDto = new OutputCollectDataRetDTO();
        try {
            Date todayStart = DateUtils.getBeforDayStartDay(1);
            Date todayEnd = DateUtils.getBeforDayEndDay(1);
            Date curMonthStart = DateUtils.getBeforMonthStartDay(0);
            Date curMonthEnd = DateUtils.getBeforMonthEndDay(0);
            List<OutputCollectDataRetDTO.CollectDataList> dayDataList = queryTotalOutputByDateAndProductIdList(todayStart, todayEnd);
            List<OutputCollectDataRetDTO.CollectDataList> monthDataList = queryTotalOutputByDateAndProductIdList(curMonthStart,curMonthEnd);
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

    @Resource
    RateOfGoodProductServiceImpl rateOfGoodProductService;

    @ApiMethodDoc(apiCode = "CIM_lineCollectData" , name = "指定工厂的良品率")
    public ProductLineData lineCollectRetDTO(){
        ProductLineData resultDto = new ProductLineData();
        try {
            List<String> factoryList = Constant.allSingleFactoryLists;
            Date todayStart = DateUtils.getBeforDayStartDay(1);
            Date todayEnd = DateUtils.getBeforDayEndDay(1);

            List<String> dayStrList = DateUtils.getDayStrList(todayStart,todayEnd);
            List<ProductLineDetailData> dayOfArray = rateOfGoodProductService.getFactoryGoodRate("ARRAY", Constant.day, dayStrList, todayStart, todayEnd);
            List<ProductLineDetailData> dayOfCell = rateOfGoodProductService.getFactoryGoodRate("CELL", Constant.day, dayStrList, todayStart, todayEnd);
            List<ProductLineDetailData> dayOfCf = rateOfGoodProductService.getFactoryGoodRate("CF", Constant.day, dayStrList, todayStart, todayEnd);
            List<ProductLineDetailData> dayOfSLOC = rateOfGoodProductService.getFactoryGoodRate("SL-OC", Constant.day, dayStrList, todayStart, todayEnd);
//            List<ProductLineDetailData> dayDataList = dwsProductLineYieldFidsDAO.queryTotalProductLineByDateAndFactoryList(Lists.newArrayList("ARRAY", "CELL", "CF", "SL"), todayStart, todayEnd, null);
            List<ProductLineDetailData> dayDataList = new ArrayList<>();
            dayDataList.addAll(dayOfArray);
            dayDataList.addAll(dayOfCell);
            dayDataList.addAll(dayOfCf);
            dayDataList.addAll(dayOfSLOC);

            Date curMonthStart = DateUtils.getBeforMonthStartDay(0);
            Date curMonthEnd = DateUtils.getBeforMonthEndDay(0);
            List<String> monthStrList = DateUtils.getMonthStrList(curMonthStart,curMonthEnd);
            List<ProductLineDetailData> monthOfArray = rateOfGoodProductService.getFactoryGoodRate("ARRAY", Constant.month, monthStrList, curMonthStart, curMonthEnd);
            List<ProductLineDetailData> monthOfCell = rateOfGoodProductService.getFactoryGoodRate("CELL", Constant.month, monthStrList, curMonthStart, curMonthEnd);
            List<ProductLineDetailData> monthOfCf = rateOfGoodProductService.getFactoryGoodRate("CF", Constant.month, monthStrList, curMonthStart, curMonthEnd);
            List<ProductLineDetailData> monthOfSLOC = rateOfGoodProductService.getFactoryGoodRate("SL-OC", Constant.month, monthStrList, curMonthStart, curMonthEnd);
            List<ProductLineDetailData> monthDataList = new ArrayList<>();
            monthDataList.addAll(monthOfArray);
            monthDataList.addAll(monthOfCell);
            monthDataList.addAll(monthOfCf);
            monthDataList.addAll(monthOfSLOC);

            /*
            //查询产品的良品率
            if (CollectionUtils.isEmpty(dayDataList)){
                dayDataList = factoryList.stream().map(factory -> new ProductLineDetailData(DateUtils.getStrDate(todayStart,"MM/dd"),factory)).collect(Collectors.toList());
            }
            for (ProductLineDetailData productLineDetailData : dayDataList){
                if (productLineDetailData.getFactory().equals("SL-OC")){
                    ProductOcDetailData productDayGoodRateData = dwsProductOcYieldFidsDAO.queryProductsGoodRate(productService.queryAllProductIdList(),todayStart, todayEnd);
                    BigDecimal factoryInline = productLineDetailData.getInLine()==null?BigDecimal.ZERO:productLineDetailData.getInLine();
                    BigDecimal productInline = productDayGoodRateData==null?BigDecimal.ZERO:productDayGoodRateData.getInLine()==null?BigDecimal.ZERO:productDayGoodRateData.getInLine();
                    if (BigDecimal.ZERO.equals(factoryInline)){
                        productLineDetailData.setInLine(productInline);
                    }else if (!BigDecimal.ZERO.equals(productInline)){
                        productLineDetailData.setInLine(factoryInline.multiply(productInline).divide(new BigDecimal("100")));
                    }
                    productLineDetailData.setFactoryInline(factoryInline);
                    productLineDetailData.setProductInline(productInline);
                }
            }
            List<ProductLineDetailData> monthDataList = dwsProductLineYieldFidsDAO.queryTotalProductLineByDateAndFactoryList(Lists.newArrayList("ARRAY","CELL","CF","SL"),curMonthStart,curMonthEnd,null);
            if (CollectionUtils.isEmpty(monthDataList)){
                monthDataList = factoryList.stream().map(factory -> new ProductLineDetailData(DateUtils.getStrDate(curMonthStart,"yyyy/MM"), factory)).collect(Collectors.toList());
            }
            for (ProductLineDetailData productLineDetailData : monthDataList){
                if (productLineDetailData.getFactory().equals("SL-OC")){
                    ProductOcDetailData productMonthGoodRateData = dwsProductOcYieldFidsDAO.queryProductsGoodRate(productService.queryAllProductIdList(),curMonthStart,curMonthEnd);
                    BigDecimal factoryInline = productLineDetailData.getInLine()==null?BigDecimal.ZERO:productLineDetailData.getInLine();
                    BigDecimal productInline = productMonthGoodRateData==null?BigDecimal.ZERO:productMonthGoodRateData.getInLine()==null?BigDecimal.ZERO:productMonthGoodRateData.getInLine();
                    if (BigDecimal.ZERO.equals(factoryInline)){
                        productLineDetailData.setInLine(productInline);
                    }else if (!BigDecimal.ZERO.equals(productInline)){
                        productLineDetailData.setInLine(factoryInline.multiply(productInline).divide(new BigDecimal("100")));
                    }
                    productLineDetailData.setFactoryInline(factoryInline);
                    productLineDetailData.setProductInline(productInline);
                }
            }*/
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
//    @ApiMethodDoc(apiCode = "CIM_ocCollectData" , name = "指定产品的良品率")
//    public ProductOcData ocCollectRetDTO(){
//        ProductOcData resultDto = new ProductOcData();
//        try {
//            Date todayStart = DateUtils.getBeforDayStartDay(1);
//            Date todayEnd = DateUtils.getBeforDayEndDay(1);
//            List<ProductOcDetailData> dayDataList = new ArrayList<>();//dwsProductOcYieldFidsDAO.queryTotalProductLineOCByDateAndProductList(todayStart,todayEnd);
//            for (Map.Entry<String,List<String>> entry : productService.allProductProductIdMap().entrySet()){
//                ProductOcDetailData productOcDetailData = new ProductOcDetailData();
//                productOcDetailData.setProductName(productService.getProductName(entry.getKey()));
//                dayDataList.add(productOcDetailData);
//                ProductOcDetailData productDayGoodRateDate = dwsProductOcYieldFidsDAO.queryProductsGoodRate(entry.getValue(),todayStart,todayEnd);
//                BigDecimal productInline = productDayGoodRateDate!=null?productDayGoodRateDate.getInLine()!=null?productDayGoodRateDate.getInLine():BigDecimal.ZERO:BigDecimal.ZERO;
//                //查询array cell sl
//                BigDecimal arrayInLine = BigDecimal.ZERO;
//                BigDecimal cellInLine = BigDecimal.ZERO;
//                BigDecimal slInLine = BigDecimal.ZERO;
//                List<ProductLineDetailData> productLineDetailDatas = dwsProductLineYieldFidsDAO.queryFactoryProductGoodRate(Lists.newArrayList("ARRAY", "CELL", "SL"), entry.getValue(), todayStart, todayEnd);
//                if (!CollectionUtils.isEmpty(productLineDetailDatas)){
//                    for (ProductLineDetailData p : productLineDetailDatas){
//                        if (p.getFactory().equals("ARRAY")){
//                            arrayInLine = p.getInLine()!=null?p.getInLine():BigDecimal.ZERO;
//                        }
//                        if (p.getFactory().equals("CELL")){
//                            cellInLine = p.getInLine()!=null?p.getInLine():BigDecimal.ZERO;
//                        }
//                        if (p.getFactory().equals("SL-OC")){
//                            slInLine = p.getInLine()!=null?p.getInLine():BigDecimal.ZERO;
//                        }
//                    }
//                }
//                if (BigDecimal.ZERO.equals(productInline)
//                        &&BigDecimal.ZERO.equals(arrayInLine)
//                        &&BigDecimal.ZERO.equals(cellInLine)
//                        &&BigDecimal.ZERO.equals(slInLine)){
//                    productOcDetailData.setInLine(BigDecimal.ZERO);
//                }else {
//                    BigDecimal inLine = new BigDecimal("100");
//                    if (!BigDecimal.ZERO.equals(productInline)){
//                        inLine = inLine.multiply(productInline).divide(new BigDecimal("100"));
//                    }
//                    if (!BigDecimal.ZERO.equals(arrayInLine)){
//                        inLine = inLine.multiply(arrayInLine).divide(new BigDecimal("100"));
//                    }
//                    if (!BigDecimal.ZERO.equals(cellInLine)){
//                        inLine = inLine.multiply(cellInLine).divide(new BigDecimal("100"));
//                    }
//                    if (!BigDecimal.ZERO.equals(slInLine)){
//                        inLine = inLine.multiply(slInLine).divide(new BigDecimal("100"));
//                    }
//                    productOcDetailData.setInLine(inLine);
//                }
//                productOcDetailData.setProductInline(productInline);
//                productOcDetailData.setArrayInline(arrayInLine);
//                productOcDetailData.setCellInline(cellInLine);
//                productOcDetailData.setSlInline(slInLine);
//            }
//
//            List<ProductOcDetailData> monthDataList = new ArrayList<>(); //dwsProductOcYieldFidsDAO.queryTotalProductLineOCByDateAndProductList(curMonthStart,curMonthEnd);
//            Date curMonthStart = DateUtils.getBeforMonthStartDay(0);
//            Date curMonthEnd = DateUtils.getBeforMonthEndDay(0);
//            for (Map.Entry<String,List<String>> entry : productService.allProductProductIdMap().entrySet()){
//                ProductOcDetailData productOcDetailData = new ProductOcDetailData();
//                productOcDetailData.setProductName(productService.getProductName(entry.getKey()));
//                monthDataList.add(productOcDetailData);
//                ProductOcDetailData productDayGoodRateDate = dwsProductOcYieldFidsDAO.queryProductsGoodRate(entry.getValue(),curMonthStart,curMonthEnd);
//                BigDecimal productInline = productDayGoodRateDate!=null?productDayGoodRateDate.getInLine()!=null?productDayGoodRateDate.getInLine():BigDecimal.ZERO:BigDecimal.ZERO;
//                //查询array cell sl
//                BigDecimal arrayInLine = BigDecimal.ZERO;
//                BigDecimal cellInLine = BigDecimal.ZERO;
//                BigDecimal slInLine = BigDecimal.ZERO;
//                List<ProductLineDetailData> productLineDetailDatas = dwsProductLineYieldFidsDAO.queryFactoryProductGoodRate(Lists.newArrayList("ARRAY", "CELL", "SL"), entry.getValue(), curMonthStart, curMonthEnd);
//                if (!CollectionUtils.isEmpty(productLineDetailDatas)){
//                    for (ProductLineDetailData p : productLineDetailDatas){
//                        if (p.getFactory().equals("ARRAY")){
//                            arrayInLine = p.getInLine()!=null?p.getInLine():BigDecimal.ZERO;
//                        }
//                        if (p.getFactory().equals("CELL")){
//                            cellInLine = p.getInLine()!=null?p.getInLine():BigDecimal.ZERO;
//                        }
//                        if (p.getFactory().equals("SL-OC")){
//                            slInLine = p.getInLine()!=null?p.getInLine():BigDecimal.ZERO;
//                        }
//                    }
//                }
//                if (BigDecimal.ZERO.equals(productInline)
//                        &&BigDecimal.ZERO.equals(arrayInLine)
//                        &&BigDecimal.ZERO.equals(cellInLine)
//                        &&BigDecimal.ZERO.equals(slInLine)){
//                    productOcDetailData.setInLine(BigDecimal.ZERO);
//                }else {
//                    BigDecimal inLine = new BigDecimal("100");
//                    if (!BigDecimal.ZERO.equals(productInline)){
//                        inLine = inLine.multiply(productInline).divide(new BigDecimal("100"));
//                    }
//                    if (!BigDecimal.ZERO.equals(arrayInLine)){
//                        inLine = inLine.multiply(arrayInLine).divide(new BigDecimal("100"));
//                    }
//                    if (!BigDecimal.ZERO.equals(cellInLine)){
//                        inLine = inLine.multiply(cellInLine).divide(new BigDecimal("100"));
//                    }
//                    if (!BigDecimal.ZERO.equals(slInLine)){
//                        inLine = inLine.multiply(slInLine).divide(new BigDecimal("100"));
//                    }
//                    productOcDetailData.setInLine(inLine);
//                }
//                productOcDetailData.setProductInline(productInline);
//                productOcDetailData.setArrayInline(arrayInLine);
//                productOcDetailData.setCellInline(cellInLine);
//                productOcDetailData.setSlInline(slInLine);
//            }
//
//            if (CollectionUtils.isEmpty(dayDataList)){
//                dayDataList = Lists.newArrayList(new ProductOcDetailData("50","20180705"));
//            }
//            if (CollectionUtils.isEmpty(monthDataList)){
//                monthDataList = Lists.newArrayList(new ProductOcDetailData("50","201807"));
//            }
//            resultDto.setProductLineOCCollectDayDataList(dayDataList);
//            resultDto.setProductLineOCCollectMonthDataList(monthDataList);
//            return resultDto;
//        }catch (Exception e){
//            LogUtils.error(this.getClass(),"ocCollectRetDTO eclipse",e);
//            resultDto.setSuccess(false);
//            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
//            return resultDto;
//        }
//    }

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
