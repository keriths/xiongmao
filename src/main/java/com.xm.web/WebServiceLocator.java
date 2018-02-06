package com.xm.web;

import com.xm.service.apiimpl.pc.login.LoginService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by fanshuai on 18/2/4.
 */
@Component
public class WebServiceLocator implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    private static <T> T getBean(String beanId){
        return (T)applicationContext.getBean(beanId);
    }
    public static LoginService getLoginService(){
        return getBean("loginService");
    }

}
