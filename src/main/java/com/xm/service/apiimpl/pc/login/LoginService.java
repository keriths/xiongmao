package com.xm.service.apiimpl.pc.login;

import com.xm.platform.util.Md5Utils;
import com.xm.service.apiimpl.pc.login.dto.UserDTO;
import com.xm.service.dao.login.UserBaseInfoDAO;
import org.springframework.stereotype.Service;


@Service("loginService")
public class LoginService {
    private UserBaseInfoDAO userBaseInfoDAO;

    /**
     * 登录，登录成功返回token
     * @param name
     * @param password
     * @return
     */
    public String doLogin(String name,String password){
        UserDTO userDTO = userBaseInfoDAO.queryUserDTO(name,password);
        if (userDTO==null){
            return null;
        }
        String token = Md5Utils.encodeString(name+"_afhakfha;fja;fjioa_"+System.currentTimeMillis());
        int updateNum = userBaseInfoDAO.updateLoginToken(userDTO.getUserId(),token);
        if (updateNum>0){
            return token;
        }
        return null;
    }

    /**
     * 根据token获取当前登录用户的基本信息，取不到，登录失效或token无效
     * @param token
     * @return
     */
    public UserDTO getLoginUserByToken(String token){
        UserDTO userDTO = userBaseInfoDAO.queryUserDTOByToken(token);
        return userDTO;
    }
}
