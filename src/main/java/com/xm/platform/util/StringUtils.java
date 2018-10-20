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

    public static String retainPoint(String str,int num){
        if (str==null){
            return str;
        }
        if (str.indexOf(".")==-1){
            return str;
        }
        int pointIndex = str.indexOf(".");
        if (num==0){
            if (str.length()>pointIndex){
                return str.substring(0,pointIndex);
            }
        }
        if (str.length()>pointIndex+1+num){
            return str.substring(0,pointIndex+1+num);
        }
        return str;
    }

    public static void main(String[] args){
        System.out.println(retainPoint("123",0));
        System.out.println(retainPoint("123.1",0));
        System.out.println(retainPoint("123.12",0));
        System.out.println(retainPoint("123.12",1));
        System.out.println(retainPoint("123.12",2));
        System.out.println(retainPoint("123.12",3));
    }

}
