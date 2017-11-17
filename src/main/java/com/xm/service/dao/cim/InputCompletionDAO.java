package com.xm.service.dao.cim;

import com.xm.service.apiimpl.pc.cim.inputCompletion.dto.InputCompletionData;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by wangshuna on 2017/11/14.
 */
@Repository("inputcompletionDAO")
public interface InputCompletionDAO {

    /**
     * 投入达成率
     * @param product
     * @param dateType
     * @return
     */
    List<InputCompletionData> InputCompletionRate(Map paramMap);

}
