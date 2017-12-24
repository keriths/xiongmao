package com.xm.service.apiimpl.pc.fmcs.humiture;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.DateUtils;
import com.xm.platform.util.LogUtils;
import com.xm.platform.util.MapUtils;
import com.xm.service.apiimpl.pc.fmcs.humiture.dto.HumitureDate;
import com.xm.service.apiimpl.pc.fmcs.humiture.dto.HumitureRealTimeDataRetDTO;
import com.xm.service.apiimpl.pc.fmcs.humiture.dto.HumitureRealTimeDate;
import com.xm.service.apiimpl.pc.fmcs.humiture.dto.HumitureDateRetDTO;
import com.xm.service.constant.Constant;
import com.xm.service.dao.fmcs.HumitureDataDAO;
import com.xm.service.dao.fmcs.HumitureRealTimeDataDAO;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by fanshuai on 17/10/24.
 */
@Service("HumitureService")
@ApiServiceDoc(name = "FMCS04_温湿度(完成)")
public class HumitureServiceImpl {

    @Resource(name = "humitureDataDAO")
    private HumitureDataDAO humitureDataDAO;
    @Resource(name = "humitureRealTimeDataDAO")
    private HumitureRealTimeDataDAO humitureRealTimeDataDAO;

    @ApiMethodDoc(apiCode = "FMCS_humitureRealTimeData",name = "指定工厂的温湿洁净度实时数据接口")
    public HumitureRealTimeDataRetDTO humitureRtData(@ApiParamDoc(desc = "厂别,如ARRAY,CELL,CF(必填)") String factory){
        HumitureRealTimeDataRetDTO resultDto=new HumitureRealTimeDataRetDTO();
        try {
            //List<String> placeList = Constant.factoryPlaceListMap.get(factory);
            //List<String> equList = Constant.placeEquipmentListMap.get(place);
            if(!Constant.factoryPlaceListMap.containsKey(factory)){
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("factory参数错误,请传入【" + Constant.factoryPlaceListMap.keySet() + "】");
                return resultDto;
            }
            /*if(!StringUtils.isEmpty(place) && !Constant.placeEquipmentListMap.containsKey(place)){
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("place参数错误,请传入【" + Constant.placeEquipmentListMap.keySet() + "】");
                return resultDto;
            }
            if(!StringUtils.isEmpty(equipment) && !equList.contains(equipment)){
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("equipment参数错误,请传入【" + equList + "】");
                return resultDto;
            }*/

            Date beginDate = DateUtils.getBeforMinuteStartDay(5);
            Date endDate = new Date();
            //List<String> minuteList = DateUtils.getMinuteStrList(beginDate,endDate);
            List<String> secondList = DateUtils.getSecondStrList(beginDate,endDate);

            List<HumitureRealTimeDate.HumitureRealTimeDetailData> queryList = humitureRealTimeDataDAO.queryHumiture(factory,beginDate,endDate);
            Map<String,HumitureRealTimeDate.HumitureRealTimeDetailData> queryMap = MapUtils.listToMap(queryList,"getSecondDate");
            List<HumitureRealTimeDate> htDateList = new ArrayList<HumitureRealTimeDate>();
            Map<String,HumitureRealTimeDate> minuteDataMap = new HashMap<String, HumitureRealTimeDate>();
            for (String strSecond:secondList){
                String minute=strSecond.substring(0,2)+":00";
                HumitureRealTimeDate minuteData = minuteDataMap.get(minute);
                if (minuteData==null){
                    minuteData=new HumitureRealTimeDate();
                    minuteData.setPeriodDate(minute);
                    minuteData.setHumitureRealTimeDetailDataList(new ArrayList<HumitureRealTimeDate.HumitureRealTimeDetailData>());
                    minuteDataMap.put(minute,minuteData);
                    htDateList.add(minuteData);
                }
                HumitureRealTimeDate.HumitureRealTimeDetailData htDetailDate=queryMap.get(strSecond);
                if (htDetailDate == null) {
                    htDetailDate = new HumitureRealTimeDate.HumitureRealTimeDetailData(minute,strSecond);
                }
                minuteData.getHumitureRealTimeDetailDataList().add(htDetailDate);
            }
            resultDto.setHumitureRealTimeDateList(htDateList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }

    @ApiMethodDoc(apiCode = "FMCS_factoryHumitureRealTimeData",name = "工厂所有区域设备最新温湿洁净度数据接口")
    public HumitureDateRetDTO factoryHumitureRtData(@ApiParamDoc(desc = "厂别,如ARRAY,CELL,CF") String factory){
        HumitureDateRetDTO resultDto = new HumitureDateRetDTO();
        try {
            List<String> placeList = Constant.factoryPlaceListMap.get(factory);
            //List<String> equList = Constant.placeEquipmentListMp.get(place);
            if(!Constant.factoryPlaceListMap.containsKey(factory)){
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("factory参数错误,请传入【" + Constant.factoryPlaceListMap.keySet() + "】");
                return resultDto;
            }

            List<HumitureDate.HtPeDate> queryList = humitureDataDAO.queryFactoryHumiture(factory);
            Map<String,HumitureDate.HtPeDate> queryMap = MapUtils.listToMap(queryList,"getEquipment");
            List<HumitureDate> htPaDateList = new ArrayList<HumitureDate>();
            for(String p:placeList){
                HumitureDate hpe = new HumitureDate();
                hpe.setPlace(p);
                List<HumitureDate.HtPeDate> htDateList = new ArrayList<HumitureDate.HtPeDate>();
                List<String> equList = Constant.placeEquipmentListMap.get(p);
                for(String e:equList){
//                    HumitureDate.HtPeDate ht = new HumitureDate.HtPeDate();
//                    ht.setEquipment(e);
                    HumitureDate.HtPeDate htD = null;
                    if(!CollectionUtils.isEmpty(queryMap)){
                        htD = queryMap.get(e);
                    }
                    if(htD == null){
                        htD = new HumitureDate.HtPeDate(p,e);
                    }
                    htDateList.add(htD);
                }
                hpe.setHumitureDetailDateList(htDateList);
                htPaDateList.add(hpe);
            }
            resultDto.setHumitureDateList(htPaDateList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }
}
