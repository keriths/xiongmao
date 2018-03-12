package com.xm.service.dao.cim;

import com.xm.service.apiimpl.pc.cim.goodRate.dto.ProductLineData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wangshuna on 2018/1/18.
 */
@Repository("dwsProductLineYieldFidsDAO")
public interface DwsProductLineYieldFidsDAO {

    List<ProductLineData.ProductLineDetailData> queryProductLineData(@Param("factoryList") List<String> factoryList,
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

    void updateTargetData(Map<String, Object> mapData);

    List<ProductLineData.ProductLineDetailData> queryTotalProductLineByDateAndFactoryList(
            @Param("factoryList") List<String> factoryList,
            @Param("startDate")Date startDate,
            @Param("endDate")Date endDate);
}
