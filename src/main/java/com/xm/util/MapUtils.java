package com.xm.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fanshuai on 17/11/14.
 */
public class MapUtils {
    public static Map newMap(Object ... objs){
        if (objs==null || objs.length==0){
            return null;
        }
        int i=0;
        Map m = new HashMap();
        while (objs.length>i+1){
            Object key = objs[i];
            Object val = objs[i+1];
            m.put(key,val);
            i+=2;
        }
        return m;
    }
}
