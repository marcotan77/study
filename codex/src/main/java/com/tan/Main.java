package com.tan;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Main {
    public static void main(String[] args) {
        /* 初始化链表 1 -> 3 -> 2 -> 5 -> 4 */
// 初始化各个节点
        LinkedNode<Integer> n0 = new LinkedNode<>(1,
                new LinkedNode<>(3, new LinkedNode<>(2, new LinkedNode<>(5, new LinkedNode<>(4, null))))
        );

        System.out.println(JSON.toJSONString(n0.value()));

    }
}