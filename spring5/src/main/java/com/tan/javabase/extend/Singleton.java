package com.tan.javabase.extend;

/**
 * @author Tan.cs
 * @version 1.0
 * @description
 * @date 2021/8/11 16:43
 **/
public class Singleton {

    // 指向自己实例的私有静态引用，主动创建
    private static Singleton singleton = new Singleton();

    // 私有的构造方法
    private Singleton(){}

    // 以自己实例为返回值的静态的公有方法，静态工厂方法
    public static Singleton getSingleton(){
        return singleton;
    }

}
