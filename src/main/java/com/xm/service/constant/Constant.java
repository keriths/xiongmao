package com.xm.service.constant;

import com.google.common.collect.Lists;
import com.xm.platform.util.MapUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by fanshuai on 17/11/19.
 */
public class Constant {

    public static final String day="day";
    public static final String month="month";
    public static final String quarter="quarter";
    /**
     * 统计时间类型天day月month季度quarter
     */
    public static final List<String> dateTypeList = Lists.newArrayList(day, month, quarter);
    /**
     * 产品id
     */
    public static final Map<String,String> productIdNameMap = MapUtils.newMap(
            "55","55寸",
            "40","40寸",
            "50","50寸",
            "60","60寸");
    /**
     * 厂别
     */
    public static final List<String> factoryList = Lists.newArrayList("SL", "OC");
    /**
     * 厂别与EQP类型
     */
    public static final Map<String,List<String>> factoryProductIdListMap = MapUtils.newMap(
            "Array",Lists.newArrayList("PHOTO","PVD","CVD","WET","DE"),
            "Cell",Lists.newArrayList("PI","FDV","ODF","HSW","KOL"),
            "CF",Lists.newArrayList("BM","ITO","PS","RGB","RML"),
            "SL-OC",Lists.newArrayList("MBD","POL","OLB","MLR","Aging")
    );
    /**
     * EQP状态
     */
    public static final List<String> statusList = Lists.newArrayList("RUN", "TRB", "WAIT", "MAN", "MNT");
    /**
     * 厂别与EQP类型
     */
    public static final Map<String,List<String>> factoryEQPStatusListMap = MapUtils.newMap(
            "Array", Lists.newArrayList("PHOTO","PVD","CVD","WET","DE"),
            "Cell",Lists.newArrayList("PI","FDV","ODF","HSW","KOL"),
            "CF",Lists.newArrayList("BM","ITO","PS","RGB","RML"),
            "SL-OC",Lists.newArrayList("MBD","POL","OLB","MLB","Aging")
    );

    /**
     * cycleTime厂别
     */
    public static final List<String> cycleTimeFactoryList=Lists.newArrayList("Array","Cell","SL-OC");
    /**
     * cycleTime产品id
     */
    public static final Map<String,String> cycleTimPproductIdNameMap = MapUtils.newMap(
            "55", "55", "50","50");
    /**
     * goodInProcess站点id名称
     */
    public static final Map<String,String> goodInProcessStepIdNameMap = MapUtils.newMap(
            "a","a","b","b","c","c","d","d","e","e","f","f","g","g");
    /**
     * 厂别
     */
    public static final List<String> factoryLists = Lists.newArrayList("Array","Cell","CF","SL-OC");

    public static final List<String> stepIdLists = Lists.newArrayList("a", "b", "c", "d", "e","f","g");
}
