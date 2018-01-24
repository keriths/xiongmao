package com.xm.service.dao.cim;

import com.xm.service.apiimpl.pc.cim.goodRate.dto.ProductOcData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by wanghsusna on 2018/1/24.
 */
@Repository("dwsProductOcYieldFidsDAO")
public interface DwsProductOcYieldFidsDAO {

    List<ProductOcData.ProductOcDetailData> queryProductOcData(@Param("factory") String factory,
                                                                 @Param("productId") String productId,
                                                                 @Param("dateType")String dateType,
                                                                 @Param("beginDate") Date beginDate,
                                                                 @Param("endDate") Date endDate);
}
