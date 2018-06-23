package com.xm.service.apiimpl.pc.person;

import com.google.common.collect.Lists;
import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.DateUtils;
import com.xm.platform.util.LogUtils;
import com.xm.platform.util.MapUtils;
import com.xm.service.dao.person.HistoryDAO;
import com.xm.service.dto.BaseRetDTO;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by fanshuai on 18/6/17.
 */
@Service("personService")
@ApiServiceDoc(name = "人员系统(完成)")
public class PersonService {
    private static Map<String,PersonInWorkData> cacheFactoryTimeData = new HashMap<>();
    String staffParrtern = "^[0-9]{1}";
    private static Map<String,List<Integer>> inMap = new HashMap<>();
    private static Map<String,List<Integer>> outMap =new HashMap<>();
    static {
        inMap.put("4A", Lists.newArrayList(421,426,431,436));
        inMap.put("4B", Lists.newArrayList(107,112,117,122));
        inMap.put("4C", Lists.newArrayList(1));
        outMap.put("4A", Lists.newArrayList(422,427,432,437));
        inMap.put("4B", Lists.newArrayList(108,113,118,123));
        inMap.put("4C", Lists.newArrayList(2));
    }
    @Resource(name = "historyDAO")
    HistoryDAO historyDAO;


    @ApiMethodDoc(apiCode = "person_latestHour5minuteData",name = "最近1小时人员数据")
    public InWorkHistoryDataRet inWorkHistoryData(){
        InWorkHistoryDataRet retDto = new InWorkHistoryDataRet();
        try {
            List<InWorkHistoryData> inWorkHistoryDataList = new ArrayList<>();
            List<Date> all5MinuteDateList = DateUtils.getAll5MinuteOfHourCurTime(new Date());
            all5MinuteDateList.stream().forEach(date -> {
                InWorkHistoryData data = new InWorkHistoryData();
                data.setDatetime(DateUtils.getStrDate(date,"HH:mm"));
                PersonInWorkData personInWorkData = getAllFactoryPersonInWorkData(date);
                data.setPersonInWorkData(personInWorkData);
                inWorkHistoryDataList.add(data);
            });
            retDto.setInWorkHistoryDataList(inWorkHistoryDataList);
            return retDto;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            retDto.setSuccess(false);
            retDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return retDto;
        }
    }

    @ApiMethodDoc(apiCode = "person_nowData",name = "当前时间人员数据")
    public AllInWorkDataRet allInWorkData(){
        AllInWorkDataRet allInWorkDataRet = new AllInWorkDataRet();
        try {
            List<AllInWorkData> allInWorkDataList =new ArrayList<>();
            Date time = new Date();
            for (Map.Entry<String,List<Integer>> entry : inMap.entrySet()){
                String factory = entry.getKey();
                PersonInWorkData personInWorkData = getPersonInWorkData(factory,time);
                AllInWorkData allInWorkData = new AllInWorkData();
                allInWorkData.setFactory(factory);
                allInWorkData.setPersonInWorkData(personInWorkData);
                allInWorkDataList.add(allInWorkData);
            }
            return allInWorkDataRet;
//            DateTime dateTime = new DateTime();
//            int minute = dateTime.minuteOfHour().get();
//            minute = (minute/5+1)*5-1;
//            Date time = dateTime.withMinuteOfHour(minute).secondOfMinute().withMaximumValue().millisOfSecond().withMaximumValue().toDate();
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            allInWorkDataRet.setSuccess(false);
            allInWorkDataRet.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return allInWorkDataRet;
        }
    }
    private PersonInWorkData getAllFactoryPersonInWorkData(Date time){
        PersonInWorkData data = new PersonInWorkData();
        inMap.entrySet().stream().forEach(entry -> {
            PersonInWorkData factoryData = getPersonInWorkData(entry.getKey(),time);
            data.setVisitorNum(data.getVisitorNum()+factoryData.getVisitorNum());
            data.setInWorkStaffNum(data.getInWorkStaffNum()+factoryData.getInWorkStaffNum());
        });
        return data;
    }
    /**
     * 获取指定时间，指定工厂的员工在岗统计数据
     * @param factory
     * @param time
     * @return
     */
    private PersonInWorkData getPersonInWorkData(String factory,Date time){
        try {
            Date curTimePlus5Minite = new DateTime().plusMinutes(5).toDate();
            if (curTimePlus5Minite.before(time)){
                return new PersonInWorkData();
            }
            String key = factory+"-"+ DateUtils.getStrDate(time,"yyyyMMddHHmm");
            PersonInWorkData cacheData = cacheFactoryTimeData.get(key);
            if (cacheData!=null){
                return cacheData;
            }
            PersonInWorkData personInWorkData = new PersonInWorkData();
            List<Integer> inPortList = inMap.get(factory);
            List<Integer> outPortList = outMap.get(factory);
            if (inPortList==null || outPortList==null){
                throw new RuntimeException("工厂("+factory+")进出口闸机port未配置");
            }
            Date startTime = null;
            Date endTime = null;
            List<HistoryData> inDataList = historyDAO.queryLatestData(inPortList, startTime, endTime);
            List<String> param3List = inDataList.stream().map(history -> history.getParam3()).collect(Collectors.toList());
            List<HistoryData> outDataList = historyDAO.queryLatestData(outPortList, startTime, endTime);
            Map<String,Date> outDateMap = new HashMap<>();
            outDataList.stream().forEach(history -> outDateMap.put(history.getParam3(),history.getLatestTime()));
            inDataList.stream().forEach(history -> {
                Date outLatest = outDateMap.get(history.getParam3());
                if (outLatest!=null && outLatest.after(history.getLatestTime())){
                    return;
                }
                Boolean isStaff = Pattern.matches(staffParrtern, history.getParam3());
                if (isStaff){
                    personInWorkData.setInWorkStaffNum(personInWorkData.getInWorkStaffNum()+1);
                }else {
                    personInWorkData.setVisitorNum(personInWorkData.getVisitorNum() + 1);
                }
            });
            Date canCacheTime = new DateTime().plusMinutes(-4).toDate();
            if (time.before(canCacheTime)){
                cacheFactoryTimeData.put(key,personInWorkData);
            }
            return personInWorkData;
        }catch (Exception e){
            LogUtils.error(this.getClass(),"人员系统查询出现异常",e);
            return new PersonInWorkData();
        }
    }
    public static class HistoryData{
        String param3;
        Date latestTime;

        public String getParam3() {
            return param3;
        }

        public void setParam3(String param3) {
            this.param3 = param3;
        }

        public Date getLatestTime() {
            return latestTime;
        }

        public void setLatestTime(Date latestTime) {
            this.latestTime = latestTime;
        }
    }
    public static class InWorkHistoryDataRet extends BaseRetDTO{
        @ApiResultFieldDesc(desc = "列表数据")
        List<InWorkHistoryData> inWorkHistoryDataList ;

        public List<InWorkHistoryData> getInWorkHistoryDataList() {
            return inWorkHistoryDataList;
        }

        public void setInWorkHistoryDataList(List<InWorkHistoryData> inWorkHistoryDataList) {
            this.inWorkHistoryDataList = inWorkHistoryDataList;
        }
    }
    public static class AllInWorkDataRet extends BaseRetDTO{
        @ApiResultFieldDesc(desc = "列表数据")
        List<AllInWorkData> allInWorkDataList ;
    }
    public static class InWorkHistoryData {
        @ApiResultFieldDesc(desc = "时间轴")
        public String datetime;
        @ApiResultFieldDesc(desc = "在岗统计数据")
        public PersonInWorkData personInWorkData;

        public String getDatetime() {
            return datetime;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }

        public PersonInWorkData getPersonInWorkData() {
            return personInWorkData;
        }

        public void setPersonInWorkData(PersonInWorkData personInWorkData) {
            this.personInWorkData = personInWorkData;
        }
    }

    public static class AllInWorkData extends BaseRetDTO{
        @ApiResultFieldDesc(desc = "工厂")
        public String factory;
        @ApiResultFieldDesc(desc = "在岗统计数据")
        public PersonInWorkData personInWorkData;

        public String getFactory() {
            return factory;
        }

        public void setFactory(String factory) {
            this.factory = factory;
        }

        public PersonService.PersonInWorkData getPersonInWorkData() {
            return personInWorkData;
        }

        public void setPersonInWorkData(PersonService.PersonInWorkData personInWorkData) {
            this.personInWorkData = personInWorkData;
        }
    }
    public static class PersonInWorkData implements Serializable{
        @ApiResultFieldDesc(desc = "在岗员工数")
        public int inWorkStaffNum = 0;
        @ApiResultFieldDesc(desc = "访客数")
        public int visitorNum = 0;

        public int getInWorkStaffNum() {
            return inWorkStaffNum;
        }

        public void setInWorkStaffNum(int inWorkStaffNum) {
            this.inWorkStaffNum = inWorkStaffNum;
        }

        public int getVisitorNum() {
            return visitorNum;
        }

        public void setVisitorNum(int visitorNum) {
            this.visitorNum = visitorNum;
        }
    }
}
