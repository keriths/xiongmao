package com.xm.service.dao.login;

import com.xm.service.apiimpl.pc.store.dto.StoreDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("storeDAO")
public interface StoreDAO {
    List<StoreDTO> queryStore();

    Integer updateStore(StoreDTO storeDTO);

    Integer addStore(StoreDTO storeDTO);

    Integer deleteStore(StoreDTO storeDTO);

    List<Map<String,Object>> querySyncData(@Param("offset")int offset, @Param("limit")int limit);
    
}
