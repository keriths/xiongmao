package com.xm.platform.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.Message;

/**
 * Created by fanshuai on 17/11/19.
 */
public class LogUtils {
//    private static final Logger logger = LogManager.getLogger(LogUtils.class.getName());

    public static void error(Class c,String msg){
        LogManager.getLogger(c.getName()).error(msg);
    }
    public static void error(Class c,Throwable throwable){
        LogManager.getLogger(c.getName()).error(throwable);
    }
    public static void error(Class c,String msg, Throwable throwable){
        LogManager.getLogger(c.getName()).error(msg,throwable);
    }
    public static void info(Class c,String msg){
        LogManager.getLogger(c.getName()).info(msg);
    }
    public static void info(Class c,String msg, Throwable throwable){
        LogManager.getLogger(c.getName()).info(msg, throwable);
    }
}
