package com.xm.service.dao.cim;

import com.xm.service.apiimpl.pc.cim.inputCompletion.dto.InputCompletionRetDTO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by fanshuai on 17/11/17.
 */
public interface DwsProductInputFidsDAO {
    List<InputCompletionRetDTO.InputCompletionData> queryInputInfo(
            @Param("productId")String productId,
            @Param("dateType")String dateType,
            @Param("beginDate")Date beginDate,
            @Param("endDate")Date endDate
            );
}
