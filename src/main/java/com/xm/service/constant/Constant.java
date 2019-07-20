package com.xm.service.constant;

import com.google.common.collect.Lists;
import com.xm.platform.util.MapUtils;

import java.util.*;

/**
 * Created by fanshuai on 17/11/19.
 */
public class Constant {
    public static final boolean showDemoData=false;

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
    public static final Map<String,String> productIdNameMap = new LinkedHashMap<>();
    static {
        productIdNameMap.put("50","50\"");
        productIdNameMap.put("58","58\"");
    }
    /**
     * 厂别
     */
    public static final List<String> factoryList = Lists.newArrayList("SL", "OC");
    public static final Map<String,List<String>> factoryStepIdListMap = MapUtils.newMap(
            "ARRAY",Lists.newArrayList("00000",
                    "00060",
                    "00070",
                    "01200",
                    "01201",
                    "01204",
                    "01300",
                    "01301",
                    "01302",
                    "01303","01304",
                            "01305",
                            "01306"),
            "CELL",Lists.newArrayList("42100",
                    "42110",
                    "42300",
                    "43100",
                    "43110",
                    "44100",
                    "45300",
                    "46100",
                    "46310"),
            "CF",Lists.newArrayList("03000",
                    "70000",
                    "70450",
                    "70460",
                    "71000",
                    "71100",
                    "71200",
                    "71410",
                    "71411",
                    "71428",
                    "71430",
                    "71432"),
            "SL-OC",Lists.newArrayList("51000",
                    "52000",
                    "55100",
                    "55200",
                    "55500",
                    "55800",
                    "55900",
                    "61000",
                    "61610",
                    "62000",
                    "63000",
                    "64200")
    );
    /**
     * 厂别与EQP类型
     */
    public static final Map<String,List<String>> factoryProductIdListMap = MapUtils.newMap(
            "ARRAY", Lists.newArrayList("PHOTO", "PVD", "CVD", "WET", "DRY"),
            "CELL", Lists.newArrayList("PI", "FDV", "ODF", "HSW", "KOL"),
            "CF", Lists.newArrayList("BM", "ITO", "PS", "RGB", "RML"),
            "SL-OC", Lists.newArrayList("MBD","POL","OLB","MLR","OEI")
    );

    /**
     * EQP状态
     */
    public static final List<String> statusList = Lists.newArrayList("RUN", "TRB", "WAT", "MAN", "MNT");
    /**
     * 厂别与EQP类型
     */
    public static final Map<String,List<String>> factoryEQPStatusListMap = MapUtils.newMap(
            "ARRAY", Lists.newArrayList("PHOTO", "PVD", "CVD", "WET", "DRY"),
            "CELL", Lists.newArrayList("PI", "FDV", "ODF", "HSW", "KOL","IJS"),
            "CF", Lists.newArrayList("BM", "ITO", "PS", "RGB", "RML"),
            "SL-OC", Lists.newArrayList("MBD","POL","OLB","MLR","OEI")
    );

    /**
     * cycleTime厂别
     */
    public static final List<String> cycleTimeFactoryList = Lists.newArrayList("ARRAY","CELL","SL-OC");
    /**
     * 厂别
     */
    public static final List<String> factoryLists = Lists.newArrayList("ARRAY","CELL","CF","SL-OC");
    public static final List<String> showFactoryList = Lists.newArrayList("ARRAY","CELL","CF","SL-OC");
    public static final List<String> allSingleFactoryLists = Lists.newArrayList("ARRAY","CELL","CF","SL","OC");
    public static final Map<String,List<String>> factoryMap = MapUtils.newMap(
            "ARRAY", Lists.newArrayList("ARRAY"),
            "CELL", Lists.newArrayList("CELL"),
            "CF", Lists.newArrayList("CF"),
            "SL-OC", Lists.newArrayList("SL","OC")
    );
    /**
     *大宗气气体类型
     */
    public static final List<String> gasNamelist = Lists.newArrayList("GN2","PN2","GO2","PO2","PAR","PHE","PH2");
    /**
     *大宗气气体类型
     */
    public static final List<String> gasTypelist = Lists.newArrayList("蒸汽", "天然气");
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
            "曝光机区", Lists.newArrayList("曝光机-201", "曝光机-301"),
            "Coater区", Lists.newArrayList("COA-201", "COA-301"),
            "PVD区", Lists.newArrayList("PVD-201", "PVD-301"),
            "CVD区", Lists.newArrayList("CVD-201", "CVD-301"),
            "WET区", Lists.newArrayList("WET-201", "WET-301"),
            "OVEN区", Lists.newArrayList("OVEN-201", "OVEN-301"),
            "ITO区", Lists.newArrayList("ITO-201", "ITO-301"),
            "BM区", Lists.newArrayList("BM-201", "BM-301"),
            "RGM区", Lists.newArrayList("RGM-201", "RGM-301"),
            "PI区", Lists.newArrayList("PI-201", "PI-301"),
            "ODF区", Lists.newArrayList("ODF-201", "ODF-301"),
            "配向区", Lists.newArrayList("配向-201", "配向-301"));


    /**
     * 气体地点
     */
    public static final List<String> PlaceTypeList = Lists.newArrayList("4A4B工厂", "4M食堂");
    /**
     * 气体类型
     */
    public static final List<String> GasTypeList = Lists.newArrayList("蒸汽", "天然气","4A4B工厂天然气","4M食堂天然气");
    /**
     * 纯水类型
     */
    public static final List<String> PureTypeList = Lists.newArrayList("4AARW", "4AUPW", "4AUFW", "4ADIW", "4BUPW", "4BUFW", "4BDIW");
    /**
     * 冷冻水类型
     */
    public static final List<String> FreezeTypeList = Lists.newArrayList("4A低温冷冻水", "4B低温冷冻水", "4H低温冷冻水",
                                                                        "4C中温冷冻水","4A中温冷冻水", "4B中温冷冻水", "4H中温冷冻水","4E中温冷冻水","4D中温冷冻水","4G中温冷冻水","4M中温冷冻水",
                                                                                        "4A温水","4B温水","4H温水","4E温水","4G温水",
                                                                                        "4B热水","4C热水","4E热水","4M热水");
    /**
     * PCW设备类型
     */
    public static final List<String> pcwEquipmentList = Lists.newArrayList("PCW-4A-101","PCW-4A-102",
                                                                            "PCW-4A-201","PCW-4A-301",
                                                                            "PCW-4A-302","PCW-4A-303",
                                                                            "PCW-4B-101","PCW-4B-201",
                                                                            "PCW-4B-301","PCW-4B-401");

    public static final List<String> wwtbDataCodeList = Lists.newArrayList("PH", "F","T-P","CODcr","T-N","CL","Cu");

    /**
     * MAU系统类型
     */
    public static final Map<String,List<String>> mauSystemListMap = MapUtils.newMap(
            "MAU", Lists.newArrayList("MAU"),
            "4A", Lists.newArrayList("4A-L2-MAU-N-01", "4A-L2-MAU-N-02", "4A-M2-MAU-N-01", "4A-M2-MAU-N-02", "4A-M2-MAU-N-03", "4A-M2-MAU-N-04", "4A-M2-MAU-N-05",
                    "4A-M2-MAU-N-06", "4A-M2-MAU-N-07", "4A-M2-MAU-N-08", "4A-M2-MAU-N-09", "4A-M2-MAU-N-10", "4A-M2-MAU-N-11", "4A-M2-MAU-N-12", "4A-M2-MAU-N-13",
                    "4A-M2-MAU-S-01", "4A-M2-MAU-S-02", "4A-M2-MAU-S-03", "4A-M2-MAU-S-04", "4A-M2-MAU-S-05", "4A-M2-MAU-S-06", "4A-M2-MAU-S-07", "4A-M2-MAU-S-08",
                    "4A-M2-MAU-S-09", "4A-M2-MAU-S-10", "4A-M2-MAU-S-11", "4A-M2-MAU-S-12"),
            "4B", Lists.newArrayList("4B-M2-MAU-N-01", "4B-M2-MAU-N-02", "4B-M2-MAU-N-03", "4B-M2-MAU-N-04", "4B-M2-MAU-N-05", "4B-M2-MAU-N-06", "4B-M2-MAU-N-08",
                    "4B-M2-MAU-N-09", "4B-M2-MAU-N-10", "4B-M2-MAU-S-01", "4B-M2-MAU-S-02", "4B-M2-MAU-S-03", "4B-M2-MAU-S-04", "4B-M2-MAU-S-06", "4B-M2-MAU-S-07",
                    "4B-M2-MAU-S-08", "4B-M2-MAU-S-09", "4B-M2-MAU-S-10", "4B-M2-MAU-S-11", "4B-M2-MAU-SL-N-01", "4B-M2-MAU-SL-N-02", "4B-M2-MAU-SL-N-03"));

    /**
     * RCU系统类型
     */
    public static final Map<String,List<String>> rcuSystemListMap = MapUtils.newMap(
            "RCU", Lists.newArrayList("RCU"),
            "4A", Lists.newArrayList("4A-L1-PHT-RCU-01", "4A-L1-PHT-RCU-02", "4A-L1-PHT-RCU-03", "4A-L1-PHT-RCU-04", "4A-L1-PHT-RCU-05", "4A-L1-PHT-RCU-06", "4A-L1-PHT-RCU-07",
                    "4A-L1-PHT-RCU-08", "4A-L1-PHT-RCU-09", "4A-L1-PHT-RCU-10", "4A-L1-PHT-RCU-11", "4A-L1-PHT-RCU-12", "4A-L1-PHT-RCU-13", "4A-L1-PHT-RCU-B1", "4A-L1-PHT-RCU-B2",
                    "4A-L3-PHT-RCU-04", "4A-L3-PHT-RCU-05", "4A-L3-PHT-RCU-06", "4A-L3-PHT-RCU-07", "4A-L3-PHT-RCU-08", "4A-L3-PHT-RCU-09", "4A-L3-PHT-RCU-10", "4A-L3-PHT-RCU-11",
                    "4A-L3-PHT-RCU-12", "4A-L3-PHT-RCU-B1", "4A-L3-PHT-RCU-B2"),
            "4B", Lists.newArrayList("4B-1-RCU-PHT-01", "4B-1-RCU-PHT-02", "4B-1-RCU-PHT-03", "4B-1-RCU-PHT-04", "4B-1-RCU-PHT-05", "4B-1-RCU-PHT-06", "4B-1-RCU-PHT-08",
                    "4B-1-RCU-PHT-10", "4B-1-RCU-PHT-12", "4B-1-RCU-PHT-13", "4B-1-RCU-UV2A-01", "4B-1-RCU-UV2A-02", "4B-1-RCU-UV2A-03", "4B-3-RCU-PHT-01", "4B-3-RCU-PHT-02",
                    "4B-3-RCU-PHT-03", "4B-3-RCU-PHT-04", "4B-3-RCU-PHT-05", "4B-3-RCU-PHT-06", "4B-3-RCU-PHT-07", "4B-3-RCU-UV2A-01", "4B-3-RCU-UV2A-02","4B-3-RCU-UV2A-03"));

    /**
     * 电的统计时间类型小时hour天day月month
     */
    public static final List<String> systemTypeList = Lists.newArrayList("4A", "4B");

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
    public static final List<String> electricityPlaceTypeList = Lists.newArrayList("4A","4B","其它");


    /**
     * 排气系统类型
     */
    public static final List<String> ExhaustTypeList = Lists.newArrayList("F-101A", "F-101B","F-101C");



//    public static final List<String> productList = new ArrayList<>();
//    public static Map<String,List<String>> productMap = new HashMap<>();
//    static {
//        productMap.put("50", Lists.newArrayList("C41A","D41A","A1CC495PU1L01","D0NL495PU1L01"));
//        productMap.put("58", Lists.newArrayList("C51A","D51A","D52A","D53A","A1CC575PU1L01","A1CC575PU3L01"));
//        productList.addAll(productMap.get("50"));
//        productList.addAll(productMap.get("58"));
//    }

}
