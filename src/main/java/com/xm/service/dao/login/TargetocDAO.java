package com.xm.service.dao.login;

import com.xm.service.apiimpl.pc.targetOC.dto.TargetocDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by wanghusna on 2018/2/9.
 */
@Repository("targetocDAO")
public interface TargetocDAO {

    List<TargetocDTO> queryTargetOCInline();

    Integer updateTargetOCInline(TargetocDTO targetoc);

    Integer addTargetOCInline(TargetocDTO targetoc);

    Integer deleteTargetOCInline(TargetocDTO targetoc);

    List<Map<String,Object>> querySyncData(@Param("offset")int offset, @Param("limit")int limit);
}
