package com.tan;

import java.util.List;

/**
 * 基于数组实现 hash 表
 * @author tanchusheng
 * @date 2023/8/17 16:07
 */
public class array_hash_map {

    class Pair {
        public int key;
        public String val;

        public Pair(int key, String val) {
            this.key = key;
            this.val = val;
        }
    }
    /* 基于数组简易实现的哈希表 */
    class ArrayHashMap{
        private List<Pair> buckets;

        // 初始化
        public ArrayHashMap(){
            for (int i = 0; i < 100; i++) {
                buckets.add(null);
            }
        }

        // 哈希函数
        private int hashFunc(int key) {
            int index = key % 100;
            return index;
        }


        public String get(int key) {
            int index = hashFunc(key);
            Pair pair = buckets.get(index);
            if (pair == null) {
                return null;
            }
            return pair.val;
        }

        public void put(int key ,String val) {
            Pair pair = new Pair(key,val);
            int index = hashFunc(key);
            buckets.set(index,pair);
        }





    }


}
