package com.xm.service.dao.cim;

import com.xm.service.apiimpl.pc.cim.tactTime.TactTimeData;

import java.util.List;

/**
 * Created by luokaiming on 17/11/11.
 */
public interface TactTimeDAO {
    List<TactTimeData> onthlyMean();

    List<TactTimeData> tactTimeQuery();
}
