package com.tan.springbean;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Tan.cs
 * @version 1.0
 * @description
 * @date 2021/8/10 14:24
 **/
public class BeanLifeCycle {
    public static void main(String[] args) {

        System.out.println("现在开始初始化容器");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        System.out.println("容器初始化成功");
        //得到Person，并使用
        Person person = context.getBean("person",Person.class);
        System.out.println(person);
        System.out.println("现在开始关闭容器！");
        ((ClassPathXmlApplicationContext)context).registerShutdownHook();
        String a = "";
        new StringBuffer("1");
        new StringBuilder("1");
    }
}
