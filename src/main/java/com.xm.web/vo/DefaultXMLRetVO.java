package com.xm.web.vo;

import javax.xml.bind.annotation.*;

/**
 * Created by fanshuai on 17/10/22.
 */
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlRootElement(name = "data")
public class DefaultXMLRetVO implements XMLRetVO {
    private int code;
    private String errorMsg;

    @XmlElement(name = "errorMsg")
    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    @XmlElement(name = "code")
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
