package com.xm.service.apiimpl.pc.fmcs.humiture;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.DateUtils;
import com.xm.platform.util.LogUtils;
import com.xm.platform.util.MapUtils;
import com.xm.service.apiimpl.pc.fmcs.humiture.dto.HumitureDataRetDTO;
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
            if(Constant.placeEquipmentListMap.containsKey(place)&&!StringUtils.isEmpty(equipment) && !Constant.placeEquipmentListMap.containsValue(equipment)){
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("equipment参数错误,请传入【" + equList + "】");
                return resultDto;
            }

            Date beginDate = DateUtils.getBeforMinuteStartDay(5);
            Date endDate = new Date();
            List<String> dateList = DateUtils.getMinuteStrList(beginDate,endDate);
            List<HumitureDataRetDTO.HumitureData> queryList = humitureDataDAO.queryHumiture(factory,place,equipment,beginDate,endDate);
            Map<String,HumitureDataRetDTO.HumitureData> queryMap = MapUtils.listToMap(queryList,"getPeriodDate");
            List<HumitureDataRetDTO.HumitureData> htDateList = new ArrayList<HumitureDataRetDTO.HumitureData>();
            for(String t:dateList){
                HumitureDataRetDTO.HumitureData ht = new HumitureDataRetDTO.HumitureData();
                ht.setPeriodDate(t);
                HumitureDataRetDTO.HumitureData htD = null;
                if(!CollectionUtils.isEmpty(queryMap)){
                    htD = queryMap.get(t);
                }
                if(htD == null){
                    htD = new HumitureDataRetDTO.HumitureData(t);
                }
                htDateList.add(htD);
                resultDto.setHumitureDataList(htDateList);
            }
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
            Date beginDate = DateUtils.getBeforMinuteStartDay(0);
            Date endDate = new Date();
            List<String> dateList = DateUtils.getSecondStrList(beginDate,endDate);
            //List<String> dateList = DateUtils.getMinuteStrList(beginDate,endDate);
            List<HumiturePlaceDate.HtPeDate> queryList = humitureDataDAO.queryFactoryHumiture(factory,beginDate,endDate);
            Map<String,HumiturePlaceDate.HtPeDate> queryMap = MapUtils.listToMap(queryList,"key");
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
                    for(String t:dateList){
                        ht.setSecondDate(t);
                        String key = e+" "+t;
                        HumiturePlaceDate.HtPeDate htD = null;
                        if(!CollectionUtils.isEmpty(queryMap)){
                            htD = queryMap.get(key);
                        }
                        if(htD == null){
                            htD = new HumiturePlaceDate.HtPeDate(e,t);
                        }
                        htDateList.add(htD);
                    }
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
