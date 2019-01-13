package com.xm.platform.util;

import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
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

    public static <K,V> Map<K,V> listToMap(List<V> listValue,String getMethodName) {
        try {
            if (CollectionUtils.isEmpty(listValue)){
                return new HashMap<>();
            }
            Map<K,V> mapValue = new HashMap<K, V>();
            if (CollectionUtils.isEmpty(listValue)){
                return mapValue;
            }
            Method method = listValue.get(0).getClass().getMethod(getMethodName);
            for (V val:listValue){
                K key = (K)method.invoke(val);
                mapValue.put(key,val);
            }
            return mapValue;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
