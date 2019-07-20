package com.xm.service.dao.login;

import com.xm.service.apiimpl.pc.target.dto.TargetDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by wangshuna on 2018/2/9.
 */
@Repository("targetDAO")
public interface TargetDAO {

    List<TargetDTO> queryTargetInline();

    Integer updateTargetInline(TargetDTO target);

    Integer addTargetInline(TargetDTO target);

    Integer deleteTargetInline(TargetDTO target);

    List<Map<String,Object>> querySyncData(@Param("offset")int offset, @Param("limit")int limit);
}
