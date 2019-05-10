package com.ybwx.spring.mybatis.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class SpringService implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void injectBean(String beanName, BeanDefinition beanDefinition){
        if(applicationContext != null){
            DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
            beanFactory.registerBeanDefinition(beanName, beanDefinition);
        }
    }

    /**
     * 注入单个Bean使用，若需要重复注入同一个Bean，使用上面的方法
     * @param object
     */
    public void injectBean(Object object){
        if(applicationContext != null){
            DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
            char[] nameArray = object.getClass().getSimpleName().toCharArray();
            nameArray[0] += 32;
            beanFactory.registerSingleton(new String(nameArray), object);
        }
    }

    public Object getBean(String beanName){
        if(applicationContext != null){
            return applicationContext.getBean(beanName);
        }
        return null;
    }

    public <T> T getBean(Class<T> clazz){
        if(applicationContext != null){
            return applicationContext.getBean(clazz);
        }
        return null;
    }
}
