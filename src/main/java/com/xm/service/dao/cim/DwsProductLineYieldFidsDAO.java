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

    List<ProductLineData.ProductLineDetailData> queryProductLineData(@Param("factory") String factory,
                                                                     @Param("productId") String productId,
                                                                     @Param("dateType")String dateType,
                                                                     @Param("beginDate") Date beginDate,
                                                                     @Param("endDate") Date endDate);
    Map<String,Object> loadByPrimaryKey(Map<String, Object> mapData);

    void addData(Map<String, Object> mapData);

    void updateData(Map<String, Object> mapData);
}
