package com.xm.service.dao.factory.fmcs;

import com.xm.service.apiimpl.pc.fmcs.exhaust.dto.ExhaustBData;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangshuna on 2018/1/2.
 */
@Repository("factoryExhaustBDataDAO")
public interface FactoryExhaustBDataDAO {
    List<ExhaustBData> queryExhaustBData();
}
