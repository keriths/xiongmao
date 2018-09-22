package com.xm.service.apiimpl.pc.person;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.DateUtils;
import com.xm.platform.util.LogUtils;
import com.xm.platform.util.MapUtils;
import com.xm.service.apiimpl.pc.person.dto.HistoryDTO;
import com.xm.service.apiimpl.pc.person.dto.PersonInWorkRet;
import com.xm.service.apiimpl.pc.person.dto.PersonNumDTO;
import com.xm.service.dao.person.HistoryDAO;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by fanshuai on 18/6/17.
 */
@ApiServiceDoc(name = "人员系统(完成)")
public class PersonService {
    HistoryDAO historyDAO;
    private static Map<String,List<Integer>> mapFactoryGatWayCardIds = new HashMap<>();
    private static Map<String,Map<String,Map<String,List<Integer>>>> mapJieJinshiJianXiuShi = new HashMap<>();
    private static Map<String,Map<String,List<Integer>>> mapBanGongXu = new HashMap<>();
    private static Map<String,List<Integer>> mapCanTing = new HashMap<>();
    private static Map<String,Map<String,List<Integer>>> map4a4b4c = new HashMap<>();
    static {
        mapFactoryGatWayCardIds.put("工厂入口卡ID",Lists.newArrayList(1,2,3,4));
//        Map map4c = new HashMap<>();
//        map4c.put("动力办公室",Lists.newArrayList(1, 2, 3));
//        map4c.put("4H值班室",Lists.newArrayList(1,2,3));
//        map4a4b4c.put("4C",map4c);
//
//        Map map4b = new HashMap<>();
//        map4b.put("生产办公室(成盒)",Lists.newArrayList(1,2,3));
//        map4b.put("财务办公室",Lists.newArrayList(1,2,3));
//        map4a4b4c.put("4B",map4b);
//
//        Map map4a = new HashMap<>();
//        map4a.put("自动化办公室",Lists.newArrayList(1,2,3));
//        map4a.put("阵列制造办公室",Lists.newArrayList(1,2,3));
//        map4a4b4c.put("4A",map4a);


//        mapCanTing.put("餐厅",Lists.newArrayList(1,2,3));
//
//        Map mapBanGongQu = new HashMap<>();
//        mapBanGongQu.put("4A",Lists.newArrayList(1, 2, 3));
//        mapBanGongQu.put("4B行政办公区",Lists.newArrayList(1, 2, 3));
//        mapBanGongQu.put("4B工厂办公区",Lists.newArrayList(1, 2, 3));
//        mapBanGongQu.put("4C",Lists.newArrayList(1, 2, 3));
//        mapBanGongXu.put("办公区",mapBanGongQu);

//        Map mapJianXiuShi = new HashMap<>();
//        mapJianXiuShi.put("Array",MapUtils.newMap("in",Lists.newArrayList(1,2,3),"out",Lists.newArrayList(1,2,3)));
//        mapJianXiuShi.put("CF",MapUtils.newMap("in",Lists.newArrayList(1,2,3),"out",Lists.newArrayList(1,2,3)));
//        mapJieJinshiJianXiuShi.put("检修室", mapJianXiuShi);
//
//        Map<String,Map<String,List<Integer>>> mapJieJinShi = new HashMap<>();
//        mapJieJinShi.put("Array",MapUtils.newMap("in",Lists.newArrayList(1,2,3),"out",Lists.newArrayList(1,2,3)));
//        mapJieJinShi.put("CF",MapUtils.newMap("in",Lists.newArrayList(1,2,3),"out",Lists.newArrayList(1,2,3)));
//        mapJieJinShi.put("CELL",MapUtils.newMap("in",Lists.newArrayList(1,2,3),"out",Lists.newArrayList(1,2,3)));
//        mapJieJinShi.put("SL-OC",MapUtils.newMap("in",Lists.newArrayList(1,2,3),"out",Lists.newArrayList(1,2,3)));
//        mapJieJinshiJianXiuShi.put("洁净室", mapJieJinShi);

        mapBanGongXu = jsonToMap("{\n" +
                "\t\"办公区\": {\n" +
                "\t\t\"4A\": [3065, 3009, 3072, 3080, 3070, 3228, 3099, 3109, 3023, 3024, 3033, 3108, 3100, 3094, 4627],\n" +
                "\t\t\"4B行政办公区\": [3691, 3696, 3695, 294, 295, 3773, 3815, 3676, 3744, 3759, 6251, 6260, 3866, 3876, 3973, 3847, 3856, 3860, 3865, 3926, 3929, 3921, 3915, 3919, 3913, 3908, 3898, 3900, 3889, 3893, 3887, 2626, 2633, 2651, 2607, 2615, 2597, 2598, 2606, 3967, 3970],\n" +
                "\t\t\"4B工厂办公区\": [1092, 668, 943],\n" +
                "\t\t\"4C\": [1, 2, 3]\n" +
                "\t}\n" +
                "}");

        mapJieJinshiJianXiuShi = jsonToMap("{\n" +
                "\t\"洁净室\": {\n" +
                "\t\t\"ARRAY\": {\n" +
                "\t\t\t\"in\": [7551, 7554, 7558, 7561],\n" +
                "\t\t\t\"out\": [7552, 7555, 7559, 7562]\n" +
                "\t\t},\n" +
                "\t\t\"CF\": {\n" +
                "\t\t\t\"in\": [7594, 7588, 7585, 7591],\n" +
                "\t\t\t\"out\": [7595, 7592, 7589, 7586]\n" +
                "\t\t},\n" +
                "\t\t\"Cell\": {\n" +
                "\t\t\t\"in\": [7618, 7621, 7624, 7627],\n" +
                "\t\t\t\"out\": [7619, 7622, 7625, 7628]\n" +
                "\t\t},\n" +
                "\t\t\"SL-OC\": {\n" +
                "\t\t\t\"in\": [7614, 7611, 7608, 7605, 7602, 7599],\n" +
                "\t\t\t\"out\": [7615, 7612, 7609, 7606, 7603, 7600]\n" +
                "\t\t}\n" +
                "\t},\n" +
                "\t\"检修室\": {\n" +
                "\t\t\"Array\": {\n" +
                "\t\t\t\"in\": [7551, 7554, 7558, 7561],\n" +
                "\t\t\t\"out\": [7552, 7555, 7559, 7562]\n" +
                "\t\t},\n" +
                "\t\t\"CF\": {\n" +
                "\t\t\t\"in\": [7578, 7581],\n" +
                "\t\t\t\"out\": [7579, 7582]\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}");
        map4a4b4c = jsonToMap("{\n" +
                "\t\"4A\": {\n" +
                "\t\t\"阵列培训室\": [3019, 3099],\n" +
                "\t\t\"一工厂第一会议室\": [3033],\n" +
                "\t\t\"阵列会议室2\": [3100],\n" +
                "\t\t\"阵列制造办公室\": [3065, 3009, 3072, 3080, 3070],\n" +
                "\t\t\"阵列会议室3\": [3094],\n" +
                "\t\t\"阵列会议室1\": [3108],\n" +
                "\t\t\"一工厂第三会议室\": [3024],\n" +
                "\t\t\"自动化办公室\": [4627],\n" +
                "\t\t\"生产办公室(阵列)\": [3228],\n" +
                "\t\t\"一工厂第二会议室\": [3023]\n" +
                "\t},\n" +
                "\t\"4B\": {\n" +
                "\t\t\"第五会议室\": [2778, 2786],\n" +
                "\t\t\"第八会议室\": [2856],\n" +
                "\t\t\"质量管理办公室\": [3926, 3929],\n" +
                "\t\t\"生产部会议室\": [3887],\n" +
                "\t\t\"总务工程办公室\": [3913, 3908],\n" +
                "\t\t\"销售管理办公室\": [3889, 3893],\n" +
                "\t\t\"产品研发实验室\": [3773],\n" +
                "\t\t\"生产办公室(彩膜)\": [6260, 6251],\n" +
                "\t\t\"第二会议室\": [2724, 2733],\n" +
                "\t\t\"财务办公室\": [2606],\n" +
                "\t\t\"PQA实验室\": [1092],\n" +
                "\t\t\"行政法务办公室\": [3967, 3970],\n" +
                "\t\t\"运营管理办公室\": [3915, 3919],\n" +
                "\t\t\"第一会议室\": [2709, 2701, 2685],\n" +
                "\t\t\"彩膜培训室\": [1266],\n" +
                "\t\t\"第六会议室\": [3981],\n" +
                "\t\t\"产品研发办公室\": [3815, 3676],\n" +
                "\t\t\"FAE实验室\": [943],\n" +
                "\t\t\"生产办公室(成盒)\": [6088],\n" +
                "\t\t\"技术部办公室\": [3860, 3865],\n" +
                "\t\t\"信息技术值班室\": [294, 295],\n" +
                "\t\t\"人力资源办公室\": [3898, 3900],\n" +
                "\t\t\"成盒制造办公室\": [2616, 2633, 2651],\n" +
                "\t\t\"信息技术办公室\": [3691, 3696, 3695],\n" +
                "\t\t\"产品研发制图室\": [3744, 3759],\n" +
                "\t\t\"品质整合办公室\": [3847, 3856],\n" +
                "\t\t\"IQC实验室\": [1, 2, 3],\n" +
                "\t\t\"生产部培训室\": [3887],\n" +
                "\t\t\"资材办公室\": [3887],\n" +
                "\t\t\"制程技术办公室\": [2607, 2615, 2597, 2598],\n" +
                "\t\t\"第四会议室\": [2759, 2777],\n" +
                "\t\t\"安全环保办公室\": [3921],\n" +
                "\t\t\"仓储办公室\": [668],\n" +
                "\t\t\"彩膜制造办公室\": [3866, 3876, 3873],\n" +
                "\t\t\"生产部办公室\": [6251, 6260],\n" +
                "\t\t\"第三会议室\": [2742, 2750],\n" +
                "\t\t\"第七会议室\": [4011]\n" +
                "\t},\n" +
                "\t\"4C\": {\n" +
                "\t\t\"4E值班室\": [4204],\n" +
                "\t\t\"4H值班室\": [5110],\n" +
                "\t\t\"4G值班室\": [226],\n" +
                "\t\t\"4F值班室\": [3926],\n" +
                "\t\t\"动力运行办公室\": [3065]\n" +
                "\t}\n" +
                "}");
        mapCanTing = jsonToMap("{\n" +
                "\t\"食堂\": [7364, 7370, 1355, 1243, 1325, 1307, 1356]\n" +
                "}");
    }
    public static void main(String [] args){
        System.out.println(JSON.toJSONString(mapJieJinshiJianXiuShi));
        System.out.println(JSON.toJSONString(mapBanGongXu));
        System.out.println(JSON.toJSONString(mapCanTing));
        System.out.println(JSON.toJSONString(map4a4b4c));
        System.out.println(JSON.toJSONString(mapFactoryGatWayCardIds));
//        Object o1 = JSON.toJavaObject(o,map4a4b4c.getClass());
//        System.out.println(o);
    }

    public static Map jsonToMap(String jsonStr){
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        return jsonToMap(jsonObject);
    }
    public static Map jsonToMap(JSONObject jsonObject){
        Map<Object,Object> map = JSON.toJavaObject(jsonObject,Map.class);
        if (map==null){
            return null;
        }
        for (Map.Entry<Object,Object> entry : map.entrySet()){
            Object key = entry.getKey();
            Object val = entry.getValue();
            if (val instanceof JSONObject){
                map.put(key,jsonToMap((JSONObject)val));
            }
            if (val instanceof JSONArray){
                map.put(key,jsonToList((JSONArray)val));
            }
        }
        return map;
    }
    public static List jsonToList(JSONArray jsonArray){
        List l = Lists.newArrayList(jsonArray.toArray());
        for (int i = 0;i<l.size();i++){
            Object o = l.get(i);
            if (o instanceof JSONObject){
                l.set(i,jsonToMap((JSONObject)o));
            }
            if (o instanceof JSONArray){
                l.set(i,jsonToList((JSONArray)o));
            }
        }
        return l;
    }

    @ApiMethodDoc(apiCode = "personInWork4A4B4C",name = "4A4B4C 数据")
    public PersonInWorkRet personInWork4A4B4C(@ApiParamDoc(desc = "区域，请填写4A,4B,4C")String area){
        PersonInWorkRet ret = new PersonInWorkRet();
        try {
            Date todayStartDay = DateUtils.getBeforDayStartDay(0);
            Map<String,List<Integer>> areas =  map4a4b4c.get(area);
            List<Integer> allCardIds = new ArrayList<>();
            Map<String,PersonNumDTO> personNumDTOMap = new HashMap<>();
            for (Map.Entry<String,List<Integer>> entry : areas.entrySet()){
                List<HistoryDTO> historyDTOList = null;
                try {
                    historyDTOList = historyDAO.querySigleIdCards(entry.getValue(), todayStartDay);
                }catch (Exception e){

                }

                PersonNumDTO personNumDTO = new PersonNumDTO();
                personNumDTO.setArea(entry.getKey());
                personNumDTOMap.put(entry.getKey(),personNumDTO);
                setPersonNum(personNumDTO,historyDTOList);
                allCardIds.addAll(entry.getValue());
            }
            List<HistoryDTO> historyDTOList = null;
            try {
                historyDTOList = historyDAO.selectFactoryPublicAreaInfos(mapFactoryGatWayCardIds.get("工厂入口卡ID"), allCardIds, todayStartDay);
            }catch (Exception e){

            }
            PersonNumDTO personNumDTO = new PersonNumDTO();
            personNumDTO.setArea("公共区域");
            personNumDTOMap.put("公共区域",personNumDTO);
            setPersonNum(personNumDTO,historyDTOList);
            ret.setPersonNumDTOMap(personNumDTOMap);
            return ret;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            ret.setSuccess(false);
            ret.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return ret;
        }
    }
    @ApiMethodDoc(apiCode = "personInWorkAll",name = "汇总数据")
    public PersonInWorkRet personInWorkAll(){
        PersonInWorkRet ret = new PersonInWorkRet();
        try {
            Map<String,PersonNumDTO> personNumDTOMap = new HashMap<>();
            Date todayStartDay = DateUtils.getBeforDayStartDay(0);
            List<HistoryDTO> historyDTOList = null;
            try {
                historyDTOList = historyDAO.querySigleIdCards(mapFactoryGatWayCardIds.get("工厂入口卡ID"), todayStartDay);
            }catch (Exception e){
                LogUtils.error(getClass(), e);
            }
            PersonNumDTO personNumDTO = new PersonNumDTO();
            personNumDTO.setArea("园区");
            personNumDTOMap.put("园区", personNumDTO);
            setPersonNum(personNumDTO, historyDTOList);
            personNumDTO.setVisitorNum(0);
            ret.setPersonNumDTOMap(personNumDTOMap);
            return ret;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            ret.setSuccess(false);
            ret.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return ret;
        }
    }
    @ApiMethodDoc(apiCode = "personInWorkCanTing",name = "餐厅数据")
    public PersonInWorkRet personInWorkCanTing(){
        PersonInWorkRet ret = new PersonInWorkRet();
        try {
            Map<String,PersonNumDTO> personNumDTOMap = new HashMap<>();
            Date todayStartDay = DateUtils.getBeforDayStartDay(0);
            List<HistoryDTO> historyDTOList = null;
            try {
                historyDTOList = historyDAO.querySigleIdCards(mapCanTing.get("餐厅"), todayStartDay);
            }catch (Exception e){

            }
            PersonNumDTO personNumDTO = new PersonNumDTO();
            personNumDTO.setArea("餐厅");
            personNumDTOMap.put("餐厅",personNumDTO);
            setPersonNum(personNumDTO,historyDTOList);
            ret.setPersonNumDTOMap(personNumDTOMap);
            return ret;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            ret.setSuccess(false);
            ret.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return ret;
        }
    }

    @ApiMethodDoc(apiCode = "personInWorkJieJinShiAndJianXiuShi",name = "洁净室,检修室数据")
    public PersonInWorkRet personInWorkJieJinShiAndJianXiuShi(@ApiParamDoc(desc = "区域，请填写洁净室，检修室")String area){
        PersonInWorkRet ret = new PersonInWorkRet();
        try {
            Map<String,PersonNumDTO> personNumDTOMap = new HashMap<>();
            Date todayStartDay = DateUtils.getBeforDayStartDay(0);
            Map<String,Map<String,List<Integer>>> areaMap =  mapJieJinshiJianXiuShi.get(area);
            for (Map.Entry<String,Map<String,List<Integer>>> areaEntry : areaMap.entrySet()){
                String areaName = areaEntry.getKey();
                Map<String,List<Integer>> cardIdMap = areaEntry.getValue();
                List<Integer> inIds = cardIdMap.get("in");
                List<Integer> outIds = cardIdMap.get("out");
                List<HistoryDTO> historyDTOList = null;
                try {
                    historyDTOList = historyDAO.queryLaeastInOutCards(inIds, outIds, todayStartDay);
                }catch (Exception e){

                }
                PersonNumDTO personNumDTO = new PersonNumDTO();
                personNumDTO.setArea(areaName);
                personNumDTOMap.put(areaName,personNumDTO);
                setPersonNum(personNumDTO,historyDTOList);
                ret.setPersonNumDTOMap(personNumDTOMap);
            }
            return  ret;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            ret.setSuccess(false);
            ret.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return ret;
        }
    }

    @ApiMethodDoc(apiCode = "personInWorkBanGongQu",name = "办公区数据")
    public PersonInWorkRet personInWorkBanGongQu(){
        PersonInWorkRet ret = new PersonInWorkRet();
        try {
            Map<String,PersonNumDTO> personNumDTOMap = new HashMap<>();
            Date todayStartDay = DateUtils.getBeforDayStartDay(0);
            Map<String,List<Integer>> areaMap = mapBanGongXu.get("办公区");
            for (Map.Entry<String,List<Integer>> areaEntry : areaMap.entrySet()){
                String areaName = areaEntry.getKey();
                List<Integer> cardIds = areaEntry.getValue();
                List<HistoryDTO> historyDTOList = null;
                try {
                    historyDTOList = historyDAO.querySigleIdCards(cardIds, todayStartDay);
                }catch (Exception e){

                }
                PersonNumDTO personNumDTO = new PersonNumDTO();
                personNumDTO.setArea(areaName);
                personNumDTOMap.put(areaName,personNumDTO);
                setPersonNum(personNumDTO,historyDTOList);
                ret.setPersonNumDTOMap(personNumDTOMap);
            }
            return  ret;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            ret.setSuccess(false);
            ret.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return ret;
        }
    }

    private void setPersonNum(PersonNumDTO personNum,List<HistoryDTO> historyDTOList){
        Integer yuanGongNum = 0;
        Integer changShangNum = 0;
        if (historyDTOList!=null){
            for (HistoryDTO historyDTO : historyDTOList){
                if (isYuanGongCard(historyDTO.getParam3())){
                    yuanGongNum++;
                }else {
                    changShangNum++;
                }
            }
        }
        personNum.setYuanGongNum(yuanGongNum);
        personNum.setChangShangNum(changShangNum);
    }
    private boolean isYuanGongCard(String param3){
        if (param3==null){
            return false;
        }
        try {
            Long.parseLong(param3);
            return true;
        }catch (Exception e){
            return false;
        }
    }
//
//    private static Map<String,PersonInWorkData> cacheFactoryTimeData = new HashMap<>();
//    String staffParrtern = "^[0-9]{1}";
//    private static Map<String,List<Integer>> inMap = new HashMap<>();
//    private static Map<String,List<Integer>> outMap =new HashMap<>();
//    static {
//        inMap.put("4A", Lists.newArrayList(421,426,431,436));
//        inMap.put("4B", Lists.newArrayList(107,112,117,122));
//        inMap.put("4C", Lists.newArrayList(1));
//        outMap.put("4A", Lists.newArrayList(422,427,432,437));
//        inMap.put("4B", Lists.newArrayList(108,113,118,123));
//        inMap.put("4C", Lists.newArrayList(2));
//    }
//
//
//
//    @ApiMethodDoc(apiCode = "person_latestHour5minuteData",name = "最近1小时人员数据")
//    public InWorkHistoryDataRet inWorkHistoryData(){
//        InWorkHistoryDataRet retDto = new InWorkHistoryDataRet();
//        try {
//            List<InWorkHistoryData> inWorkHistoryDataList = new ArrayList<>();
//            List<Date> all5MinuteDateList = DateUtils.getAll5MinuteOfHourCurTime(new Date());
//            all5MinuteDateList.stream().forEach(date -> {
//                InWorkHistoryData data = new InWorkHistoryData();
//                data.setDatetime(DateUtils.getStrDate(date,"HH:mm"));
//                PersonInWorkData personInWorkData = getAllFactoryPersonInWorkData(date);
//                data.setPersonInWorkData(personInWorkData);
//                inWorkHistoryDataList.add(data);
//            });
//            retDto.setInWorkHistoryDataList(inWorkHistoryDataList);
//            return retDto;
//        }catch (Exception e){
//            LogUtils.error(getClass(), e);
//            retDto.setSuccess(false);
//            retDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
//            return retDto;
//        }
//    }
//
//    @ApiMethodDoc(apiCode = "person_nowData",name = "当前时间人员数据")
//    public AllInWorkDataRet allInWorkData(){
//        AllInWorkDataRet allInWorkDataRet = new AllInWorkDataRet();
//        try {
//            List<AllInWorkData> allInWorkDataList =new ArrayList<>();
//            Date time = new Date();
//            for (Map.Entry<String,List<Integer>> entry : inMap.entrySet()){
//                String factory = entry.getKey();
//                PersonInWorkData personInWorkData = getPersonInWorkData(factory,time);
//                AllInWorkData allInWorkData = new AllInWorkData();
//                allInWorkData.setFactory(factory);
//                allInWorkData.setPersonInWorkData(personInWorkData);
//                allInWorkDataList.add(allInWorkData);
//            }
//            return allInWorkDataRet;
////            DateTime dateTime = new DateTime();
////            int minute = dateTime.minuteOfHour().get();
////            minute = (minute/5+1)*5-1;
////            Date time = dateTime.withMinuteOfHour(minute).secondOfMinute().withMaximumValue().millisOfSecond().withMaximumValue().toDate();
//        }catch (Exception e){
//            LogUtils.error(getClass(), e);
//            allInWorkDataRet.setSuccess(false);
//            allInWorkDataRet.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
//            return allInWorkDataRet;
//        }
//    }
//    private PersonInWorkData getAllFactoryPersonInWorkData(Date time){
//        PersonInWorkData data = new PersonInWorkData();
//        inMap.entrySet().stream().forEach(entry -> {
//            PersonInWorkData factoryData = getPersonInWorkData(entry.getKey(),time);
//            data.setVisitorNum(data.getVisitorNum()+factoryData.getVisitorNum());
//            data.setInWorkStaffNum(data.getInWorkStaffNum()+factoryData.getInWorkStaffNum());
//        });
//        return data;
//    }
//    /**
//     * 获取指定时间，指定工厂的员工在岗统计数据
//     * @param factory
//     * @param time
//     * @return
//     */
//    private PersonInWorkData getPersonInWorkData(String factory,Date time){
//        try {
//            Date curTimePlus5Minite = new DateTime().plusMinutes(5).toDate();
//            if (curTimePlus5Minite.before(time)){
//                return new PersonInWorkData();
//            }
//            String key = factory+"-"+ DateUtils.getStrDate(time,"yyyyMMddHHmm");
//            PersonInWorkData cacheData = cacheFactoryTimeData.get(key);
//            if (cacheData!=null){
//                return cacheData;
//            }
//            PersonInWorkData personInWorkData = new PersonInWorkData();
//            List<Integer> inPortList = inMap.get(factory);
//            List<Integer> outPortList = outMap.get(factory);
//            if (inPortList==null || outPortList==null){
//                throw new RuntimeException("工厂("+factory+")进出口闸机port未配置");
//            }
//            Date startTime = null;
//            Date endTime = null;
//            List<HistoryData> inDataList = historyDAO.queryLatestData(inPortList, startTime, endTime);
//            List<String> param3List = inDataList.stream().mapJieJinshiJianXiuShi(history -> history.getParam3()).collect(Collectors.toList());
//            List<HistoryData> outDataList = historyDAO.queryLatestData(outPortList, startTime, endTime);
//            Map<String,Date> outDateMap = new HashMap<>();
//            outDataList.stream().forEach(history -> outDateMap.put(history.getParam3(),history.getLatestTime()));
//            inDataList.stream().forEach(history -> {
//                Date outLatest = outDateMap.get(history.getParam3());
//                if (outLatest!=null && outLatest.after(history.getLatestTime())){
//                    return;
//                }
//                Boolean isStaff = Pattern.matches(staffParrtern, history.getParam3());
//                if (isStaff){
//                    personInWorkData.setInWorkStaffNum(personInWorkData.getInWorkStaffNum()+1);
//                }else {
//                    personInWorkData.setVisitorNum(personInWorkData.getVisitorNum() + 1);
//                }
//            });
//            Date canCacheTime = new DateTime().plusMinutes(-4).toDate();
//            if (time.before(canCacheTime)){
//                cacheFactoryTimeData.put(key,personInWorkData);
//            }
//            return personInWorkData;
//        }catch (Exception e){
//            LogUtils.error(this.getClass(),"人员系统查询出现异常",e);
//            return new PersonInWorkData();
//        }
//    }
//    public static class HistoryData{
//        String param3;
//        Date latestTime;
//
//        public String getParam3() {
//            return param3;
//        }
//
//        public void setParam3(String param3) {
//            this.param3 = param3;
//        }
//
//        public Date getLatestTime() {
//            return latestTime;
//        }
//
//        public void setLatestTime(Date latestTime) {
//            this.latestTime = latestTime;
//        }
//    }
//    public static class InWorkHistoryDataRet extends BaseRetDTO{
//        @ApiResultFieldDesc(desc = "列表数据")
//        List<InWorkHistoryData> inWorkHistoryDataList ;
//
//        public List<InWorkHistoryData> getInWorkHistoryDataList() {
//            return inWorkHistoryDataList;
//        }
//
//        public void setInWorkHistoryDataList(List<InWorkHistoryData> inWorkHistoryDataList) {
//            this.inWorkHistoryDataList = inWorkHistoryDataList;
//        }
//    }
//    public static class AllInWorkDataRet extends BaseRetDTO{
//        @ApiResultFieldDesc(desc = "列表数据")
//        List<AllInWorkData> allInWorkDataList ;
//    }
//    public static class InWorkHistoryData {
//        @ApiResultFieldDesc(desc = "时间轴")
//        public String datetime;
//        @ApiResultFieldDesc(desc = "在岗统计数据")
//        public PersonInWorkData personInWorkData;
//
//        public String getDatetime() {
//            return datetime;
//        }
//
//        public void setDatetime(String datetime) {
//            this.datetime = datetime;
//        }
//
//        public PersonInWorkData getPersonInWorkData() {
//            return personInWorkData;
//        }
//
//        public void setPersonInWorkData(PersonInWorkData personInWorkData) {
//            this.personInWorkData = personInWorkData;
//        }
//    }
//
//    public static class AllInWorkData extends BaseRetDTO{
//        @ApiResultFieldDesc(desc = "工厂")
//        public String factory;
//        @ApiResultFieldDesc(desc = "在岗统计数据")
//        public PersonInWorkData personInWorkData;
//
//        public String getFactory() {
//            return factory;
//        }
//
//        public void setFactory(String factory) {
//            this.factory = factory;
//        }
//
//        public PersonService.PersonInWorkData getPersonInWorkData() {
//            return personInWorkData;
//        }
//
//        public void setPersonInWorkData(PersonService.PersonInWorkData personInWorkData) {
//            this.personInWorkData = personInWorkData;
//        }
//    }
//    public static class PersonInWorkData implements Serializable{
//        @ApiResultFieldDesc(desc = "在岗员工数")
//        public int inWorkStaffNum = 0;
//        @ApiResultFieldDesc(desc = "访客数")
//        public int visitorNum = 0;
//
//        public int getInWorkStaffNum() {
//            return inWorkStaffNum;
//        }
//
//        public void setInWorkStaffNum(int inWorkStaffNum) {
//            this.inWorkStaffNum = inWorkStaffNum;
//        }
//
//        public int getVisitorNum() {
//            return visitorNum;
//        }
//
//        public void setVisitorNum(int visitorNum) {
//            this.visitorNum = visitorNum;
//        }
//    }


    public HistoryDAO getHistoryDAO() {
        return historyDAO;
    }

    public void setHistoryDAO(HistoryDAO historyDAO) {
        this.historyDAO = historyDAO;
    }
}
