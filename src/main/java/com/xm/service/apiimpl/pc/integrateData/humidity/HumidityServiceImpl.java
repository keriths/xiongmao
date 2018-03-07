package com.xm.service.apiimpl.pc.integrateData.humidity;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.LogUtils;
import com.xm.service.apiimpl.pc.integrateData.humidity.dto.GasCollectDataDTO;
import com.xm.service.apiimpl.pc.integrateData.humidity.dto.HumitureDataDTO;
import com.xm.service.apiimpl.pc.integrateData.humidity.dto.WaterElectricityCollectDataDTO;
import com.xm.service.constant.Constant;
import com.xm.service.dao.fmcs.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangshuna on 2018/3/6.
 */
@Service("HumidityServiceImpl")
@ApiServiceDoc(name = "水电气温湿度综合数据")
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

            List<WaterElectricityCollectDataDTO.WaterElectricityCollectData> tapWaterDayDataList = tapWaterEveryDayDataDAO.collectDayData();
            List<WaterElectricityCollectDataDTO.WaterElectricityCollectData> tapWaterMonthDataList = tapWaterEveryDayDataDAO.collectMonthData();

            List<WaterElectricityCollectDataDTO.WaterElectricityCollectData> pureWaterDayDataList = pureWaterEveryDayDataDAO.collectDayData();
            List<WaterElectricityCollectDataDTO.WaterElectricityCollectData> pureWaterMonthDataList = pureWaterEveryDayDataDAO.collectMonthData();

            List<WaterElectricityCollectDataDTO.WaterElectricityCollectData> freezeWaterDayDataList = freezeWaterEveryDayDataDAO.collectDayData();
            List<WaterElectricityCollectDataDTO.WaterElectricityCollectData> freezeWaterMonthDataList = freezeWaterEveryDayDataDAO.collectMonthData();

            List<WaterElectricityCollectDataDTO.WaterElectricityCollectData> electDayDataList = elecEveryHourDataDAO.collectDayData();
            List<WaterElectricityCollectDataDTO.WaterElectricityCollectData> electMonthDataList = elecEveryHourDataDAO.collectMonthData();

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
            List<GasCollectDataDTO.GasCollectData> bigGasDayDataList = bigGasEveryDayDataDAO.collectDayData(Constant.gasNamelist);
            List<GasCollectDataDTO.GasCollectData> bigGasMonthDataList = bigGasEveryDayDataDAO.collectMonthData(Constant.gasNamelist);
            List<GasCollectDataDTO.GasCollectData> natGasDayDataList = natgasEveryDayDataDAO.collectDayData(Constant.gasTypelist);
            List<GasCollectDataDTO.GasCollectData> natGasMonthDataList = natgasEveryDayDataDAO.collectMonthData(Constant.gasTypelist);

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
            List<String> factoryList = Constant.factoryLists;
            List<HumitureDataDTO.HumitureData> humitureDataList = humitureRealTimeDataDAO.queryData(factoryList);
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
