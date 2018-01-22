package com.xm.service.dao.fmcs;

import com.xm.service.apiimpl.pc.fmcs.pcw.dto.PcwEquipmentData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangshuna on 2017/12/21.
 */
@Repository("pcwDataDAO")
public interface PCWDataDAO {
    List<PcwEquipmentData> queryPCWData();

    int updateStatusData(PcwEquipmentData equipmentStatusData);

    int insertStatusData(PcwEquipmentData equipmentStatusData);

    PcwEquipmentData queryStatusByKey(@Param("key") String key);
}

