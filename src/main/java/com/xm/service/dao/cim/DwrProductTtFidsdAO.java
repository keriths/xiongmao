package com.xm.service.dao.cim;

import com.xm.service.apiimpl.pc.cim.tactTime.TactTimeMonthAvgDataDTO;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by fanshuai on 17/11/15.
 */
public interface DwrProductTtFidsDAO {
    List<TactTimeMonthAvgDataDTO> queryMonthAvg(@Param("factory")String factory,@Param("productIdList")List<String> productIdList,@Param("beginDate")Date beginDate,@Param("endDate")Date endDate);
}
