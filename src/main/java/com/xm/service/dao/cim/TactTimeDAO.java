package com.xm.service.dao.cim;

import com.xm.service.apiimpl.pc.cim.tactTime.TactTimeData;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by luokaiming on 17/11/11.
 */
@Repository("tactTimeDAO")
public interface TactTimeDAO {
    /**
     * 设备月底平均值
     * @param factory
     * @param productType
     * @return
     */
    List<TactTimeData> onthlyMean(String factory,String productType);

    /**
     * 产品tact_time值
     * @param factory
     * @param productType
     * @return
     */
    List<TactTimeData> tactTimeQuery(String factory,String productType);
}
