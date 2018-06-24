package com.xm.service.dao.cim;

import com.xm.service.apiimpl.pc.cim.cycletime.dto.CycleTimeData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by luokaiming on 2017/11/20.
 */
@Repository("dwrProductCtFidsDAO")
public interface DwrProductCtFidsDAO {

    /**
     * Cycle_Time显示
     * @param productId 产品id
     * @param beginDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    List<CycleTimeData.CycleTimeDetailData> cycleTimeShow(@Param("productIdList") List<String> productIdList,
                                                          @Param("dateType")String dateType,
                                                          @Param("beginDate") Date beginDate,
                                                          @Param("endDate") Date endDate,
                                                          @Param("productTypeList")List<String> productTypeList);

    Map<String,Object> loadByPrimaryKey(Map<String, Object> mapData);

    void addData(Map<String, Object> mapData);

    void updateData(Map<String, Object> mapData);

}
