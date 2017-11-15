package com.xm.platform.processor;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.apidoc.*;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fanshuai on 17/10/22.
 */
@Component
public class ServiceAPIDocumentBeanPostProcessor implements BeanPostProcessor {
//    private boolean isSameMethod(Method method,CtMethod ctMethod){
//        if (!method.getName().equals(ctMethod.getName())){
//            return false;
//        }
//        Class[] parameterTypes = method.getParameterTypes();
//        CtClass[] ctParameterTypes = null;
//        try {
//            ctParameterTypes = ctMethod.getParameterTypes();
//        } catch (NotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        if ((parameterTypes==null || parameterTypes.length==0) && (ctParameterTypes==null || ctParameterTypes.length==0)){
//            return true;
//        }
//        if (parameterTypes!=null && ctParameterTypes!=null && parameterTypes.length==ctParameterTypes.length){
//            for (int i = 0;i<parameterTypes.length;i++){
//                Class parameterType = parameterTypes[i];
//                CtClass ctParameterType = ctParameterTypes[i];
//                if (!parameterType.getName().equals(ctParameterType.getName())){
//                    return false;
//                }
//            }
//            return true;
//        }
//        return false;
//    }
//    private CtMethod getCtMethod(CtClass ctClass,Method method){
//        CtMethod ctMethod = null;
//        try {
//            ctMethod = ctClass.getDeclaredMethod(method.getName());
//        } catch (NotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        if (isSameMethod(method,ctMethod)){
//            return ctMethod;
//        }
//        CtMethod[] ctMethods = ctClass.getMethods();
//        for (CtMethod ctMet : ctMethods){
//            if (isSameMethod(method,ctMet)){
//                return ctMet;
//            }
//        }
//        throw new RuntimeException("not found ctMethod"+method.getName());
//    }
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Map<String, Object> apiServiceMap = beanFactory.getBeansWithAnnotation(ApiServiceDoc.class);
        if (CollectionUtils.isEmpty(apiServiceMap)){
            return;
        }
        for (Map.Entry<String,Object> entry : apiServiceMap.entrySet()){
            String serviceId = entry.getKey();
            Object serviceObj = entry.getValue();
            Class serviceClass = serviceObj.getClass();

            ApiServiceDoc apiServiceDoc = (ApiServiceDoc) serviceClass.getAnnotation(ApiServiceDoc.class);
            String apiServiceDesc = apiServiceDoc.name();
            if (ApiManager.getServiceMethodList(apiServiceDesc)!=null){
                throw new RuntimeException(apiServiceDesc+" cant more then one");
            }

//            CtClass ctClass = getCtClass(serviceClass);

            Method[] methods = serviceClass.getMethods();
            for (Method method : methods){
                ApiMethodDoc apiMethodDoc = method.getAnnotation(ApiMethodDoc.class);
                if (apiMethodDoc==null){
                    continue;
                }
                ApiMethod apiMethod = new ApiMethod();
                apiMethod.setMethod(method);
                apiMethod.setServiceId(serviceId);
                apiMethod.setServiceObj(serviceObj);
                apiMethod.setServiceDesc(apiServiceDesc);
                apiMethod.setApiCode(apiMethodDoc.apiCode());
                apiMethod.setMethodDesc(apiMethodDoc.name());

                ApiMethodResultType apiMethodResultType = getApiMethodResultType(method);
                LinkedHashMap<String, ApiParam> paramMap = getStringApiParamLinkedHashMap(method);
                apiMethod.setParamMap(paramMap);
                apiMethod.setApiMethodResultType(apiMethodResultType);

                ApiManager.addApiMethod(apiMethod);
            }
        }
        System.out.println("\n\n\n\n\n\n\n"+ApiManager.getApiMethodMap());
    }

    private String getTypeFullName(Type type){
        if (type instanceof Class){
            return ((Class) type).getName();
        }else if (type instanceof ParameterizedTypeImpl){
            return ((ParameterizedTypeImpl)type).getRawType().getName();
        }
        throw new RuntimeException(" not found type ");
    }
    private String getTypeSimpleName(Type type){
        if (type instanceof Class){
            return ((Class) type).getSimpleName();
        }else if (type instanceof ParameterizedTypeImpl){
            return ((ParameterizedTypeImpl)type).getRawType().getSimpleName();
        }
        throw new RuntimeException(" not found type "+type);
    }
    private boolean isOwnerType(Type type){
        if (getTypeFullName(type).startsWith("com.xm")){
            return true;
        }
        return false;
    }

    private void typeDetail(Type type,LinkedHashMap<String,TypeFieldDetail> typeFieldDetailLinkedHashMap){
        String typeFullName=getTypeFullName(type);
        String typeSingleName=getTypeSimpleName(type);
        if (typeFieldDetailLinkedHashMap.containsKey(typeFullName)){
            return;
        }
        TypeFieldDetail typeDetail = new TypeFieldDetail();
        typeDetail.setTypeFullName(typeFullName);
        typeDetail.setTypeSingleName(typeSingleName);
        typeFieldDetailLinkedHashMap.put(typeFullName,typeDetail);
        Field[] fields = ((Class) type).getDeclaredFields();
        if (fields==null || fields.length==0){
            return;
        }
        List<FieldDetail> fieldDetailList = new ArrayList<FieldDetail>();
        typeDetail.setFieldDetailList(fieldDetailList);
        for (Field field:fields){
            FieldDetail fieldDetail = new FieldDetail();

            Type genericType = field.getGenericType();

            ApiResultFieldDesc apiResultFieldDesc=field.getAnnotation(ApiResultFieldDesc.class);
            String desc = "";
            if (apiResultFieldDesc!=null){
                desc=apiResultFieldDesc.desc();
            }
            String filedName = field.getName();
            String fieldTypeName = getTypeName(genericType,typeFieldDetailLinkedHashMap);
            fieldDetail.setFieldDesc(desc);
            fieldDetail.setFieldTypeFullName(getTypeFullName(genericType));
            fieldDetail.setFieldTypeSingleName(fieldTypeName);
            fieldDetail.setFieldName(filedName);
            fieldDetailList.add(fieldDetail);
        }
    }



    private ApiMethodResultType getApiMethodResultType(Method method) {
        ApiMethodResultType apiMethodResultType = new ApiMethodResultType();
//        List<Type> ownerTypeList = new ArrayList<Type>();
        Type genericReturnType = method.getGenericReturnType();
        apiMethodResultType.setResultTypeSingleName(getTypeSimpleName(genericReturnType));
        apiMethodResultType.setResultTypeFullName(getTypeFullName(genericReturnType));
        if (isOwnerType(genericReturnType)){
            LinkedHashMap<String,TypeFieldDetail> typeFieldDetailLinkedHashMap = new LinkedHashMap<String, TypeFieldDetail>();
            typeDetail(genericReturnType,typeFieldDetailLinkedHashMap);
            apiMethodResultType.setTypeFieldDetailLinkedHashMap(typeFieldDetailLinkedHashMap);
//            ownerTypeList.add(genericReturnType);
            //自定义DTO
//            Field[] fields = ((Class) genericReturnType).getDeclaredFields();
//            for (Field field:fields){
//                Type genericType = field.getGenericType();
//                if (isOwnerType(genericType) ){
////                    ownerTypeList.add(genericType);
//                }
//                ApiResultFieldDesc apiResultFieldDesc=field.getAnnotation(ApiResultFieldDesc.class);
//                String desc = "";
//                if (apiResultFieldDesc!=null){
//                    desc=apiResultFieldDesc.desc();
//                }
//                String filedName = field.getName();
//                String fieldTypeName = getTypeName(genericType,typeFieldDetailLinkedHashMap);
//                System.out.println(fieldTypeName);
//
//            }
        }else {
            //其它返回
        }
        return apiMethodResultType;
    }

    private String getTypeName(Type type,LinkedHashMap<String,TypeFieldDetail> typeFieldDetailLinkedHashMap){
        if (isOwnerType(type) ){
            typeDetail(type,typeFieldDetailLinkedHashMap);
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (type instanceof ParameterizedType){
            String fieldTypeName = getTypeSimpleName(type);
            stringBuilder.append(fieldTypeName);
            Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
            if (actualTypeArguments==null||actualTypeArguments.length==0){
                return stringBuilder.toString();
            }
            stringBuilder.append("<");
            int index=0;
            for (Type actualType:actualTypeArguments){
                if (index==0){
                    stringBuilder.append(getTypeName(actualType,typeFieldDetailLinkedHashMap));
                }else {
                    stringBuilder.append(",").append(getTypeName(actualType,typeFieldDetailLinkedHashMap));
                }
                index++;
            }
            stringBuilder.append(">");
            return stringBuilder.toString();
        }else {
            String fieldTypeName = getTypeSimpleName(type);
            stringBuilder.append(fieldTypeName);
            return stringBuilder.toString();
        }

    }

//    private CtClass getCtClass(Class serviceClass) {
//        ClassPool pool = ClassPool.getDefault();
//        ClassClassPath classPath = new ClassClassPath(this.getClass());
//        pool.insertClassPath(classPath);
//        CtClass ctClass = null;
//        try {
//            ctClass = pool.getCtClass(serviceClass.getName());
//        } catch (NotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        return ctClass;
//    }

    private LinkedHashMap<String, ApiParam> getStringApiParamLinkedHashMap( Method method) {
        LinkedHashMap<String,ApiParam> paramMap = null;
//        CtMethod ctMethod = getCtMethod(ctClass, method);
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        Type[] parameterTypes = method.getGenericParameterTypes();
        Class[] parameterClasses = method.getParameterTypes();
//        MethodInfo methodInfo = ctMethod.getMethodInfo();
//        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
//        LocalVariableAttribute attribute = (LocalVariableAttribute)codeAttribute.getAttribute(LocalVariableAttribute.tag);
//        int pos = Modifier.isStatic(ctMethod.getModifiers()) ? 0 : 1;

        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] params = u.getParameterNames(method);

        for (int i = 0;i<parameterTypes.length;i++) {
            if (paramMap==null){
                paramMap = new LinkedHashMap<String, ApiParam>();
            }
            Type parameterType = parameterTypes[i];
            Class parameterClass = parameterClasses[i];
//            String paramtypeName = attribute.variableName(i + pos);
            String paramtypeName = params[i];
            ApiParamDoc apiParamDoc = getApiParamDoc(parameterAnnotations[i]);

            ApiParam apiParam = new ApiParam();
            apiParam.setParamDesc(apiParamDoc==null?"":apiParamDoc.desc());
            apiParam.setParamName(paramtypeName);
            apiParam.setParamType(parameterType);
            apiParam.setParamClass(parameterClass);
            paramMap.put(apiParam.getParamName(),apiParam);
//                    apiMethod.addParam(apiParam);
        }
        return paramMap;
    }

    private ApiParamDoc getApiParamDoc(Annotation[] parameterAnnotation) {
        ApiParamDoc apiParamDoc = null;
        if (parameterAnnotation!=null && parameterAnnotation.length>0){
            for (Annotation annotation:parameterAnnotation){
                if (annotation instanceof ApiParamDoc){
                    apiParamDoc = (ApiParamDoc)annotation;
                }
            }
        }
        return apiParamDoc;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Object serviceObj = bean;
        String serviceId = beanName;
        Class serviceClass = serviceObj.getClass();

        ApiServiceDoc apiServiceDoc = (ApiServiceDoc) serviceClass.getAnnotation(ApiServiceDoc.class);
        if (apiServiceDoc==null){
            return bean;
        }
        String apiServiceDesc = apiServiceDoc.name();
        if (ApiManager.getServiceMethodList(apiServiceDesc)!=null){
            throw new RuntimeException(apiServiceDesc+" cant more then one");
        }

//        CtClass ctClass = getCtClass(serviceClass);

        Method[] methods = serviceClass.getMethods();
        for (Method method : methods){
            ApiMethodDoc apiMethodDoc = method.getAnnotation(ApiMethodDoc.class);
            if (apiMethodDoc==null){
                continue;
            }
            ApiMethod apiMethod = new ApiMethod();
            apiMethod.setMethod(method);
            apiMethod.setServiceId(serviceId);
            apiMethod.setServiceObj(serviceObj);
            apiMethod.setServiceDesc(apiServiceDesc);
            apiMethod.setApiCode(apiMethodDoc.apiCode());
            apiMethod.setMethodDesc(apiMethodDoc.name());

            ApiMethodResultType apiMethodResultType = getApiMethodResultType(method);
            LinkedHashMap<String, ApiParam> paramMap = getStringApiParamLinkedHashMap( method);
            apiMethod.setParamMap(paramMap);
            apiMethod.setApiMethodResultType(apiMethodResultType);

            ApiManager.addApiMethod(apiMethod);
        }
        return bean;
    }
}
