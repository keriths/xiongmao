package com.xm.service;

import com.xm.service.annotations.ApiMethodDoc;
import com.xm.service.annotations.ApiParamDoc;
import com.xm.service.annotations.ApiServiceDoc;
import com.xm.service.apidoc.ApiManager;
import com.xm.service.apidoc.ApiMethod;
import com.xm.service.apidoc.ApiParam;
import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.util.CollectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by fanshuai on 17/10/22.
 */
public class ServiceAPIDocumentBeanFactoryPostProcessor implements BeanFactoryPostProcessor{
    private boolean isSameMethod(Method method,CtMethod ctMethod){
        if (!method.getName().equals(ctMethod.getName())){
            return false;
        }
        Class[] parameterTypes = method.getParameterTypes();
        CtClass[] ctParameterTypes = null;
        try {
            ctParameterTypes = ctMethod.getParameterTypes();
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
        if ((parameterTypes==null || parameterTypes.length==0) && (ctParameterTypes==null || ctParameterTypes.length==0)){
            return true;
        }
        if (parameterTypes!=null && ctParameterTypes!=null && parameterTypes.length==ctParameterTypes.length){
            for (int i = 0;i<parameterTypes.length;i++){
                Class parameterType = parameterTypes[i];
                CtClass ctParameterType = ctParameterTypes[i];
                if (!parameterType.getName().equals(ctParameterType.getName())){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    private CtMethod getCtMethod(CtClass ctClass,Method method){
        CtMethod ctMethod = null;
        try {
            ctMethod = ctClass.getDeclaredMethod(method.getName());
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
        if (isSameMethod(method,ctMethod)){
            return ctMethod;
        }
        CtMethod[] ctMethods = ctClass.getMethods();
        for (CtMethod ctMet : ctMethods){
            if (isSameMethod(method,ctMet)){
                return ctMet;
            }
        }
        throw new RuntimeException("not found ctMethod"+method.getName());
    }
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Map<String, Object> apiServiceMap = beanFactory.getBeansWithAnnotation(ApiServiceDoc.class);
        if (CollectionUtils.isEmpty(apiServiceMap)){
            return;
        }
        for (Map.Entry<String,Object> entry : apiServiceMap.entrySet()){
            String serviceId = entry.getKey();
            Object serviceObj = entry.getValue();
            Class serviceClass = serviceObj.getClass();

            ClassPool pool = ClassPool.getDefault();
            ClassClassPath classPath = new ClassClassPath(this.getClass());
            pool.insertClassPath(classPath);
            CtClass ctClass = null;
            try {
                ctClass = pool.getCtClass(serviceClass.getName());
            } catch (NotFoundException e) {
                throw new RuntimeException(e);
            }

            ApiServiceDoc apiServiceDoc = (ApiServiceDoc) serviceClass.getAnnotation(ApiServiceDoc.class);
            String apiServiceDesc = apiServiceDoc.name()+"["+serviceClass.getName()+"]";
            System.out.print("\nApiService-" + apiServiceDoc.name());
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

                Class<?> returnType = method.getReturnType();
                String name = method.getName();

                CtMethod ctMethod = getCtMethod(ctClass, method);
                Annotation[][] parameterAnnotations = method.getParameterAnnotations();
                Class<?>[] parameterTypes = method.getParameterTypes();
                MethodInfo methodInfo = ctMethod.getMethodInfo();
                CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
                LocalVariableAttribute attribute = (LocalVariableAttribute)codeAttribute.getAttribute(LocalVariableAttribute.tag);
                int pos = Modifier.isStatic(ctMethod.getModifiers()) ? 0 : 1;
                for (int i = 0;i<parameterTypes.length;i++) {
                    Class parameterType = parameterTypes[i];
                    String paramtypeName = attribute.variableName(i + pos);
                    ApiParamDoc apiParamDoc = getApiParamDoc(parameterAnnotations[i]);

                    ApiParam apiParam = new ApiParam();
                    apiParam.setParamDesc(apiParamDoc==null?"":apiParamDoc.desc());
                    apiParam.setParamName(paramtypeName);
                    apiParam.setParamType(parameterType);
                    apiMethod.addParam(apiParam);
                }
                ApiManager.addApiMethod(apiMethod);

            }
        }
        System.out.println("\n\n\n\n\n\n\n"+ApiManager.getApiMethodMap());
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
}
