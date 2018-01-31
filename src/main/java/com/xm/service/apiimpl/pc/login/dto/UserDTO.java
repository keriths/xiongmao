package com.xm.service.apiimpl.pc.login.dto;

import java.io.Serializable;

public class UserDTO implements Serializable{
    private Integer userId;
    private String realName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
