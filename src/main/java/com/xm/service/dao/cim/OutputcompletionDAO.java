package com.xm.service.dao.cim;

import com.xm.service.apiimpl.pc.cim.Outputcompletion.OutputCompletionData;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by luokaiming on 17/11/11.
 */
@Repository("outputcompletionDAO")
public interface OutputcompletionDAO {
    /**
     * 产出达成率
     * @param factory
     * @return
     */
    List<OutputCompletionData> OutputCompletionRate(String factory);
}
