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
import com.xm.web.api.action.MonitorUtils;
import org.springframework.util.LinkedCaseInsensitiveMap;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
        mapFactoryGatWayCardIds.put("工厂入口in",Lists.newArrayList(10,12,14,16,18,20));
        mapFactoryGatWayCardIds.put("工厂入口out",Lists.newArrayList(9,11,13,15,17,19));
        map4a4b4c = jsonToMap("{\n" +
                "\t\"4A\": {\n" +
                "\t\t\"阵列培训室\": [5115, 5123],\n" +
                "\t\t\"一工厂第一会议室\": [5048],\n" +
                "\t\t\"阵列会议室2\": [5106],\n" +
                "\t\t\"阵列制造办公室\": [5077, 5022, 5086, 5091, 5085],\n" +
                "\t\t\"阵列会议室3\": [5114],\n" +
                "\t\t\"阵列会议室1\": [5124],\n" +
                "\t\t\"一工厂第三会议室\": [5040],\n" +
                "\t\t\"自动化办公室\": [6508],\n" +
                "\t\t\"生产办公室(阵列)\": [5212],\n" +
                "\t\t\"一工厂第二会议室\": [5039]\n" +
                "\t},\n" +
                "\t\"4B\": {\n" +
                "\t\t\"第五会议室\": [738, 746],\n" +
                "\t\t\"第八会议室\": [815],\n" +
                "\t\t\"质量管理办公室\": [465, 473],\n" +
                "\t\t\"生产部会议室\": [3887],\n" +
                "\t\t\"总务工程办公室\": [429, 411],\n" +
                "\t\t\"销售管理办公室\": [356, 366],\n" +
                "\t\t\"产品研发实验室\": [199],\n" +
                "\t\t\"生产办公室(彩膜)\": [3909, 3918],\n" +
                "\t\t\"第二会议室\": [684, 693],\n" +
                "\t\t\"财务办公室\": [807],\n" +
                "\t\t\"PQA实验室\": [2996],\n" +
                "\t\t\"行政法务办公室\": [928, 937],\n" +
                "\t\t\"运营管理办公室\": [437, 447],\n" +
                "\t\t\"第一会议室\": [665, 656, 637],\n" +
                "\t\t\"彩膜培训室\": [3138],\n" +
                "\t\t\"第六会议室\": [965],\n" +
                "\t\t\"产品研发办公室\": [233, 43],\n" +
                "\t\t\"FAE实验室\": [2958],\n" +
                "\t\t\"生产办公室(成盒)\": [6088],\n" +
                "\t\t\"技术部办公室\": [287, 295],\n" +
                "\t\t\"信息技术值班室\": [3010, 3011],\n" +
                "\t\t\"人力资源办公室\": [384, 392],\n" +
                "\t\t\"成盒制造办公室\": [567, 580, 598],\n" +
                "\t\t\"信息技术办公室\": [72, 81, 80],\n" +
                "\t\t\"产品研发制图室\": [162, 181],\n" +
                "\t\t\"品质整合办公室\": [3847, 3856],\n" +
                "\t\t\"IQC实验室\": [1, 2, 3],\n" +
                "\t\t\"生产部培训室\": [3887],\n" +
                "\t\t\"资材办公室\": [348],\n" +
                "\t\t\"制程技术办公室\": [558, 566, 548, 549],\n" +
                "\t\t\"第四会议室\": [719, 737],\n" +
                "\t\t\"安全环保办公室\": [455],\n" +
                "\t\t\"仓储办公室\": [2641],\n" +
                "\t\t\"彩膜制造办公室\": [296, 323, 313],\n" +
                "\t\t\"生产部办公室\": [3909, 3918],\n" +
                "\t\t\"第三会议室\": [702, 710],\n" +
                "\t\t\"第七会议室\": [1045]\n" +
                "\t},\n" +
                "\t\"4C\": {\n" +
                "\t\t\"4E值班室\": [1757],\n" +
                "\t\t\"4H值班室\": [1280],\n" +
                "\t\t\"4G值班室\": [1191],\n" +
                "\t\t\"4F值班室\": [3926],\n" +
                "\t\t\"动力运行办公室\": [3065]\n" +
                "\t}\n" +
                "}");

        mapJieJinshiJianXiuShi = jsonToMap("{\n" +
                "\t\"洁净室\": {\n" +
                "\t\t\"Array\": {\n" +
                "\t\t\t\"in\": [6325, 6328, 6331, 6334, 6337, 6340, 6343, 6346],\n" +
                "\t\t\t\"out\": [6326, 6329, 6332, 6335, 6338, 6341, 6344, 6347]\n" +
                "\t\t},\n" +
                "\t\t\"CF\": {\n" +
                "\t\t\t\"in\": [6364, 6361, 6358, 6352],\n" +
                "\t\t\t\"out\": [6365, 6362, 6359, 6353]\n" +
                "\t\t},\n" +
                "\t\t\"CELL\": {\n" +
                "\t\t\t\"in\": [6394, 6397, 6400, 6403],\n" +
                "\t\t\t\"out\": [6395, 6398, 6401, 6404]\n" +
                "\t\t},\n" +
                "\t\t\"SL-OC\": {\n" +
                "\t\t\t\"in\": [6390, 6387, 6384, 6381, 6378, 6375],\n" +
                "\t\t\t\"out\": [6391, 6388, 6385, 6382, 6379, 6376]\n" +
                "\t\t}\n" +
                "\t},\n" +
                "\t\"检修室\": {\n" +
                "\t\t\"Array\": {\n" +
                "\t\t\t\"in\": [4315, 4318],\n" +
                "\t\t\t\"out\": [4316, 4319]\n" +
                "\t\t},\n" +
                "\t\t\"CF\": {\n" +
                "\t\t\t\"in\": [6367, 6370],\n" +
                "\t\t\t\"out\": [6368, 6371]\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}");

        mapBanGongXu = jsonToMap("{\n" +
                "\t\"办公区\": {\n" +
                "\t\t\"4A\": [5077, 5022, 5086, 5091, 5085, 5212, 5115, 5123, 5039, 5040, 5048, 5124, 5106, 5114, 6508],\n" +
                "\t\t\"4B行政办公区\": [72, 81, 80, 199, 233, 43, 162, 181, 3909, 3918, 296, 323, 313, 268, 277, 287, 295, 465, 473, 455, 437, 447, 429, 411, 384, 392, 356, 366, 348, 3138, 567, 580, 598, 558, 566, 548, 549, 665, 656, 637, 684, 693, 702, 710, 719, 737, 738, 746, 807, 928, 937, 965, 1045, 815, 3010],\n" +
                "\t\t\"4B工厂办公区\": [2996, 2641, 2958, 3909, 3918],\n" +
                "\t\t\"4C\": [1280, 1191, 1757]\n" +
                "\t}\n" +
                "}");



        mapCanTing = jsonToMap("{\n" +
                "\t\"食堂\": [1586, 1592, 3221, 3110, 3191, 3172, 3222]\n" +
                "}");


    }
    public static void main(String [] args){
        System.out.println();System.out.println();
        System.out.println(JSON.toJSONString(mapJieJinshiJianXiuShi));
        System.out.println();System.out.println();
        System.out.println(JSON.toJSONString(mapBanGongXu));
        System.out.println();System.out.println();
        System.out.println(JSON.toJSONString(mapCanTing));
        System.out.println();System.out.println();
        System.out.println(JSON.toJSONString(map4a4b4c));
        System.out.println();System.out.println();
        System.out.println(JSON.toJSONString(mapFactoryGatWayCardIds));
        System.out.println();System.out.println();
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
    ExecutorService threadPool = Executors.newFixedThreadPool(100);
    @ApiMethodDoc(apiCode = "personInWork4A4B4C",name = "4A4B4C 数据")
    public PersonInWorkRet personInWork4A4B4C(@ApiParamDoc(desc = "区域，请填写4A,4B,4C")String area){
        PersonInWorkRet ret = new PersonInWorkRet();
        try {
            Date todayStartDay = DateUtils.getBeforDayStartDay(0);
            Map<String,List<Integer>> areas =  map4a4b4c.get(area);
            List<Integer> allCardIds = new ArrayList<>();
            Map<String,PersonNumDTO> personNumDTOMap = new LinkedHashMap<>();
            List<Future> tList = new ArrayList<>();
            for (Map.Entry<String,List<Integer>> entry : areas.entrySet()){
                Thread t = new Thread(){
                    @Override
                    public void run() {
                        List<HistoryDTO> historyDTOList = null;
                        try {
                            long t1 = System.currentTimeMillis();
                            historyDTOList = historyDAO.querySigleIdCards(entry.getValue(), todayStartDay);
                            long t2 = System.currentTimeMillis();
                            MonitorUtils.doMonitor("queryPerson"+entry.getValue(),t2-t1);
                        }catch (Exception e){

                        }

                        PersonNumDTO personNumDTO = new PersonNumDTO();
                        personNumDTO.setArea(entry.getKey());
                        setPersonNum(personNumDTO,historyDTOList);
                        personNumDTOMap.put(entry.getKey(), personNumDTO);
                    }
                };
                Future f = threadPool.submit(t);
                tList.add(f);
                allCardIds.addAll(entry.getValue());
            }
            Thread tt = new Thread(){
                @Override
                public void run() {
                    List<HistoryDTO> historyDTOList = null;
                    try {
//                        historyDTOList = historyDAO.selectFactoryPublicAreaInfos(mapFactoryGatWayCardIds.get("工厂入口in"),mapFactoryGatWayCardIds.get("工厂入口out"), allCardIds, todayStartDay);
                    }catch (Exception e){

                    }
                    PersonNumDTO personNumDTO = new PersonNumDTO();
                    personNumDTO.setArea("公共区域");
                    personNumDTOMap.put("公共区域",personNumDTO);
                    setPersonNum(personNumDTO, historyDTOList);
                }
            };
            Future f = threadPool.submit(tt);
            tList.add(f);
            for (Future t : tList){
                t.get();
            }
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
                historyDTOList = historyDAO.queryLaeastInOutCards(mapFactoryGatWayCardIds.get("工厂入口in"),mapFactoryGatWayCardIds.get("工厂入口out"), todayStartDay);
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
                if (isYuanGongCard(historyDTO)){
                    yuanGongNum++;
                }else {
                    changShangNum++;
                }
            }
        }
        personNum.setYuanGongNum(yuanGongNum);
        personNum.setChangShangNum(changShangNum);
    }
    private boolean isYuanGongCard(HistoryDTO historyDTO){
        if (historyDTO==null){
            return false;
        }
        if (historyDTO.getNote1()==null){
            return false;
        }
        return historyDTO.getNote1().startsWith("PD");
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
