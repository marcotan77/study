package com.example.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tan.cs
 * @version 1.0
 * @description 两数之和
 * @date 2021/6/5 10:01
 **/
public class TwoNums{

    public static void main(String[] args) {
//        towNum(new int[]{2,7,11,15},9);
        new Thread(new Threads()).start();
        new Thread(new Threads()).start();
    }

    int num =0;

//    @Override
//    public void run() {
//        for (int i = 0; i < 1000; i++) {
//            num++;
//        }
//    }

    private static int[] towNum(int[] nums, int target){
        int[] result = new int[2];
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])){
                result[0] = i;
                result[1] = map.get(nums[i]);
                return result;
            }
            map.put(target-nums[i],i);
        }
        return result;
    }

 static class Threads implements Runnable{
     int num =0;
     @Override
     public void run() {
        for (int i = 0; i < 1000; i++) {
            num++;
        }
         System.out.println(num);
     }
 }
}
