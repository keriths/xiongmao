package com.xm.service.dao.cim;

import com.xm.service.apiimpl.pc.cim.goodRate.dto.ProductGoodRateRetDTO;
import com.xm.service.apiimpl.pc.cim.goodRate.dto.ProductLineDetailData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wangshuna on 2018/1/18.
 */
@Repository("dwsProductLineYieldFidsDAO")
public interface DwsProductLineYieldFidsDAO {

    List<ProductGoodRateRetDTO.ProductGoodRateDTO> queryProductGoodRateByYieldType(
            @Param("YIELD_TYPE")Integer YIELD_TYPE,
            @Param("productIdList")List<String> productIdList,
            @Param("beginDate")Date beginDate,
            @Param("endDate")Date endDate,
            @Param("dateType") String dateType);
    List<ProductGoodRateRetDTO.ProductGoodRateDTO> queryProductGoodRate(@Param("productIdList")List<String> productIdList,
                                                                        @Param("beginDate")Date beginDate,
                                                                        @Param("endDate")Date endDate,
                                                                        @Param("dateType") String dateType);

    BigDecimal queryProductGoodRateLeastPlanSA(@Param("productIdList")List<String> productIdList,@Param("maxDate") Date maxDate,@Param("dateType") String dateType);


    List<ProductLineDetailData> querySLFactoryGoodRage(
                                                       @Param("dateType")String dateType,
                                                       @Param("beginDate") Date beginDate,
                                                       @Param("endDate") Date endDate);
    List<ProductLineDetailData> queryProductLineData(@Param("factoryList") List<String> factoryList,
                                                                     @Param("dateType")String dateType,
                                                                     @Param("beginDate") Date beginDate,
                                                                     @Param("endDate") Date endDate);

    List<ProductLineDetailData> queryFactoryProductGodRateData(@Param("factoryList") List<String> factoryList,
                                                     @Param("productIdList") List<String> productIdList,
                                                     @Param("dateType")String dateType,
                                                     @Param("beginDate") Date beginDate,
                                                     @Param("endDate") Date endDate);
    Map<String,Object> loadByPrimaryKey(Map<String, Object> mapData);

    void addData(Map<String, Object> mapData);

    void updateData(Map<String, Object> mapData);

    /**
     * 目标良品率
     * @param mapData
     */
    /*Map<String,Object> loadTargetByPrimaryKey(Map<String, Object> mapData);

    void addTargetData(Map<String, Object> mapData);*/

//    void updateTargetData(Map<String, Object> mapData);

    List<ProductLineDetailData> queryTotalProductLineByDateAndFactoryList(
            @Param("factoryList") List<String> factoryList,
            @Param("startDate")Date startDate,
            @Param("endDate")Date endDate,
            @Param("productTypeList")List<String> productTypeList);

    List<ProductLineDetailData> queryFactoryProductGoodRate(
            @Param("factoryList") List<String> factoryList,
            @Param("productIdList") List<String> productIdList,
            @Param("startDate")Date startDate,
            @Param("endDate")Date endDate);
}
