package com.xm.service.dao.login;

import com.xm.service.apiimpl.pc.login.dto.StoreDTO;
import com.xm.service.apiimpl.pc.login.dto.UserDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("storeDAO")
public interface StoreDAO {
    List<StoreDTO> queryStore();

    Integer updataStore(StoreDTO storeDTO);
}
