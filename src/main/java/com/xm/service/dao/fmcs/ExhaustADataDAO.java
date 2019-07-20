package com.xm.service.dao.fmcs;

import com.xm.service.apiimpl.pc.fmcs.exhaust.dto.ExhaustAData;
import com.xm.service.apiimpl.pc.fmcs.exhaust.dto.SyncExhaustAData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by wangshuna on 2018/1/2.
 */
@Repository("exhaustADataDAO")
public interface ExhaustADataDAO {
    List<ExhaustAData> queryExhaustAData(@Param("name") String name);

    SyncExhaustAData loadByPrimaryName(@Param("name") String name);

    void addData(SyncExhaustAData exhaustAData);

    void updateData(SyncExhaustAData exhaustAData);
}
