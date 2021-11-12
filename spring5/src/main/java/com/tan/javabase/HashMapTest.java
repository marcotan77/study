package com.tan.javabase;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author Tan.cs
 * @version 1.0
 * @description
 * @date 2021/8/16 16:21
 **/
public class HashMapTest {

    public static void main(String[] args) {
        removeBymap();
    }

    public static void removeByIterator() {//正确的删除方式
        Map<String, Object> map = new HashMap<>();
        map.put("1", "one");
        map.put("2", "two");
        map.put("3", "three");
        System.out.println(map);
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry next = (Map.Entry) it.next();
            if ("2".equals(next.getKey()))
                it.remove();//使用迭代器的remove()方法删除元素
        }
        System.out.println(map);
    }



    public static void removeBymap() {//错误的删除方式
        Map<String, Object> map = new HashMap<>();
        map.put("1", "one");
        map.put("2", "two");
        map.put("3", "three");
        System.out.println(map);
        Set<Map.Entry<String, Object>> entries = map.entrySet();
        for (Map.Entry entry : entries) {
            if ("2".equals(entry.getKey())) {
                map.remove(entry.getKey());//ConcurrentModificationException
            }
        }
        System.out.println(map);
    }

}


