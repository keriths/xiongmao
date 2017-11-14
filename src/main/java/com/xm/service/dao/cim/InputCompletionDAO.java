package com.xm.service.dao.cim;

import com.xm.service.apiimpl.pc.cim.Inputcompletion.dto.InputCompletionData;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangshuna on 2017/11/14.
 */
@Repository("inputcompletionDAO")
public interface InputCompletionDAO {

    List<InputCompletionData> InputCompletionRate(String productType);

}
