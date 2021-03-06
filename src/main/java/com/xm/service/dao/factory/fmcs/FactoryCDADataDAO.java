package com.xm.service.dao.factory.fmcs;

import com.xm.service.apiimpl.pc.fmcs.cda.dto.SyncCdaData;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangshuna on 2017/12/18.
 */
@Repository("factoryCdaDataDAO")
public interface FactoryCDADataDAO {
    List<SyncCdaData> queryCdaData();

}
