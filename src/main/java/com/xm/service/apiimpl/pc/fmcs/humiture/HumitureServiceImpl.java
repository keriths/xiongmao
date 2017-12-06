package com.xm.service.apiimpl.pc.fmcs.humiture;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.DateUtils;
import com.xm.platform.util.LogUtils;
import com.xm.platform.util.MapUtils;
import com.xm.service.apiimpl.pc.fmcs.humiture.dto.HumitureDataRetDTO;
import com.xm.service.apiimpl.pc.fmcs.humiture.dto.HumitureDate;
import com.xm.service.apiimpl.pc.fmcs.humiture.dto.HumiturePlaceDate;
import com.xm.service.apiimpl.pc.fmcs.humiture.dto.HumiturePlaceDateRetDTO;
import com.xm.service.constant.Constant;
import com.xm.service.dao.fmcs.HumitureDataDAO;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by fanshuai on 17/10/24.
 */
@Service("HumitureService")
@ApiServiceDoc(name = "FMCS_温湿度")
public class HumitureServiceImpl {

    @Resource(name = "humitureDataDAO")
    private HumitureDataDAO humitureDataDAO;

    @ApiMethodDoc(apiCode = "FMCS_humitureRealTimeData",name = "指定工厂的温湿洁净度实时数据接口")
    public HumitureDataRetDTO humitureRtData(@ApiParamDoc(desc = "厂别,如ARRAY,CELL,CF(必填)") String factory,
                                             @ApiParamDoc(desc = "区域,如曝光机区,PVD区,CVD区") String place,
                                             @ApiParamDoc(desc = "区域设备,如曝光机-201,PVD-201,PVD-301") String equipment){
        HumitureDataRetDTO resultDto=new HumitureDataRetDTO();
        try {
            List<String> placeList = Constant.factoryPlaceListMap.get(factory);
            List<String> equList = Constant.placeEquipmentListMap.get(place);
            if(!Constant.factoryPlaceListMap.containsKey(factory)){
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("factory参数错误,请传入【" + Constant.factoryPlaceListMap.keySet() + "】");
                return resultDto;
            }
            if(!StringUtils.isEmpty(place) && !Constant.placeEquipmentListMap.containsKey(place)){
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("place参数错误,请传入【" + Constant.placeEquipmentListMap.keySet() + "】");
                return resultDto;
            }
            if(!StringUtils.isEmpty(equipment) && !equList.contains(equipment)){
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("equipment参数错误,请传入【" + equList + "】");
                return resultDto;
            }

            Date beginDate = DateUtils.getBeforMinuteStartDay(5);
            Date endDate = new Date();
            List<String> minuteList = DateUtils.getMinuteStrList(beginDate,endDate);
            List<String> secondList = DateUtils.getSecondStrList(DateUtils.getBeforMinuteStartDay(0),endDate);

            List<HumitureDate.HumitureDetailData> queryList = humitureDataDAO.queryHumiture(factory,place,equipment,beginDate,endDate);
            Map<String,HumitureDate.HumitureDetailData> queryMap = MapUtils.listToMap(queryList,"key");
            List<HumitureDate> htDateList = new ArrayList<HumitureDate>();
            for(String min:minuteList){
                HumitureDate htDate = new HumitureDate();
                htDate.setPeriodDate(min);
                String minute=min.substring(0,2);
                List<HumitureDate.HumitureDetailData> htDetailList = new ArrayList<HumitureDate.HumitureDetailData>();
                for(String sec:secondList){
                    String second=sec.substring(3);
                    String s=minute+":"+second;
                    String key = min+" "+s;
                    HumitureDate.HumitureDetailData htDetailDate = queryMap.get(key);
                    if(htDetailDate == null){
                        htDetailDate = new HumitureDate.HumitureDetailData(min,sec);
                        htDetailDate.setSecondDate(s);
                    }
                    htDetailDate.setSecondDate(s);
                    htDetailList.add(htDetailDate);
                }
                htDate.setHumitureDataList(htDetailList);
                htDateList.add(htDate);
            }
            resultDto.setHumitureDateList(htDateList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(this.getClass(),"humitureData eclipse",e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }

    @ApiMethodDoc(apiCode = "FMCS_factoryHumitureRealTimeData",name = "工厂所有区域设备最新温湿洁净度数据接口")
    public HumiturePlaceDateRetDTO factoryHumitureRtData(@ApiParamDoc(desc = "厂别,如ARRAY,CELL,CF") String factory){
        HumiturePlaceDateRetDTO resultDto = new HumiturePlaceDateRetDTO();
        try {
            List<String> placeList = Constant.factoryPlaceListMap.get(factory);
            //List<String> equList = Constant.placeEquipmentListMp.get(place);
            if(!Constant.factoryPlaceListMap.containsKey(factory)){
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("factory参数错误,请传入【" + Constant.factoryPlaceListMap.keySet() + "】");
                return resultDto;
            }
            /*Date beginDate = DateUtils.getBeforMinuteStartDay(0);
            Date endDate = new Date();
            List<String> dateList = DateUtils.getSecondStrList(beginDate,endDate);
            //List<String> dateList = DateUtils.getMinuteStrList(beginDate,endDate);*/
            List<HumiturePlaceDate.HtPeDate> queryList = humitureDataDAO.queryFactoryHumiture(factory);
            /*if (CollectionUtils.isEmpty(queryList)){
                //如果这一分钟数据还没有出来，取上一分钟的数据
                beginDate = DateUtils.getBeforMinuteStartDay(1);
                endDate = DateUtils.getBeforMinuteStartDay(0);
                queryList = humitureDataDAO.queryFactoryHumiture(factory);
            }*/
            Map<String,HumiturePlaceDate.HtPeDate> queryMap = MapUtils.listToMap(queryList,"getEquipment");
            List<HumiturePlaceDate> htPaDateList = new ArrayList<HumiturePlaceDate>();
            for(String p:placeList){
                HumiturePlaceDate hpe = new HumiturePlaceDate();
                hpe.setPlace(p);
                List<HumiturePlaceDate.HtPeDate> htDateList = new ArrayList<HumiturePlaceDate.HtPeDate>();
                //String p = placeList.iterator().toString();
                List<String> equList = Constant.placeEquipmentListMap.get(p);
                for(String e:equList){
                    HumiturePlaceDate.HtPeDate ht = new HumiturePlaceDate.HtPeDate();
                    ht.setEquipment(e);
                    HumiturePlaceDate.HtPeDate htD = null;
                    if(!CollectionUtils.isEmpty(queryMap)){
                        htD = queryMap.get(e);
                    }
                    if(htD == null){
                        htD = new HumiturePlaceDate.HtPeDate(e);
                    }
                    htDateList.add(htD);
                    /*for(String t:dateList){
                        ht.setSecondDate(t);
                        String key = e+" "+t;
                    }*/
                }
                hpe.setHtPeDateList(htDateList);
                htPaDateList.add(hpe);
            }
            resultDto.setHumiturePlaceDateList(htPaDateList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(this.getClass(),"HtPeDate eclipse",e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }
}
