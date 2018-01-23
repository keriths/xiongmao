package com.xm.service.dao.fmcs;

import com.xm.service.apiimpl.pc.fmcs.cda.dto.CdaData;
import com.xm.service.apiimpl.pc.fmcs.cda.dto.SyncCdaData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Created by wangshuna on 2017/12/18.
 */
@Repository("cdaDataDAO")
public interface CDADataDAO {
    List<CdaData> queryCdaData();

    int updateStatusData(SyncCdaData cdaData);

    int insertStatusData(SyncCdaData cdaData);

    SyncCdaData queryStatusByKey(@Param("key") String key);
}
