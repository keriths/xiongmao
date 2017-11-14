package com.xm.service.dao.cim;

import com.xm.service.apiimpl.pc.cim.Outputcompletion.OutputCompletionData;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by luokaiming on 17/11/11.
 */
@Repository("outputcompletionDAO")
public interface OutputcompletionDAO {
    /**
     * 产出达成率
     * @param product
     * @param dateType
     * @return
     */
    List<OutputCompletionData> OutputCompletionRate(Map paramMap);
}
