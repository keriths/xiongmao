package com.xm.service.dao.factory.fmcs;

import com.xm.service.apiimpl.pc.fmcs.upw.dto.SyncUpwaData;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by luokaiming on 2017/12/21.
 */
@Repository("factoryUpwaDataDAO")
public interface FactoryUpwaDataDAO {

    /**
     *设备状态列表查询
     * @return
     */
    List<SyncUpwaData> queryUpwaDataList();
}
