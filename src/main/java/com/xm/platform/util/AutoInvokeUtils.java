package com.xm.platform.util;

import com.xm.platform.annotations.NotAutoRunMethod;
import com.xm.platform.apidoc.ApiMethod;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by fanshuai on 19/1/1.
 */
public class AutoInvokeUtils {
    private static long runAllOnceUseTime = 1000*60*5;
    private static ExecutorService t = Executors.newFixedThreadPool(30);
    private static final Map<String,InvokeContext> invokeContextMap = new ConcurrentHashMap<>();
    public static void addInvoke(String key,ApiMethod apiMethod,Object[] param){
        invokeContextMap.put(key,new InvokeContext(apiMethod,param));
    }
    static {
        new Thread(){
            @Override
            public void run() {
                while (true){
                    try {
                        invokeAllMethod();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(1000l);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
    private static void invokeAllMethod(){
        long t1 = System.currentTimeMillis();
        Set<String> keySet = invokeContextMap.keySet();
        List<String> keyList = new ArrayList<String>(keySet.size());
        for (String key:keySet){
            keyList.add(key);
        }
        List<Future> futureList = new ArrayList<>();
        for (String key : keyList){
            try {
                Future f = t.submit(new Thread() {
                    @Override
                    public void run() {
                        try {
                            InvokeContext invokeContext = invokeContextMap.get(key);
                            ApiMethod apiMethod = invokeContext.getApiMethod();
                            Method method = apiMethod.getMethod();
                            Annotation notAutoRunMethod = method.getAnnotation(NotAutoRunMethod.class);
                            if (notAutoRunMethod!=null){
                                return;
                            }
                            Object obj = apiMethod.getMethod().invoke(apiMethod.getServiceObj(),invokeContext.getParam());
                            int minute=(int)(runAllOnceUseTime/1000/60)+9999999;
                            LocalCacheUtils.setCacheValue(key, obj, minute);
                        }catch (Exception e){

                        }
                    }
                });
                futureList.add(f);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (Future f : futureList){
            try {
                f.get();
            }catch (Exception e){

            }
        }
        long t2 = System.currentTimeMillis();
        if ((t2-t1) > runAllOnceUseTime){
            runAllOnceUseTime = t2-t1;
        }
        System.out.println("maxruntime"+runAllOnceUseTime+" thisonceruntime"+(t2-t1));
    }
    private static class InvokeContext{
        public InvokeContext(ApiMethod apiMethod,Object[] param){
            this.apiMethod=apiMethod;
            this.param=param;
        }
        ApiMethod apiMethod;
        Object[] param;

        public ApiMethod getApiMethod() {
            return apiMethod;
        }

        public void setApiMethod(ApiMethod apiMethod) {
            this.apiMethod = apiMethod;
        }

        public Object[] getParam() {
            return param;
        }

        public void setParam(Object[] param) {
            this.param = param;
        }
    }
}
