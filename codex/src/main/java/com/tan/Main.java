package com.tan;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /* 初始化链表 1 -> 3 -> 2 -> 5 -> 4 */
        // 初始化各个节点
        LinkedNode<Integer> n0 = new LinkedNode<>(1,
                new LinkedNode<>(3, new LinkedNode<>(2, new LinkedNode<>(5, new LinkedNode<>(4, null))))
        );

        List<String> strings = Arrays.asList("a","b");

        System.out.println(JSON.toJSONString(strings));

    }
}