package com.t.jvm.demo;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author Tan.cs
 * @version 1.0
 * @description Metaspace区域内存溢出的时候，应该如何解决？
 * @date 2021/6/18 15:13
 **/
public class Demo_8 {
    /**
     * -XX:+UseParNewGC
     * -XX:+UseConcMarkSweepGC
     * -XX:MetaspaceSize=10m
     * -XX:MaxMetaspaceSize=10m
     * -XX:+PrintGCDetails
     * -Xloggc:gc.log
     * -XX:+HeapDumpOnOutOfMemoryError
     * -XX:HeapDumpPath=./
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        long count = 0;
        while (true) {
            System.out.println("目前创建了" + (++count) + "个car类的子类");
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(Car.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    if (method.getName().equals("run")) {
                        System.out.println("启动汽车之间，先进行自动的检查");
                        return methodProxy.invokeSuper(o, objects);
                    }
                    return methodProxy.invokeSuper(o, objects);
                }
            });
            Car cars = (Car) enhancer.create();
            cars.run();
        }


    }

    static class Car {
        public void run() {System.out.println("汽车启动，开始行使");
        }

    }

    static class SafeCar extends Car{
        @Override
        public void run() {
            System.out.println("汽车启动，开始行使");
            super.run();
        }
    }
}
