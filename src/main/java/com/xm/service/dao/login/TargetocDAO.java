package com.xm.service.dao.login;

import com.xm.service.apiimpl.pc.target.dto.TargetDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by wanghusna on 2018/2/9.
 */
@Repository("targetocDAO")
public interface TargetocDAO {

    List<TargetDTO> queryTargetOCInline();

    Integer updateTargetOCInline(TargetDTO targetoc);

    Integer addTargetOCInline(TargetDTO targetoc);

    Integer deleteTargetOCInline(TargetDTO targetoc);

    List<Map<String,Object>> querySyncData(@Param("offset")int offset, @Param("limit")int limit);
}
