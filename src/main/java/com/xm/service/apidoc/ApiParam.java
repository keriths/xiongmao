package com.xm.service.apidoc;

import java.lang.reflect.Type;

/**
 * Created by fanshuai on 17/10/22.
 */
public class ApiParam {
    private String paramDesc;
    private String paramName;
    private Type paramType;
    private Class paramClass;

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

    public Type getParamType() {
        return paramType;
    }

    public void setParamType(Type paramType) {
        this.paramType = paramType;
    }


    public Class getParamClass() {
        return paramClass;
    }

    public void setParamClass(Class paramClass) {
        this.paramClass = paramClass;
    }

    @Override
    public String toString() {
        return "ApiParam{" +
                "paramDesc='" + paramDesc + '\'' +
                ", paramName='" + paramName + '\'' +
                ", paramType=" + paramType +
                ", paramClass=" + paramClass +
                '}';
    }
}
