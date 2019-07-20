package com.xm.platform.util;

import org.joda.time.DateTime;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by fanshuai on 19/1/1.
 */
public class LocalCacheUtils {
    private static final Map<String,CacheValue> cacheData = new ConcurrentHashMap<>();
    static {
        new Thread(){
            @Override
            public void run() {
                while (true){
                    try {
                        deleteEndTimeKey();
                        try {
                            Thread.sleep(1000*60*60);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

            }
        }.start();;
    }
    private static void deleteEndTimeKey(){
        Date now = new Date();
        Set<String> keySet = cacheData.keySet();
        List<String> keyList = new ArrayList<String>(keySet.size());
        for (String key:keySet){
            keyList.add(key);
        }
        for (String key:keyList){
            CacheValue cacheValue =  cacheData.get(key);
            if (cacheValue!=null && cacheValue.getEndTime().before(now)){
                cacheData.remove(key);
            }
        }
    }

    /**
     * 设置缓存多少分钟过期
     * @param key
     * @param val
     * @param minutes
     */
    public static void setCacheValue(String key,Object val,int minutes){
        setCacheValue(key,val,new DateTime().plusMinutes(minutes).toDate());
    }

    /**
     * 设置缓存，什么时刻过期
     * @param key
     * @param val
     * @param endTime
     */
    public static void setCacheValue(String key,Object val,Date endTime){
        if (endTime==null){
            endTime = new DateTime().plusHours(1).toDate();
        }
        cacheData.put(key,new CacheValue(val,endTime));
    }


    public static Object getCacheValue(String key){
        CacheValue cacheValue = cacheData.get(key);
        if (cacheValue==null){
            return null;
        }
        if (cacheValue.getEndTime().before(new Date())){
            return null;
        }
        return cacheValue.getData();
    }
    private static class CacheValue{
        public CacheValue(Object data, Date endTime){
            this.data=data;
            this.endTime=endTime;
        }
        Object data;
        Date endTime;

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public Date getEndTime() {
            return endTime;
        }

        public void setEndTime(Date endTime) {
            this.endTime = endTime;
        }
    }
}
