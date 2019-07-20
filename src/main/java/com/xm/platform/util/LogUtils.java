package com.xm.platform.util;


import org.apache.commons.logging.LogFactory;

/**
 * Created by fanshuai on 17/11/19.
 */
public class LogUtils {
    public static void error(Class c,String msg){
        LogFactory.getLog(c).error(msg);
    }
    public static void error(Class c,Throwable throwable){
        LogFactory.getLog(c).error("",throwable);
    }
    public static void error(Class c,String msg, Throwable throwable){
        LogFactory.getLog(c).error(msg,throwable);
    }
    public static void info(Class c,String msg){
        LogFactory.getLog(c).info(msg);
    }
    public static void info(Class c,String msg, Throwable throwable){
        LogFactory.getLog(c).info(msg, throwable);
    }
}
