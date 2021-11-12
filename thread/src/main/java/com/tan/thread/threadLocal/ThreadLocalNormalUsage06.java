package com.tan.thread.threadLocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Tan.cs
 * @version 1.0
 * @description
 * threadLocal 用法二  避免传递参数的麻烦
 * @date 2021/5/24 15:55
 **/
public class ThreadLocalNormalUsage06 {

    public static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        new Service1().process();
    }

}

class Service1{
    public void process(){
        User user = new User("java");
        UserContextHolder.holder.set(user);
        new Service2().process();
    }
}
class Service2{
    public void process(){
        User user = UserContextHolder.holder.get();
        System.out.println("Service2"+user.name);
        new Service3().process();
    }
}
class Service3{
    public void process(){
        User user = UserContextHolder.holder.get();
        System.out.println("Service3"+user.name);
    }
}
class UserContextHolder{
    public static ThreadLocal<User> holder = new ThreadLocal<>();

}

class User {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String name) {
        this.name = name;
    }
}
