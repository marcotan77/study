package com.tan.thread;

/**
 * @author Tan.cs
 * @version 1.0
 * @description
 * @date 2021/6/29 16:27
 **/
public class Final {


    public static void main(String[] args) {
        String a = "asd2";
        final String b = "asd";
        String c = "asd";
        String d = b+2;
        String e = c+2;
        System.out.println(a==d);
        System.out.println(a==e);
    }
}
