package com.xm.service.dao.fmcs;

import com.xm.service.apiimpl.pc.fmcs.exhaust.dto.ExhaustBData;
import com.xm.service.apiimpl.pc.fmcs.exhaust.dto.SyncExhaustBData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangshuna on 2018/1/2.
 */
@Repository("exhaustBDataDAO")
public interface ExhaustBDataDAO {
    List<ExhaustBData> queryExhaustBData();

    int updateStatusData(SyncExhaustBData exhaustBData);

    int insertStatusData(SyncExhaustBData exhaustBData);

    SyncExhaustBData queryStatusByKey(@Param("key") String key);
}
