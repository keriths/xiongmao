package com.xm.platform.util;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by fanshuai on 17/11/14.
 */
public class DateUtils {
    public static final Map<Integer,Integer> monthToQuarterMap = MapUtils.newMap(1,1,2,1,3,1,4,2,5,2,6,2,7,3,8,3,9,3,10,4,11,4,12,4);
    public static final Map<Integer,Integer> quarterToStartMonthMap = MapUtils.newMap(1,1,2,4,3,7,4,10);
    public static final Map<Integer,Integer> quarterToEndMonthMap = MapUtils.newMap(1,3,2,6,3,9,4,12);
    /**
     *
     * @param beforDayNum
     * @return
     */
    public static Date getBeforDayStartDay(int beforDayNum){
        DateTime curDateTime = new DateTime();
        return curDateTime.plusDays(-beforDayNum).millisOfDay().withMinimumValue().toDate();
    }

    public static Date getBeforHourStartDay(int beforHourNum){
        DateTime curDateTime = new DateTime();
        return curDateTime.plusHours(-beforHourNum).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0).toDate();
    }
    /**
     * 获得前几个月日期当月的最开始的时间 当前时间向前推${beforMonthNum}自然月的当月的最小时间   1号的0点0分0秒
     * @param beforMonthNum
     * @return
     */
    public static Date getBeforMonthStartDay(int beforMonthNum){
        DateTime curDateTime = new DateTime();
        return curDateTime.plusMonths(-beforMonthNum).dayOfMonth().withMinimumValue().millisOfDay().withMinimumValue().toDate();
    }
    /**
     *获得前几个季度的开始时间
     * @param beforQuarterNum
     * @return
     */
    public static Date getBeforQuarterStartDay(int beforQuarterNum){
        DateTime curDateTime = new DateTime();
        int curQuarter = monthToQuarterMap.get(curDateTime.monthOfYear().get());
        int curQuarterStartMonth = quarterToStartMonthMap.get(curQuarter);
        return curDateTime.dayOfMonth().withMinimumValue().millisOfDay().withMinimumValue().withMonthOfYear(curQuarterStartMonth).plusMonths(-beforQuarterNum*3).toDate();
    }

    /**
     * 获得当月时间的最大日期 当月最后一天的23点59分59秒
     * @return
     */
    public static Date getCurMonthEndDay(){
        DateTime curDateTime = new DateTime();
        return curDateTime.dayOfMonth().withMaximumValue().millisOfDay().withMaximumValue().toDate();
    }
    /**
     * 获得当前季度的结束时间
     * @return
     */
    public static Date getQuarterEndDay(){
        DateTime curDateTime = new DateTime();
        int curQuarter = monthToQuarterMap.get(curDateTime.monthOfYear().get());
        int curQuarterEndMonth = quarterToEndMonthMap.get(curQuarter);
        return curDateTime.dayOfMonth().withMinimumValue().withMonthOfYear(curQuarterEndMonth).dayOfMonth().withMaximumValue().millisOfDay().withMaximumValue().toDate();
    }


    public static List<String> getDayStrList(Date begin,Date end){
        DateTime beginTime = new DateTime(begin);
        DateTime endTime = new DateTime(end);
        begin = beginTime.millisOfDay().withMinimumValue().toDate();
        end = endTime.millisOfDay().withMinimumValue().toDate();
        if (end.before(begin)){
            return null;
        }
        List<String> dayStrStrList = new ArrayList<String>();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd");
//        DateTime minDateTime = beginTime.millisOfDay().withMinimumValue();
        DateTime minDateTime = new DateTime(begin);
        while (minDateTime.toDate().before(end)){
            dayStrStrList.add(format.format(minDateTime.toDate()));
            minDateTime = minDateTime.plusDays(1).toDateTime();
        }
        dayStrStrList.add(format.format(end));
        return dayStrStrList;
    }

    public static List<String> getMonthStrList(Date begin,Date end){
        DateTime beginTime = new DateTime(begin);
        DateTime endTime = new DateTime(end);
        begin = beginTime.dayOfMonth().withMinimumValue().millisOfDay().withMinimumValue().toDate();
        end =     endTime.dayOfMonth().withMinimumValue().millisOfDay().withMinimumValue().toDate();
        if (end.before(begin)){
            return null;
        }
        List<String> dayStrStrList = new ArrayList<String>();
        SimpleDateFormat format = new SimpleDateFormat("MM月");
        DateTime minDateTime = new DateTime(begin);
        while (minDateTime.toDate().before(end)){
            dayStrStrList.add(format.format(minDateTime.toDate()));
            minDateTime = minDateTime.plusMonths(1).toDateTime();
        }
        dayStrStrList.add(format.format(end));
        return dayStrStrList;
    }
    public static String getQuarterStr(Date day){
        DateTime d = new DateTime(day);
        return monthToQuarterMap.get(d.monthOfYear().get())+"季度";
    }
    public static List<String> getQuarterStrList(Date begin,Date end){
        if (end.before(begin)){
            return null;
        }
        DateTime beginTime = new DateTime(begin);
        DateTime endTime = new DateTime(end);
        int curBeginQuarter = monthToQuarterMap.get(beginTime.monthOfYear().get());
        int curEndQuarter = monthToQuarterMap.get(endTime.monthOfYear().get());
        int curBeginQuarterFirstMonth = quarterToStartMonthMap.get(curBeginQuarter);
        int curEndQuarterFirstMonth = quarterToStartMonthMap.get(curEndQuarter);
        begin = beginTime.dayOfMonth().withMinimumValue().millisOfDay().withMinimumValue().withMonthOfYear(curBeginQuarterFirstMonth).toDate();
        end =     endTime.dayOfMonth().withMinimumValue().millisOfDay().withMinimumValue().withMonthOfYear(curEndQuarterFirstMonth).toDate();
        if (end.before(begin)){
            return null;
        }
        List<String> dayStrStrList = new ArrayList<String>();
        DateTime minDateTime = new DateTime(begin);
        while (minDateTime.toDate().before(end)){
            dayStrStrList.add(getQuarterStr(minDateTime.toDate()));
            minDateTime = minDateTime.plusMonths(3).toDateTime();
        }
        dayStrStrList.add(getQuarterStr(end));
        return dayStrStrList;
    }

    public static List<String> getHourStrList(Date begin,Date end){
        DateTime beginTime = new DateTime(begin);
        DateTime endTime = new DateTime(end);
        begin = beginTime.minuteOfHour().withMinimumValue().secondOfMinute().withMinimumValue().millisOfSecond().withMinimumValue().toDate();
        end =     endTime.minuteOfHour().withMinimumValue().secondOfMinute().withMinimumValue().millisOfSecond().withMinimumValue().toDate();
        if (end.before(begin)){
            return null;
        }
        List<String> dayStrStrList = new ArrayList<String>();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        DateTime minDateTime = new DateTime(begin);
        while (minDateTime.toDate().before(end)){
            dayStrStrList.add(format.format(minDateTime.toDate()));
            minDateTime = minDateTime.plusHours(1).toDateTime();
        }
        dayStrStrList.add(format.format(end));
        return dayStrStrList;
    }

    public static String getStrDate(Date day,String format){
        return new SimpleDateFormat(format).format(day);
    }

    public static void main(String[] args){
        System.out.println(getStrDate(getBeforQuarterStartDay(0), "yyyyMMdd HH:mm:ss"));
        System.out.println(getHourStrList(getBeforHourStartDay(1), new Date()));
        System.out.println(getDayStrList(getBeforDayStartDay(1), new Date()));
        System.out.println(getMonthStrList(getBeforMonthStartDay(1), new Date()));
        System.out.println(getQuarterStrList(getBeforQuarterStartDay(1),new Date()));
    }

}
