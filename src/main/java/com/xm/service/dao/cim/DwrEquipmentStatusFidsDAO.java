package com.xm.service.dao.cim;

import com.xm.service.apiimpl.pc.cim.equipmentstatus.dto.EquipmentStatusData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangshuna on 2017/12/26.
 */
@Repository("dwrEquipmentStatusFidsDAO")
public interface DwrEquipmentStatusFidsDAO {
    List<EquipmentStatusData> queryStatusData(@Param("factory") String factory);
}
