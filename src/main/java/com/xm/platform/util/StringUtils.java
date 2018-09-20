package com.xm.platform.util;

/**
 * Created by fanshuai on 18/9/18.
 */
public class StringUtils {

    public static String twoPoint(String str){
        if (str==null){
            return str;
        }
        if (str.indexOf(".")==-1){
            return str;
        }
        int pointIndex = str.indexOf(".");
        if (str.length()>pointIndex+1+2){
            return str.substring(0,pointIndex+3);
        }
        return str;
    }


}
