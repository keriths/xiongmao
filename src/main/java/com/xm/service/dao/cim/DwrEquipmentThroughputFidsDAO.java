package com.xm.service.dao.cim;

import com.xm.service.apiimpl.pc.cim.equipmentstatus.dto.EquipmentThroughputData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by wangshuna on 2017/12/26.
 */
@Repository("dwrEquipmentThroughputFidsDAO")
public interface DwrEquipmentThroughputFidsDAO {
    List<EquipmentThroughputData> queryThroughputData(@Param("factoryList") List<String> factoryList,
                                                      @Param("beginDate") Date beginDate,
                                                      @Param("endDate") Date endDate);
}
