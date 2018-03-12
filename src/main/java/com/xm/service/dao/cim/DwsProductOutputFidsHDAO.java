package com.xm.service.dao.cim;

import com.xm.service.apiimpl.pc.cim.equipmentstatus.dto.EquipmentThroughputData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wangshuna on 2017/12/26.
 */
@Repository("dwrProductOutputFidsHDAO")
public interface DwsProductOutputFidsHDAO {
    List<EquipmentThroughputData> queryThroughputData(@Param("factoryList") List<String> factoryList,
                                                      @Param("beginDate") Date beginDate,
                                                      @Param("endDate") Date endDate,
                                                      @Param("productTypeList")List<String> productTypeList);



    Map<String,Object> loadByPrimaryKey(Map<String, Object> mapData);

    void addData(Map<String, Object> mapData);

    void updateData(Map<String, Object> mapData);
}
