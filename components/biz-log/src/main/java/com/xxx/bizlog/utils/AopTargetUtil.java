package com.xxx.bizlog.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.SpringProxy;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

/**
 * 获取AOP代理类的目标类
 *
 * @author dngzs
 * @date 2023年05月29日11:32:54
 **/
@Slf4j
public abstract class AopTargetUtil {

    /**
     * 获取目标类
     *
     * @param proxy
     * @return
     */
    public static Object getTarget(Object proxy) {
        try {
            if (!AopUtils.isAopProxy(proxy)) {
                return proxy;
            }
            if (AopUtils.isCglibProxy(proxy)) {
                proxy = getCglibProxyTargetObject(proxy);
            }
            if (AopUtils.isJdkDynamicProxy(proxy)) {
                proxy = getJdkDynamicProxyTargetObject(proxy);
            }
        } catch (Exception e) {
            log.warn("====AopTargetUtil getAOPTarget error e=", e);
        }
        return getTarget(proxy);
    }

    /**
     * 获取CGLIB代理类的目标类
     *
     * @param proxy
     * @return
     * @throws Exception
     */
    private static Object getCglibProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
        h.setAccessible(true);
        Object aopProxy = h.get(proxy);
        Field advised = aopProxy.getClass().getDeclaredField("advised");
        advised.setAccessible(true);
        return ((AdvisedSupport) advised.get(aopProxy)).getTargetSource().getTarget();
    }

    /**
     * 获取Jdk代理类的目标类
     *
     * @param proxy
     * @return
     * @throws Exception
     */
    private static Object getJdkDynamicProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
        h.setAccessible(true);
        AopProxy aopProxy = (AopProxy) h.get(proxy);
        Field advised = aopProxy.getClass().getDeclaredField("advised");
        advised.setAccessible(true);
        return ((AdvisedSupport) advised.get(aopProxy)).getTargetSource().getTarget();
    }



    /**
     * 是否是代理class
     *
     * @param clazz
     */
    public static boolean isProxyClass(@Nullable Class clazz) {
        return isCglibProxy(clazz) || isJdkDynamicProxy(clazz);
    }


    /**
     * 是cglib代理的class
     *
     * @param clazz
     */
    public static boolean isCglibProxy(@Nullable Class clazz) {
        return clazz.getName().contains(ClassUtils.CGLIB_CLASS_SEPARATOR);
    }


    /**
     * Check whether the given object is a JDK dynamic proxy.
     * <p>This method goes beyond the implementation of
     * {@link Proxy#isProxyClass(Class)} by additionally checking if the
     * given object is an instance of {@link SpringProxy}.
     * @see Proxy#isProxyClass
     */
    public static boolean isJdkDynamicProxy(@Nullable Class clazz) {
        return Proxy.isProxyClass(clazz);
    }
}
