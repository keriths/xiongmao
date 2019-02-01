package com.xm.service.apiimpl.pc.integrateData.humidity;

import com.google.common.collect.Lists;
import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.DateUtils;
import com.xm.platform.util.LogUtils;
import com.xm.platform.util.RandomUtils;
import com.xm.service.apiimpl.pc.integrateData.humidity.dto.GasCollectDataDTO;
import com.xm.service.apiimpl.pc.integrateData.humidity.dto.HumitureDataDTO;
import com.xm.service.apiimpl.pc.integrateData.humidity.dto.WaterElectricityCollectDataDTO;
import com.xm.service.constant.Constant;
import com.xm.service.dao.fmcs.*;
import com.xm.service.dto.TwoDaysDataDTO;
import com.xm.service.dto.TwoDaysGasDataDTO;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by wangshuna on 2018/3/6.
 */
@Service("HumidityServiceImpl")
@ApiServiceDoc(name = "综合数据_水电气温湿度")
public class HumidityServiceImpl {

    @Resource(name = "tapWaterEveryDayDataDAO")
    private TapWaterEveryDayDataDAO tapWaterEveryDayDataDAO;
    @Resource(name = "pureWaterEveryDayDataDAO")
    private PureWaterEveryDayDataDAO pureWaterEveryDayDataDAO;
    @Resource(name = "freezeWaterEveryDayDataDAO")
    private FreezeWaterEveryDayDataDAO freezeWaterEveryDayDataDAO;
    @Resource(name = "elecEveryHourDataDAO")
    private ElecEveryHourDataDAO elecEveryHourDataDAO;
    @Resource(name = "bigGasEveryDayDataDAO")
    private GasEveryDayDataDAO bigGasEveryDayDataDAO;
    @Resource(name = "natgasEveryDayDataDAO")
    private NatgasEveryDayDataDAO natgasEveryDayDataDAO;
    @Resource(name = "humitureRealTimeDataDAO")
    private HumitureRealTimeDataDAO humitureRealTimeDataDAO;

    @ApiMethodDoc(apiCode = "WaterElectricityCollectData" , name = "水电综合数据")
    public WaterElectricityCollectDataDTO waterElectricityCollectDataDTO(){
        WaterElectricityCollectDataDTO resultDto = new WaterElectricityCollectDataDTO();
        try {

            Date dayBeforDate = DateUtils.getBeforDayStartDay(1);
            Date dayAfterDate = DateUtils.getBeforDayStartDay(0);
            Date monthBeforDate = DateUtils.getBeforMonthStartDay(0);
            Date monthAfterDate = DateUtils.getBeforDayStartDay(0);
//
//            Date todayStart = DateUtils.getBeforDayStartDay(0);
//            Date todayEnd = DateUtils.getBeforDayEndDay(0);
//            Date curMonthStart = DateUtils.getBeforMonthStartDay(0);
//            Date curMonthEnd = DateUtils.getBeforMonthEndDay(0);

            List<TwoDaysDataDTO> tapWaterDayDataList = tapWaterEveryDayDataDAO.queryTwoDaysTapWaterByQueryData(dayBeforDate, dayAfterDate);
            List<TwoDaysDataDTO> tapWaterMonthDataList = tapWaterEveryDayDataDAO.queryTwoDaysTapWaterByQueryData(monthBeforDate, monthAfterDate);

            List<TwoDaysDataDTO> pureWaterDayDataList = pureWaterEveryDayDataDAO.queryTwoDaysPureWaterByQueryData(dayBeforDate,dayAfterDate);
            List<TwoDaysDataDTO> pureWaterMonthDataList = pureWaterEveryDayDataDAO.queryTwoDaysPureWaterByQueryData(monthBeforDate, monthAfterDate);

            List<TwoDaysDataDTO> freezeWaterDayDataList = freezeWaterEveryDayDataDAO.queryTwoDaysFreezeWaterByQueryData(dayBeforDate, dayAfterDate);
            List<TwoDaysDataDTO> freezeWaterMonthDataList = freezeWaterEveryDayDataDAO.queryTwoDaysFreezeWaterByQueryData(monthBeforDate, monthAfterDate);


            Date elecdayBeforDate = DateUtils.getBeforDayStartDay(0);
            Date elecdayAfterDate = DateUtils.getBeforHourStartDay(1);
            if (new DateTime().hourOfDay().get()==0){
                elecdayBeforDate = DateUtils.getBeforDayStartDay(0);
                elecdayAfterDate = DateUtils.getBeforHourStartDay(0);
            }
            Date elecmonthBeforDate = DateUtils.getBeforMonthStartDay(0);
            Date elecmonthAfterDate = DateUtils.getBeforHourStartDay(1);
            if (new DateTime().hourOfDay().get()==0 && new DateTime().dayOfMonth().get()==1){
                elecmonthBeforDate = DateUtils.getBeforMonthStartDay(0);
                elecmonthAfterDate = DateUtils.getBeforHourStartDay(0);
            }

            List<String> placeList = elecEveryHourDataDAO.elecPlaceList();
            List<TwoDaysDataDTO> electDayDataList = new ArrayList<>();
            List<TwoDaysDataDTO> electMonthDataList = new ArrayList<>();
            BigDecimal dayTotal =  new BigDecimal("0");
            BigDecimal monthTotal =  new BigDecimal("0");
            for (String place : placeList){
                List<TwoDaysDataDTO> elePlaceDayDatas = elecEveryHourDataDAO.queryTwoDaysElectricityByQueryData(place,elecdayBeforDate, elecdayAfterDate);
                if (!CollectionUtils.isEmpty(elePlaceDayDatas)){
                    dayTotal = dayTotal.add(elePlaceDayDatas.get(0).getTotalNum());
                }
                List<TwoDaysDataDTO> electPlaceMonthDatas = elecEveryHourDataDAO.queryTwoDaysElectricityByQueryData(place,elecmonthBeforDate, elecmonthAfterDate);
                if (!CollectionUtils.isEmpty(electPlaceMonthDatas)){
                    monthTotal = monthTotal.add(electPlaceMonthDatas.get(0).getTotalNum());
                }
            }
            TwoDaysDataDTO day = new TwoDaysDataDTO();
            day.setTotalNum(dayTotal);
            day.setBeforDataDate(elecdayBeforDate);
            electDayDataList.add(day);

            TwoDaysDataDTO month = new TwoDaysDataDTO();
            month.setTotalNum(monthTotal);
            month.setBeforDataDate(elecmonthBeforDate);
            electMonthDataList.add(month);

//            tapWaterDayDataList = Lists.newArrayList(new WaterElectricityCollectDataDTO.WaterElectricityCollectData(40,60));
//            pureWaterDayDataList = Lists.newArrayList(new WaterElectricityCollectDataDTO.WaterElectricityCollectData(40,60));
//            freezeWaterDayDataList = Lists.newArrayList(new WaterElectricityCollectDataDTO.WaterElectricityCollectData(40,60));

//            tapWaterMonthDataList = Lists.newArrayList(new WaterElectricityCollectDataDTO.WaterElectricityCollectData(1800,2000));
//            pureWaterMonthDataList = Lists.newArrayList(new WaterElectricityCollectDataDTO.WaterElectricityCollectData(1800,2000));
//            freezeWaterMonthDataList = Lists.newArrayList(new WaterElectricityCollectDataDTO.WaterElectricityCollectData(1800,2000));

//
//            electDayDataList = Lists.newArrayList(new WaterElectricityCollectDataDTO.WaterElectricityCollectData(350,400));
//            electMonthDataList = Lists.newArrayList(new WaterElectricityCollectDataDTO.WaterElectricityCollectData(7500,9000));

            resultDto.setTapWaterDayDataList(tapWaterDayDataList);
            resultDto.setTapWaterMonthDataList(tapWaterMonthDataList);
            resultDto.setPureWaterDayDataList(pureWaterDayDataList);
            resultDto.setPureWaterMonthDataList(pureWaterMonthDataList);
            resultDto.setFreezeWaterDayDataList(freezeWaterDayDataList);
            resultDto.setFreezeWaterMonthDataList(freezeWaterMonthDataList);
            resultDto.setElectDayDataList(electDayDataList);
            resultDto.setElectMonthDataList(electMonthDataList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(this.getClass(),"waterElectricityCollectDataDTO eclipse",e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }

    @ApiMethodDoc(apiCode = "GasCollectData" , name = "气、汽综合数据")
    public GasCollectDataDTO lineCollectRetDTO(){
        GasCollectDataDTO resultDto = new GasCollectDataDTO();
        try {
            Date dayBeforDate = DateUtils.getBeforDayStartDay(1);
            Date dayAfterDate = DateUtils.getBeforDayStartDay(0);
            Date monthBeforDate = DateUtils.getBeforMonthStartDay(0);
            Date monthAfterDate = DateUtils.getBeforDayStartDay(0);
//
//            Date todayStart = DateUtils.getBeforDayStartDay(0);
//            Date todayEnd = DateUtils.getBeforDayEndDay(0);
//            Date curMonthStart = DateUtils.getBeforMonthStartDay(0);
//            Date curMonthEnd = DateUtils.getBeforMonthEndDay(0);
            List<TwoDaysGasDataDTO> bigGasDayDataList = bigGasEveryDayDataDAO.queryTwoDaysBigGasByQueryData(Constant.gasNamelist, dayBeforDate, dayAfterDate);
//            bigGasDayDataList = Constant.gasNamelist.stream().map(gasName -> new GasCollectDataDTO.GasCollectData(gasName,3600,5000)).collect(Collectors.toList());
            List<TwoDaysGasDataDTO> bigGasMonthDataList = bigGasEveryDayDataDAO.queryTwoDaysBigGasByQueryData(Constant.gasNamelist, monthBeforDate, monthAfterDate);
//            bigGasMonthDataList = Constant.gasNamelist.stream().map(gasName -> new GasCollectDataDTO.GasCollectData(gasName,80000,90000)).collect(Collectors.toList());

            List<TwoDaysGasDataDTO> natGasDayDataList = natgasEveryDayDataDAO.queryTwoDaysNatGasByQueryData(Constant.gasTypelist, dayBeforDate, dayAfterDate);
//            natGasDayDataList = Constant.gasTypelist.stream().map(gasName -> new GasCollectDataDTO.GasCollectData(gasName,300,350)).collect(Collectors.toList());

            List<TwoDaysGasDataDTO> natGasMonthDataList = natgasEveryDayDataDAO.queryTwoDaysNatGasByQueryData(Constant.gasTypelist, monthBeforDate, monthAfterDate);
//            natGasMonthDataList = Constant.gasTypelist.stream().map(gasName -> new GasCollectDataDTO.GasCollectData(gasName,6000,7000)).collect(Collectors.toList());

            resultDto.setBigGasDayCollectDataList(bigGasDayDataList);
            resultDto.setBigGasMonthCollectDataList(bigGasMonthDataList);
            resultDto.setNatGasDayCollectDataList(natGasDayDataList);
            resultDto.setNatGasMonthCollectDataList(natGasMonthDataList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(this.getClass(),"lineCollectRetDTO eclipse",e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }

    @ApiMethodDoc(apiCode = "HumitureData" , name = "温湿度数据")
    public HumitureDataDTO humitureDataDTO(){
        HumitureDataDTO resultDto = new HumitureDataDTO();
        try {
            List<String> factoryList = Lists.newArrayList("ARRAY","CF","CELL","SL");
            List<HumitureDataDTO.HumitureData> humitureDataList = humitureRealTimeDataDAO.queryData(factoryList);
//            humitureDataList = factoryList.stream().map(factory ->{
//                HumitureDataDTO.HumitureData data = new HumitureDataDTO.HumitureData();
//                data.setFactory(factory);
//                data.setTemperature(RandomUtils.randomFloat(20,30));
//                data.setCleanliness(RandomUtils.randomFloat(70,90));
//                data.setHumidity(RandomUtils.randomFloat(50,70));
//                return data;
//            }).collect(Collectors.toList());
            resultDto.setHumitureDataList(humitureDataList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(this.getClass(),"humitureDataDTO eclipse",e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }
}
