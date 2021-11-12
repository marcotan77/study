package com.t.jvm.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tan.cs
 * @version 1.0
 * @description 使用mat工具
 * @date 2021/6/18 15:13
 **/
public class Demo_5 {
    public static void main(String[] args) throws InterruptedException {
        List<Data> data = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            data.add(new Data());
        }
        Thread.sleep(1 * 60 *60 * 1000);
    }
    static class Data{}
}
