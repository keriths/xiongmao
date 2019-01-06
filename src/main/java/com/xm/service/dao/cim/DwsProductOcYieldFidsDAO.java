package com.xm.service.dao.cim;

import com.xm.service.apiimpl.pc.cim.goodRate.dto.ProductOcData;
import com.xm.service.apiimpl.pc.cim.goodRate.dto.ProductOcDetailData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wanghsusna on 2018/1/24.
 */
@Repository("dwsProductOcYieldFidsDAO")
public interface DwsProductOcYieldFidsDAO {

    List<ProductOcDetailData> queryProductOcData(
                                                                 @Param("productIdList") List<String> productIdList,
                                                                 @Param("dateType")String dateType,
                                                                 @Param("beginDate") Date beginDate,
                                                                 @Param("endDate") Date endDate);
    Map<String,Object> loadByPrimaryKey(Map<String, Object> mapData);

    void addData(Map<String, Object> mapData);

    void updateData(Map<String, Object> mapData);

    /**
     * 单个目标良品率
     * @param
     */
    /*Map<String,Object> loadTargetByPrimaryKey(Map<String, Object> mapData);

    void addTargetData(Map<String, Object> mapData);*/

//    void updateTargetData(Map<String, Object> mapData);

//    List<ProductOcDetailData> queryTotalProductLineOCByDateAndProductList(
//            @Param("startDate")Date startDate,
//            @Param("endDate")Date endDate);

    ProductOcDetailData queryProductsGoodRate(
            @Param("productNameList") List<String> productNameList,
            @Param("startDate")Date startDate,
            @Param("endDate")Date endDate);
}
