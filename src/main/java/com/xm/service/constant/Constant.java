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
    public static final List<String> dateTypeList = Lists.newArrayList(day, month, quarter);
    public static final Map<String,String> productIdNameMap = MapUtils.newMap(
            "55", "55",
            "40","40",
            "50","50");
    public static final List<String> factoryList = Lists.newArrayList("SL", "OC");
    public static final Map<String,List<String>> factoryProductIdListMap = MapUtils.newMap(
            "Array",Lists.newArrayList("PHOTO","PVD","CVD","WET","DE"),
            "Cell",Lists.newArrayList("PI","FDV","ODF","HSW","KOL"),
            "CF",Lists.newArrayList("BM","ITO","PS","RGB","RML"),
            "SL-OC",Lists.newArrayList("MBD","POL","OLB","MLR","Aging")
    );
    public static final List<String> statusList = Lists.newArrayList("RUN", "TRB", "WAIT", "MAN", "MNT");
    public static final Map<String,List<String>> factoryEQPStatusListMap = MapUtils.newMap(
            "Array", Lists.newArrayList("PHOTO","PVD","CVD","WET","DE"),
            "Cell",Lists.newArrayList("PI","FDV","ODF","HSW","KOL"),
            "CF",Lists.newArrayList("BM","ITO","PS","RGB","RML"),
            "SL-OC",Lists.newArrayList("MBD","POL","OLB","MLB","Aging")
    );

    public static final List<String> cycleTimeFactoryList=Lists.newArrayList("Array","Cell","SL-OC");
    public static final Map<String,String> cycleTimPproductIdNameMap = MapUtils.newMap(
            "55", "55", "50","50");
}
