package com.xm.service.dao.factory.fmcs;

import com.xm.service.apiimpl.pc.fmcs.exhaust.dto.SyncExhaustAData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by luokaiming on 2017/12/18.
 */
@Repository("factoryExhaustADataDAO")
public interface FactoryExhaustADataDAO {

    List<SyncExhaustAData> querySyncData();
}
