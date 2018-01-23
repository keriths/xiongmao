package com.xm.service.dao.fmcs;

import com.xm.service.apiimpl.pc.fmcs.pcw.dto.PcwEquipmentData;
import com.xm.service.apiimpl.pc.fmcs.pcw.dto.SyncPcwEquipmentData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangshuna on 2017/12/21.
 */
@Repository("pcwDataDAO")
public interface PCWDataDAO {
    List<PcwEquipmentData> queryPCWData();

    int updateStatusData(SyncPcwEquipmentData equipmentStatusData);

    int insertStatusData(SyncPcwEquipmentData equipmentStatusData);

    SyncPcwEquipmentData queryStatusByKey(@Param("key") String key);
}

