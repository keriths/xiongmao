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
            "ARRAY",Lists.newArrayList("PHOTO","PVD","CVD","WET","DE"),
            "CELL",Lists.newArrayList("PI","FDV","ODF","HSW","KOL"),
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
            "ARRAY", Lists.newArrayList("PHOTO","PVD","CVD","WET","DE"),
            "CELL",Lists.newArrayList("PI","FDV","ODF","HSW","KOL"),
            "CF",Lists.newArrayList("BM","ITO","PS","RGB","RML"),
            "SL-OC",Lists.newArrayList("MBD","POL","OLB","MLB","Aging")
    );

    /**
     * cycleTime厂别
     */
    public static final List<String> cycleTimeFactoryList=Lists.newArrayList("ARRAY","CELL","SL-OC");
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
    public static final List<String> factoryLists = Lists.newArrayList("ARRAY","CELL","CF","SL-OC");
    public static final List<String> showFactoryList = Lists.newArrayList("ARRAY","CELL","CF","SL-OC");
    public static final List<String> allSingleFactoryLists = Lists.newArrayList("ARRAY","CELL","CF","SL","OC");
    public static final Map<String,List<String>> factoryMap = MapUtils.newMap(
            "ARRAY",Lists.newArrayList("ARRAY"),
            "CELL",Lists.newArrayList("CELL"),
            "CF",Lists.newArrayList("CF"),
            "SL-OC",Lists.newArrayList("SL","OC")
    );
    /**
     * 在制品站点
     */
    public static final List<String> stepIdLists = Lists.newArrayList("站点1", "站点2", "站点3", "站点4", "站点5","站点6","站点7");
    /**
     *大宗气气体类型
     */
    public static final List<String> gasNamelist = Lists.newArrayList("GN2","PN2","GO2","PO2","PAr","PHe","PH2");
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

    public static final List<String> wwtbDataCodeList=Lists.newArrayList("PH","F","PO4-P","CODcr","T-N","C1","Cu");

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
            "RCU",Lists.newArrayList("RCU"),
            "4A",Lists.newArrayList("4A-L1-PHT-RCU-01","4A-L1-PHT-RCU-02","4A-L1-PHT-RCU-03","4A-L1-PHT-RCU-04","4A-L1-PHT-RCU-05","4A-L1-PHT-RCU-06","4A-L1-PHT-RCU-07",
                            "4A-L1-PHT-RCU-08","4A-L1-PHT-RCU-09","4A-L1-PHT-RCU-10","4A-L1-PHT-RCU-11","4A-L1-PHT-RCU-12","4A-L1-PHT-RCU-13","4A-L1-PHT-RCU-B1","4A-L1-PHT-RCU-B2",
                            "4A-L3-PHT-RCU-04","4A-L3-PHT-RCU-05","4A-L3-PHT-RCU-06","4A-L3-PHT-RCU-07","4A-L3-PHT-RCU-08","4A-L3-PHT-RCU-09","4A-L3-PHT-RCU-10","4A-L3-PHT-RCU-11",
                            "4A-L3-PHT-RCU-12","4A-L3-PHT-RCU-B1","4A-L3-PHT-RCU-B2"),
            "4B",Lists.newArrayList("4B-1-RCU-PHT-01","4B-1-RCU-PHT-02","4B-1-RCU-PHT-03","4B-1-RCU-PHT-04","4B-1-RCU-PHT-05","4B-1-RCU-PHT-06","4B-1-RCU-PHT-08",
                            "4B-1-RCU-PHT-10","4B-1-RCU-PHT-12","4B-1-RCU-PHT-13","4B-1-RCU-UV2A-01","4B-1-RCU-UV2A-02","4B-1-RCU-UV2A-03","4B-3-RCU-PHT-01","4B-3-RCU-PHT-02",
                            "4B-3-RCU-PHT-03","4B-3-RCU-PHT-04","4B-3-RCU-PHT-05","4B-3-RCU-PHT-06","4B-3-RCU-PHT-07","4B-3-RCU-UV2A-01","4B-3-RCU-UV2A-02","4B-3-RCU-UV2A-03"));

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
    public static final List<String> electricityPlaceTypeList = Lists.newArrayList("4A","4B","其他");


    /**
     * 排气系统类型
     */
    public static final List<String> ExhaustTypeList = Lists.newArrayList("F-101A", "F-101B","F-101C");

    /**
     * 产出数横坐标产品
     */
    public static final Map<String,String> outProductIdNameMap = MapUtils.newMap(
            "50","50",
            "55","55",
            "60","60",
            "65","65");


    public static final Map<String,List<String>> eqpIdMap = MapUtils.newMap(
            "PHOTO",Lists.newArrayList("VDN-01C","PEB-16C","KAM-02C","WIT-02CL","KRV-01C"),
            "PVD",Lists.newArrayList("SPI-01C","VDN-05C-CUL","CAK-16CL","PCD-07C","CAK-07CL"),
            "CVD",Lists.newArrayList("KLD-01CL","PHB-07C","VDN-01CL","KEF-01CL","KGM-01CL"),
            "WET",Lists.newArrayList("KHM-01CL","WIG-01CL","WUK-02C","DEV-07C","SPX-01CL"),
            "DE",Lists.newArrayList("KAR-02CL","XSB-01C2","XOB-03CL","SPI-01CL","KMM-03CL"),
            "PI",Lists.newArrayList("PIS-11C","PMW-11C","CPR-141C","CPL-11CU","PKS-12C"),
            "FDV",Lists.newArrayList("KOL-01C","PRS-11C","MVW-01CL","SSR-13C","CPR-151C"),
            "ODF",Lists.newArrayList("MVK-01C","FDV-11C","MVW-01C","SSR-11C","CHL-01CL"),
            "HSW",Lists.newArrayList("PRK-11C","CPR-121C","CPR-151C","CPR-111C","CFL-11CL"),
            "KOL",Lists.newArrayList("FDV-11C","CPR-141C","PKS-11C","CPR-161C","PKS-11C"),
            "BM",Lists.newArrayList("RML-01C","PDV-03C","FMR-131C","FMR-141C","FRC-112C"),
            "ITO",Lists.newArrayList("FDB-121C","FMC-111C","FRT-112C","MTP-01C","WUK-03CL"),
            "PS",Lists.newArrayList("FRL-01CU","IOV-01C","PTC-03C","FRC-113C","PDV-02C"),
            "RGB",Lists.newArrayList("MTM-01C","FMR-181C","FDL-01CU","FPC-172C","FPC-114C"),
            "RML",Lists.newArrayList("WCP-02C","FDR-111C","FDR-141C","FMR-121C","PMT-03C"),
            "MBD",Lists.newArrayList("AVL205CN","MLR206CN","MLR207CN","IMD205CT","MBD205CN"),
            "POL",Lists.newArrayList("MLR208CL","MBD205CN","MLR202CN","AVL205CN","IMD403CL"),
            "OLB",Lists.newArrayList("MBD-25CL","MLR204CN","MLR203CN","MLR202CN","MLR207CN"),
            "MLB",Lists.newArrayList("SSL205CN","BGW205CN","MBD-25CL","BGW205CN","ASL205CN"),
            "Aging",Lists.newArrayList("AVL205CN","IMD403CL","IMD205CT","SUD205CN","ABI204CN"));

    //不确定用1还是2
    public static final Map<String,String> eqpIdEqpNameMap1 = MapUtils.newMap(
            "PHOTO", "设备A1", "PVD", "设备A2", "CVD", "设备A3", "WET", "设备A4", "DE", "设备A5",
            "PI", "设备B1", "FDV", "设备B2", "ODF", "设备B3", "HSW", "设备B4", "KOL", "设备B5",
            "BM", "设备C1", "ITO", "设备C2", "PS", "设备C3", "RGB", "设备C4", "RML", "设备C5",
            "MBD", "设备D1", "POL", "设备D2", "OLB", "设备D3", "MLB", "设备D4", "Aqing", "设备D5");

    public static final Map<String,String> eqpIdEqpNameMap2 = MapUtils.newMap(
            "VDN-01C","设备A1","PEB-16C","设备A2","KAM-02C","设备A3","WIT-02CL","设备A4","KRV-01C","设备A5",
            "SPI-01C","设备B1","VDN-05C-CUL","设备B2","CAK-16CL","设备B3","PCD-07C","设备B4","CAK-07CL","设备B5",
            "KLD-01CL","设备C1","PHB-07C","设备C2","VDN-01CL","设备C3","KEF-01CL","设备C4","KGM-01CL","设备C5",
            "KHM-01CL","设备D1","WIG-01CL","设备D2","WUK-02C","设备D3","DEV-07C","设备D4","SPX-01CL","设备D5",
            "KAR-02CL","设备E1","XSB-01C2","设备E2","XOB-03CL","设备E3","SPI-01CL","设备E4","KMM-03CL","设备E5",
            "PIS-11C","设备F1","PMW-11C","设备F22","CPR-141C","设备F3","CPL-11CU","设备F4","PKS-12C","设备F5",
            "KOL-01C","设备G1","PRS-11C","设备G2","MVW-01CL","设备G3","SSR-13C","设备G4","CPR-151C","设备G5",
            "MVK-01C","设备H1","FDV-11C","设备H2","MVW-01C","设备H3","SSR-11C","设备H4","CHL-01CL","设备H4",
            "PRK-11C","设备I1","CPR-121C","设备I2","CPR-151C","设备I3","CPR-111C","设备I4","CFL-11CL","设备I5",
            "FDV-11C","设备J1","CPR-141C","设备J2","PKS-11C","设备J3","CPR-161C","设备J4","PKS-11C","设备J5",
            "RML-01C","设备K1","PDV-03C","设备K2","FMR-131C","设备K3","FMR-141C","设备K4","FRC-112C","设备K5",
            "FDB-121C","设备L1","FMC-111C","设备L2","FRT-112C","设备L3","MTP-01C","设备L4","WUK-03CL","设备L5",
            "FRL-01CU","设备M1","IOV-01C","设备M2","PTC-03C","设备M3","FRC-113C","设备M4","PDV-02C","设备M5",
            "MTM-01C","设备N1","FMR-181C","设备N2","FDL-01CU","设备N3","FPC-172C","设备N4","FPC-114C","设备N5",
            "WCP-02C","设备O1","FDR-111C","设备O2","FDR-141C","设备O3","FMR-121C","设备O4","PMT-03C","设备O5",
            "AVL205CN","设备P1","MLR206CN","设备P2","MLR207CN","设备P3","IMD205CT","设备P4","MBD205CN","设备P5",
            "MLR208CL","设备Q1","MBD205CN","设备Q2","MLR202CN","设备Q3","AVL205CN","设备Q4","IMD403CL","设备Q5",
            "MBD-25CL","设备R1","MLR204CN","设备R2","MLR203CN","设备R3","MLR202CN","设备R4","MLR207CN","设备R5",
            "SSL205CN","设备S1","BGW205CN","设备S2","MBD-25CL","设备S3","BGW205CN","设备S4","ASL205CN","设备S5",
            "AVL205CN","设备T1","IMD403CL","设备T2","IMD205CT","设备T3","SUD205CN","设备T4","ABI204CN","设备T5");

}
