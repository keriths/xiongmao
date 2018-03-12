package com.xm.service.dao.cim;

import com.xm.service.apiimpl.pc.cim.goodRate.dto.ProductOcData;
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

    List<ProductOcData.ProductOcDetailData> queryProductOcData(
                                                                 @Param("productId") String productId,
                                                                 @Param("dateType")String dateType,
                                                                 @Param("beginDate") Date beginDate,
                                                                 @Param("endDate") Date endDate);
    Map<String,Object> loadByPrimaryKey(Map<String, Object> mapData);

    void addData(Map<String, Object> mapData);

    void updateData(Map<String, Object> mapData);

    /**
     * 单个目标良品率
     * @param mapData
     */
    /*Map<String,Object> loadTargetByPrimaryKey(Map<String, Object> mapData);

    void addTargetData(Map<String, Object> mapData);*/

    void updateTargetData(Map<String, Object> mapData);

    List<ProductOcData.ProductOcDetailData> queryTotalProductLineOCByDateAndProductList(
            @Param("productIdList") List<String> productIdList,
            @Param("startDate")Date startDate,
            @Param("endDate")Date endDate);
}
