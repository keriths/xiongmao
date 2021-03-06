package com.xm.service.dao.cim;

import com.xm.service.apiimpl.pc.cim.tactTime.dto.TactTimeMonthAvgDataDTO;
import com.xm.service.apiimpl.pc.cim.tactTime.dto.TactTimeProductTimeListRetDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by fanshuai on 17/11/15.
 */
@Repository("dwrProductTtFidsDAO")
public interface DwrProductTtFidsDAO {
    List<TactTimeMonthAvgDataDTO> queryMonthAvg(
            @Param("factoryList") List<String> factoryList,
            @Param("productIdList")List<String> productIdList,
            @Param("beginDate")Date beginDate,
            @Param("endDate")Date endDate,
            @Param("productTypeList")List<String> productTypeList
    );

    List<TactTimeProductTimeListRetDTO.TactTimeProductDetail> queryTactTimeListByProductIdAndTime(
            @Param("factoryList") List<String> factoryList,
            @Param("productId")String productId,
            @Param("beginDate")Date beginDate,
            @Param("endDate")Date endDate,
            @Param("productTypeList")List<String> productTypeList
    );

    Map<String,Object> loadByPrimaryKey(Map<String, Object> mapData);

    void addData(Map<String, Object> mapData);

    void updateData(Map<String, Object> mapData);
}