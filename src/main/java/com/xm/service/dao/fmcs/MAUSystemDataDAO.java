package com.xm.service.dao.fmcs;

import com.xm.service.apiimpl.pc.fmcs.mau.dto.MauSystemData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangshuna on 2017/12/21.
 */
@Repository("mauSystemDataDAO")
public interface MAUSystemDataDAO {
    List<MauSystemData.MauSystemDetailData> queryMAUSystemData(@Param("") String systemType);
}
