package com.xm.web.api.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fanshuai on 18/1/23.
 */
public class MonitorUtils {
    private static Map<String,List<Long>> functionUseTimeMaps = new HashMap<String, List<Long>>();
    public static void doMonitor(String function,long useTime){
        List<Long> useTimeList =  functionUseTimeMaps.get(function);
        if (useTimeList==null){
            useTimeList = new ArrayList<Long>();
            functionUseTimeMaps.put(function,useTimeList);
        }
        useTimeList.add(useTime);
    }
    public static Map<String,List<Long>> getFunctionUseTimeMaps(){
        return functionUseTimeMaps;
    }
}
