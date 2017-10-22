package com.xm.service.apidoc;

/**
 * Created by fanshuai on 17/10/22.
 */
public class ApiParam {
    private String paramDesc;
    private String paramName;
    private Class paramType;

    public String getParamDesc() {
        return paramDesc;
    }

    public void setParamDesc(String paramDesc) {
        this.paramDesc = paramDesc;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public Class getParamType() {
        return paramType;
    }

    public void setParamType(Class paramType) {
        this.paramType = paramType;
    }


    @Override
    public String toString() {
        return "ApiParam{" +
                "paramDesc='" + paramDesc + '\'' +
                ", paramName='" + paramName + '\'' +
                ", paramType=" + paramType +
                '}';
    }
}
