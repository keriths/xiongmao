package com.xm.service.dao.cim;

import com.xm.service.apiimpl.pc.cim.tactTime.dto.TactTimeMonthAvgDataDTO;
import com.xm.service.apiimpl.pc.cim.tactTime.dto.TactTimeProductTimeListRetDTO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by fanshuai on 17/11/15.
 */
public interface DwrProductTtFidsDAO {
    List<TactTimeMonthAvgDataDTO> queryMonthAvg(
            @Param("factory")String factory,
            @Param("productIdList")List<String> productIdList,
            @Param("beginDate")Date beginDate,
            @Param("endDate")Date endDate
    );

    List<TactTimeProductTimeListRetDTO.TactTimeProductDetail> queryTactTimeListByProductIdAndTime(
            @Param("factory")String factory,
            @Param("productId")String productId,
            @Param("beginDate")Date beginDate,
            @Param("endDate")Date endDate
    );

    Map<String,Object> loadByPrimaryKey(Map<String, Object> mapData);

    void addData(Map<String, Object> mapData);

    void updateData(Map<String, Object> mapData);
}