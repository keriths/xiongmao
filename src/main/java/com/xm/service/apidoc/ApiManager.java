package com.xm.service.apidoc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fanshuai on 17/10/22.
 */
public class ApiManager {
    private static Map<String,ApiMethod> apiMethodMap;
    private static Map<String,List<ApiMethod>> apiMethodListMap;

    public static void addApiMethod(ApiMethod apiMethod){
        if (apiMethodMap==null){
            apiMethodMap = new HashMap<String, ApiMethod>();
        }
        if (apiMethodListMap == null){
            apiMethodListMap = new HashMap<String, List<ApiMethod>>();
        }
        if (apiMethodMap.containsKey(apiMethod.getApiCode())){
            throw new RuntimeException("apiCode ["+apiMethod.getApiCode()+"] has used");
        }
        apiMethodMap.put(apiMethod.getApiCode(),apiMethod);
        List<ApiMethod> serviceApiMethodList = apiMethodListMap.get(apiMethod.getServiceDesc());
        if (serviceApiMethodList==null){
            serviceApiMethodList = new ArrayList<ApiMethod>();
            apiMethodListMap.put(apiMethod.getServiceDesc(),serviceApiMethodList);
        }
        serviceApiMethodList.add(apiMethod);
    }

    public static ApiMethod getApiMethod(String apiCode){
        if (apiMethodMap==null){
            return null;
        }
        return apiMethodMap.get(apiCode);
    }

    public static Map<String, ApiMethod> getApiMethodMap() {
        return apiMethodMap;
    }
}
