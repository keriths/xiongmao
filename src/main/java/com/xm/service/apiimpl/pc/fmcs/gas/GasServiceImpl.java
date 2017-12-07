package com.xm.service.apiimpl.pc.fmcs.gas;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.DateUtils;
import com.xm.platform.util.LogUtils;
import com.xm.platform.util.MapUtils;
import com.xm.service.apiimpl.pc.fmcs.gas.dto.*;
import com.xm.service.constant.Constant;
import com.xm.service.dao.fmcs.GasEveryDayDataDAO;
import com.xm.service.dao.fmcs.GasRealTimeDataDAO;
import com.xm.service.dao.fmcs.NatgasEveryDayDataDAO;
import com.xm.service.dao.fmcs.NatgasRealTimeDataDAO;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by fanshuai on 17/10/24.
 */
@Service("GasService")
@ApiServiceDoc(name = "FMCS_气、汽")
public class GasServiceImpl {

    @Resource(name="natgasRealTimeDataDAO")
    private NatgasRealTimeDataDAO natgasRealTimeDataDAO;
    @Resource(name="natgasEveryDayDataDAO")
    private NatgasEveryDayDataDAO natgasEveryDayDataDAO;
    @Resource(name="bigGasRealTimeDataDAO")
    private GasRealTimeDataDAO gasRealTimeDataDAO;
    @Resource(name="bigGasEveryDayDataDAO")
    private GasEveryDayDataDAO gasEveryDayDataDAO;

    @ApiMethodDoc(apiCode = "FMCS_gasRealTime",name = "蒸汽天然气实时数据接口")
    public NatgasTimeDataRetDTO natgasRealTime(@ApiParamDoc(desc = "统计时间类型天day月month(必填)")String dateType,
                                               @ApiParamDoc(desc = "气体类型如蒸汽，天然气") String gasType,
                                               @ApiParamDoc(desc = "地点如4A/4B-工厂、4M食堂") String place){
        NatgasTimeDataRetDTO resultDto=new NatgasTimeDataRetDTO();
        try {
            return resultDto;
        }catch (Exception e){
            LogUtils.error(this.getClass(),"natgasRealTime eclipse",e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }

    @ApiMethodDoc(apiCode = "FMCS_gsaStatistics",name = "蒸汽天然气统计数据接口（按天、按月）（OK）")
    public NatgasStatisticsDataRetDTO natgasStatistics(@ApiParamDoc(desc = "统计时间类型天day月month(必填)")String dateType,
                                                       @ApiParamDoc(desc = "气体类型如蒸汽，天然气(必填)") String gasType,
                                                       @ApiParamDoc(desc = "地点如4A/4B-工厂、4M食堂") String place) {
        NatgasStatisticsDataRetDTO resultDto = new NatgasStatisticsDataRetDTO();
        try {
            if (!Constant.gasDateTypeList.contains(dateType)){
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("dateType参数错误,请传入【" + Constant.gasDateTypeList + "】");
                return resultDto;
            }
            if (!Constant.GasTypeList.contains(gasType)){
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("gasType参数错误,请传入【" + Constant.GasTypeList + "】");
                return resultDto;
            }
            if (!StringUtils.isEmpty(place) && !Constant.PlaceTypeList.contains(place)){
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("place参数错误,请传入【" + Constant.PlaceTypeList + "】");
                return resultDto;
            }

            List<String> dateList = null;
            Date beginDate = null;
            Date endDate = new Date();
            if (dateType.equals(Constant.day)){
                beginDate = DateUtils.getBeforDayStartDay(12);
                dateList = DateUtils.getDayStrList(beginDate,endDate);
            }else if (dateType.equals(Constant.month)){
                beginDate = DateUtils.getBeforMonthStartDay(11);
                dateList = DateUtils.getMonthStrList(beginDate,endDate);
            }
            List<NatgasStatisticsDataRetDTO.GsaStatisticsData>  dataList=natgasEveryDayDataDAO.queryGsaStatisticsData(dateType,gasType,place,beginDate,endDate);
            Map<String,NatgasStatisticsDataRetDTO.GsaStatisticsData> dataMap= MapUtils.listToMap(dataList,"getPeriodDate");
            List<NatgasStatisticsDataRetDTO.GsaStatisticsData> gsaStatisticsDataList =new ArrayList<NatgasStatisticsDataRetDTO.GsaStatisticsData>();
            for (String str:dateList){
                NatgasStatisticsDataRetDTO.GsaStatisticsData gsaStatisticsData =dataMap.get(str);
                if(gsaStatisticsData ==null){
                    gsaStatisticsData =new NatgasStatisticsDataRetDTO.GsaStatisticsData(str);
                }
                gsaStatisticsDataList.add(gsaStatisticsData);
            }
            resultDto.setDataList(gsaStatisticsDataList);
            return resultDto;
        } catch (Exception e) {
            LogUtils.error(this.getClass(), "natgasStatistics eclipse", e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }

    @ApiMethodDoc(apiCode = "FMCS_bigGasRealTime",name = "大宗气实时数据接口")
    public BigGasRealTimeDateRetDTO bigGasRealTime(@ApiParamDoc(desc = "大宗气名称,如“GN2,PN2,GO2，PO2，PAr,PHe,PH2”") String gasName){
        BigGasRealTimeDateRetDTO resultDto = new BigGasRealTimeDateRetDTO();
        try{
            if(!Constant.gasNamelist.contains(gasName)){
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("dateType参数错误,请传入【" + Constant.gasNamelist + "】");
                return resultDto;
            }
            Date beginDate = DateUtils.getBeforMinuteStartDay(5);
            Date endDate = new Date();
            List<String> minuteList = DateUtils.getMinuteStrList(beginDate,endDate);
            List<String> secondList = DateUtils.getSecondStrList(DateUtils.getBeforMinuteStartDay(0),endDate);

            List<BigGasRealTimeDate.GasRealTimeDate> queryList = gasRealTimeDataDAO.queryBigGasRealTimeDate(gasName,beginDate,endDate);
            Map<String,BigGasRealTimeDate.GasRealTimeDate> queryMap = MapUtils.listToMap(queryList,"key");
            List<BigGasRealTimeDate> bigGasRT = new ArrayList<BigGasRealTimeDate>();
            for(String min:minuteList){
                BigGasRealTimeDate gasRT = new BigGasRealTimeDate();
                gasRT.setPeriodDate(min);
                String minute=min.substring(0,2);
                List<BigGasRealTimeDate.GasRealTimeDate> gasDateList = new ArrayList<BigGasRealTimeDate.GasRealTimeDate>();
                for(String sec:secondList){
                    String second=sec.substring(3);
                    String s=minute+":"+second;
                    String key = min+" "+s;
                    BigGasRealTimeDate.GasRealTimeDate gasRTDate = queryMap.get(key);
                    if(gasRTDate==null){
                        gasRTDate = new BigGasRealTimeDate.GasRealTimeDate(min,sec);
                        gasRTDate.setSecondDate(s);
                    }
                    gasRTDate.setSecondDate(s);
                    gasDateList.add(gasRTDate);
                }
                gasRT.setGasRealTimeDateList(gasDateList);
                bigGasRT.add(gasRT);
            }
            resultDto.setBigGasRealTimeDateList(bigGasRT);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(this.getClass(),"bigGasRealTime eclipse",e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }

    @ApiMethodDoc(apiCode = "FMCS_Statistics",name = "大宗气统计数据接口")
    public BigGasStatisticsDateRetDTO bigGasStatistics(@ApiParamDoc(desc = "大宗气名称,如“GN2,PN2,GO2，PO2，PAr,PHe,PH2”") String gasName, @ApiParamDoc(desc = "统计时间类型天day月month(必填)")String dateType){
        BigGasStatisticsDateRetDTO resultDto = new BigGasStatisticsDateRetDTO();
        try{
            if (!Constant.gasDateTypeList.contains(dateType)){
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("dateType参数错误,请传入【" + Constant.gasDateTypeList + "】");
                return resultDto;
            }
            if(!Constant.gasNamelist.contains(gasName)){
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("gasName参数错误,请传入【" + Constant.gasNamelist + "】");
                return resultDto;
            }
            List<String> dateList = null;
            Date beginDate = null;
            Date endDate = new Date();
            if (dateType.equals(Constant.day)){
                beginDate = DateUtils.getBeforDayStartDay(12);
                dateList = DateUtils.getDayStrList(beginDate,endDate);
            }else if (dateType.equals(Constant.month)){
                beginDate = DateUtils.getBeforMonthStartDay(11);
                dateList = DateUtils.getMonthStrList(beginDate,endDate);
            }
            List<BigGasStatisticsDateRetDTO.BigGasStatisticsDate> queryList = gasEveryDayDataDAO.queryBigGasStatisticsDate(gasName,dateType,beginDate,endDate);
            Map<String,BigGasStatisticsDateRetDTO.BigGasStatisticsDate> queryMap = MapUtils.listToMap(queryList,"getPeriodDate");
            List<BigGasStatisticsDateRetDTO.BigGasStatisticsDate> bigGasSt = new ArrayList<BigGasStatisticsDateRetDTO.BigGasStatisticsDate>();
            for(String day:dateList){
                BigGasStatisticsDateRetDTO.BigGasStatisticsDate gasSt = new BigGasStatisticsDateRetDTO.BigGasStatisticsDate();
                gasSt.setPeriodDate(day);
                BigGasStatisticsDateRetDTO.BigGasStatisticsDate date = null;
                if(!CollectionUtils.isEmpty(queryMap)){
                    date = queryMap.get(day);
                }
                if(date == null){
                    date = new BigGasStatisticsDateRetDTO.BigGasStatisticsDate(day);
                }
                bigGasSt.add(date);
                resultDto.setBigGasStatisticsDateList(bigGasSt);
            }

            return resultDto;
        }catch (Exception e){
            LogUtils.error(this.getClass(),"bigGasStatistics eclipse",e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }

    }

    @ApiMethodDoc(apiCode = "FMCS_BigGasList",name ="大宗气气体列表" )
    public BigGasTabRetDTO queryBigGas(){
        BigGasTabRetDTO resultDto = new BigGasTabRetDTO();
        List<BigGasTabRetDTO.BigGas> dataDto = new ArrayList<BigGasTabRetDTO.BigGas>();
        List<String> dataList = Constant.gasNamelist;
        Iterator it = dataList.iterator();
        while(it.hasNext()){
            BigGasTabRetDTO.BigGas data = new BigGasTabRetDTO.BigGas();
            String str = (String)it.next();
            data.setGasName(str);
            dataDto.add(data);
        }
        resultDto.setBigGasesList(dataDto);
        return resultDto;
    }

}
