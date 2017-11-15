package com.xm.service.dao.cim;

import com.xm.service.apiimpl.pc.cim.Outputcompletion.OutputCompletionData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by luokaiming on 17/11/11.
 */
@Repository("outputcompletionDAO")
public interface OutputcompletionDAO {
    /**
     * 产出达成率
     * @return
     */
    List<OutputCompletionData> OutputCompletionRate(Map paramMap);
}
