package com.xxx.singleton;

/**
 * 懒汉模式(线程安全) + synchronized
 */
public class Singleton_02 {

    private static Singleton_02 instance;

    private Singleton_02() {
    }

    public static synchronized Singleton_02 getInstance(){
        if (null != instance) return instance;
        return new Singleton_02();
    }

}
