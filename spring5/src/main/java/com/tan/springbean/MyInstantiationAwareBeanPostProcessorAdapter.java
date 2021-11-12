package com.tan.springbean;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

import java.beans.PropertyDescriptor;

/**
 * @author Tan.cs
 * @version 1.0
 * @description
 * @date 2021/8/10 14:12
 **/
public class MyInstantiationAwareBeanPostProcessorAdapter extends InstantiationAwareBeanPostProcessorAdapter {

    public MyInstantiationAwareBeanPostProcessorAdapter() {
        System.out.println("这是InstantiationAwareBeanPostProcessorAdapter实现类构造器");
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessorAdapter调用postProcessBeforeInstantiation方法");
        return super.postProcessBeforeInstantiation(beanClass, beanName);
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessor调用postProcessAfterInitialization方法");
        return super.postProcessAfterInstantiation(bean, beanName);
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessor调用postProcessPropertyValues方法");
        return super.postProcessPropertyValues(pvs, pds, bean, beanName);
    }
}
