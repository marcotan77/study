package com.tan.conllection.predecessor;

import java.util.Hashtable;

/**
 * @author Tan.cs
 * @version 1.0
 * @description
 * @date 2021/6/29 16:44
 **/
public class HashtableDemo {
    public static void main(String[] args) {
        Hashtable<String,String> hashtable = new Hashtable<>();
        hashtable.put("1","java");
        System.out.println(hashtable.get("1"));
    }
}
