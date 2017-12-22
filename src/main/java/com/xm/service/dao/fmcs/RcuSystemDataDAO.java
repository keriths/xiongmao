package com.xm.service.dao.fmcs;

import com.xm.service.apiimpl.pc.fmcs.rcu.dto.RcuSystemData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangshuna on 2017/12/22.
 */
@Repository("rcuSystemDataDAO")
public interface RcuSystemDataDAO {
    List<RcuSystemData> queryRCUSystemData(@Param("systemType") String systemType);
}
