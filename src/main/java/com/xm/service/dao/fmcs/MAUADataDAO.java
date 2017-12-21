package com.xm.service.dao.fmcs;

import com.xm.service.apiimpl.pc.fmcs.mau.dto.MauEquipmentData;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangshuna on 2017/12/21.
 */
@Repository("mauEquipmentDataDAO")
public interface MAUADataDAO {
    List<MauEquipmentData> queryMAUEquipmentData();
}
