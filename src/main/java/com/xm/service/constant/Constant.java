package com.xm.service.constant;

import com.google.common.collect.Lists;
import com.xm.platform.util.MapUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by fanshuai on 17/11/19.
 */
public class Constant {
    public static final boolean showDemoData=true;

    public static final String hour="hour";
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
    /**
     * 在制品站点
     */
    public static final List<String> stepIdLists = Lists.newArrayList("a", "b", "c", "d", "e","f","g");
    /**
     *
     */
    public static final List<String> gasNamelist = Lists.newArrayList("GN2","PN2","GO2","PO2","PAr","PHe","PH2");
    /**
     * 气的统计时间类型天day月month
     */
    public static final List<String> gasDateTypeList = Lists.newArrayList(day, month);
    /**
     *温湿度厂别和区域
     */
    public static final Map<String,List<String>> factoryPlaceListMap = MapUtils.newMap(
            "ARRAY",Lists.newArrayList("曝光机区","Coater区","PVD区","CVD区","WET区","OVEN区"),
            "CF",Lists.newArrayList("ITO区","BM区","RGM区"),
            "CELL",Lists.newArrayList("PI区","ODF区","配向区"));
    /**
     *温湿度区域设备
     */
    public static final Map<String,List<String>> placeEquipmentListMap = MapUtils.newMap(
            "曝光机区",Lists.newArrayList("曝光机-201","曝光机-301"),
            "Coater区",Lists.newArrayList("COA-201","COA-301"),
            "PVD区",Lists.newArrayList("PVD-201","PVD-301"),
            "CVD区",Lists.newArrayList("CVD-201","CVD-301"),
            "WET区",Lists.newArrayList("WET-201","WET-301"),
            "OVEN区",Lists.newArrayList("OVEN-201","OVEN-301"),
            "ITO区",Lists.newArrayList("ITO-201","ITO-301"),
            "BM区",Lists.newArrayList("BM-201","BM-301"),
            "RGM区",Lists.newArrayList("RGM-201","RGM-301"),
            "PI区",Lists.newArrayList("PI-201","PI-301"),
            "ODF区",Lists.newArrayList("ODF-201","ODF-301"),
            "配向区",Lists.newArrayList("配向-201","配向-301"));


    /**
     * 气体地点
     */
    public static final List<String> PlaceTypeList = Lists.newArrayList("4A/4B-工厂", "4M食堂");
    /**
     * 气体类型
     */
    public static final List<String> GasTypeList = Lists.newArrayList("蒸汽", "天然气","4A/4B-工厂天然气","4M食堂天然气");
    /**
     * 纯水类型
     */
    public static final List<String> PureTypeList = Lists.newArrayList("4AARW", "4AUPW", "4AUFW", "4ADIW", "4BUPW", "4BUFW", "4BDIW");
    /**
     * 冷冻水类型
     */
    public static final List<String> FreezeTypeList = Lists.newArrayList("4A低温冷冻水", "4B低温冷冻水", "4H低温冷冻水", "4C低温冷冻水",
                                                                                        "4B中温冷冻水", "4H中温冷冻水","4E中温冷冻水","4D中温冷冻水","4G中温冷冻水","4M中温冷冻水",
                                                                                        "4A温水","4B温水","4H温水","4E温水","4G温水",
                                                                                        "4B热水","4C热水","4A热水","4A热水");
    /**
     * PCW设备类型
     */
    public static final List<String> pcwEquipmentList = Lists.newArrayList("PCW-4A-101","PCW-4A-102",
                                                                            "PCW-4A-201","PCW-4A-301",
                                                                            "PCW-4A-302","PCW-4A-303",
                                                                            "PCW-4B-101","PCW-4B-201",
                                                                            "PCW-4B-301","PCW-4B-401");

    public static final List<String> wwtbDataCodeList=Lists.newArrayList("PH","F","PO4-P","CODcr","T-N","C1");

    /**
     * 温度露点类型
     */
    public static final Map<String,List<String>> mauSystemListMap = MapUtils.newMap(
            "MAU",Lists.newArrayList("MAU"),
            "4A",Lists.newArrayList("4A-L2-MAU-N-01","4A-L2-MAU-N-02","4A-M2-MAU-N-01"),
            "4B",Lists.newArrayList("4B-M2-MAU-N-01","4B-M2-MAU-N-02","4B-M2-MAU-N-03"));

    /**
     * 电的统计时间类型小时hour天day月month
     */
    public static final List<String> electricityDateTypeList = Lists.newArrayList(hour,day, month);
    /**
     * 电的区域
     */
    public static final List<String> electricityPlaceList = Lists.newArrayList("4A-ARRAY","4B-CF/CELL/SL","4C-动力站"
                                                                                            ,"4D-废水站","4E-纯水站","4M-食堂","4R-研发中心");
    /**
     * 电的区域类型
     */
    public static final List<String> electricityPlaceTypeList = Lists.newArrayList("4A","4B","其他");


}
