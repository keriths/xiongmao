package com.xm.service.dao.factory.fmcs;

import com.xm.service.apiimpl.pc.fmcs.wwt.dto.SyncWwtaData;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by luokaiming on 2017/12/18.
 */
@Repository("factoryWwtaDataDAO")
public interface FactoryWwtaDataDAO {

    /**
     *设备状态列表查询
     * @return
     */
    List<SyncWwtaData> queryWwtaDataList();


}
