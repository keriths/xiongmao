package com.xm.service.dao.login;

import com.xm.service.apiimpl.pc.login.dto.UserDTO;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 建个表，字段包括
 * userId    用户id
 * name      登录帐号
 * password  登录密码
 * token     登录成功的token
 * loginTime 登录时间
 * realName  真实姓名
 */

@Repository("userBaseInfoDAO")
public interface UserBaseInfoDAO {
    /**
     * 根据用户名和密码查询
     * @param name
     * @param password
     * @return
     */

    UserDTO queryUserDTO(@Param("name")String name, @Param("password")String password);

    /**
     * 根据token查询，查询的时候，取登录时间1天内的
     * @param token
     * @return
     */

    UserDTO queryUserDTOByToken(@Param("token")String token);

    /**
     * 更新token和登录时间
     * @param userId
     * @param token
     */

    int updateLoginToken(@Param("userId")Integer userId, @Param("token")String token);
}
