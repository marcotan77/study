package com.tan.spring_Autowried.pojo;

import java.util.*;

/**
 * @program: code
 * @description
 * @author: Tan.
 * @create: 2020-10-22 14:40
 **/
public class Cat {

    public static void main(String[] args) {
        List<Object> list = new ArrayList<>(1);
        list.add(1);
        list.add(2);
        for (int i = 0; ; i++) {
            list.add(i);
            System.out.println(list.toArray());
        }
    }



}
