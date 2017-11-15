package com.xm.service.dto;

import com.alibaba.fastjson.JSON;
import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.Md5Util;

import java.io.Serializable;

/**
 * Created by fanshuai on 17/11/15.
 */
public class BaseRetDTO implements Serializable{
    @ApiResultFieldDesc(desc = "失败描述")
    public String errorMsg;
    @ApiResultFieldDesc(desc = "是否成功")
    public Boolean success = true;
    @ApiResultFieldDesc(desc = "数据check")
    public String sign;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
    public String getSign(){
        if (sign!=null){
            return sign;
        }
        if (sign==null){
            sign="a";
            sign=Md5Util.encodeString(JSON.toJSONString(this));
        }
        return sign;
    }

}
