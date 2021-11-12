package com.tan.conllection.predecessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Tan.cs
 * @version 1.0
 * @description  Collections.synchronizedList(new ArrayList<>())
 * @date 2021/6/29 16:47
 **/
public class SynList {
    public static void main(String[] args) {
        List<Integer> objects = Collections.synchronizedList(new ArrayList<>());
        objects.add(5);
    }
}
