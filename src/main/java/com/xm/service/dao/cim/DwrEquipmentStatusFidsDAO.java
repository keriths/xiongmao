package com.xm.service.dao.cim;

import com.xm.service.apiimpl.pc.cim.equipmentstatus.dto.EquipmentDataDto;
import com.xm.service.apiimpl.pc.cim.equipmentstatus.dto.EquipmentStatusData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wangshuna on 2017/12/26.
 */
@Repository("dwrEquipmentStatusFidsDAO")
public interface DwrEquipmentStatusFidsDAO {
    List<EquipmentStatusData> queryStatusData(@Param("factory") String factory);

    int updateStatusData(EquipmentStatusData equipmentStatusData);

    int insertStatusData(EquipmentStatusData equipmentStatusData);

    EquipmentStatusData queryStatusByKey(@Param("factory")String factory,@Param("key") String key);

    List<EquipmentDataDto.EquipmentData> queryStatus(@Param("factoryList") List<String>  factoryLists);
}
