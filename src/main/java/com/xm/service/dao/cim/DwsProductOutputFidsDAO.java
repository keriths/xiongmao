package com.xm.service.dao.cim;

import com.xm.service.apiimpl.pc.cim.outputCompletion.dto.OutputCompletionData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by luokaiming on 17/11/11.
 */
@Repository("outputcompletionDAO")
public interface DwsProductOutputFidsDAO {
    /**
     * 产出达成率
     * @return
     */
    List<OutputCompletionData.DataList> OutputCompletionRate(@Param("productId")String productId,
                                                         @Param("dateType")String dateType,
                                                         @Param("beginDate")Date beginDate,
                                                         @Param("endDate")Date endDate);


}
