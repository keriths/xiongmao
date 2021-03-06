package com.xm.service.dao.fmcs;

import com.xm.service.apiimpl.pc.fmcs.upw.dto.SyncUpwaData;
import com.xm.service.apiimpl.pc.fmcs.upw.dto.UpwaData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by luokaiming on 2017/12/21.
 */
@Repository("upwaDataDAO")
public interface UpwaDataDAO {

    /**
     *设备状态列表查询
     * @return
     */
    List<UpwaData> queryUpwaDataList();

    int updateStatusData(SyncUpwaData upwaData);

    int insertStatusData(SyncUpwaData upwaData);

    SyncUpwaData queryStatusByKey(@Param("key") String key);
}
