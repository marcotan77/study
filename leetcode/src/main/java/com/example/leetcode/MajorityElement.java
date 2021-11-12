package com.example.leetcode;

import java.util.*;

/**
 * @version 1.0
 * @description: 多数元素
 * @author: Tcs
 * @date: 2021-03-27 09:18
 **/
public class MajorityElement {

    public static void main(String[] args) {
        int[] nums = {2,1,1,1,1,2,2,2,7,2};
        System.out.println(moreElement2(nums));
    }

    public static int moreElement(int[] nums){
        Arrays.sort(nums);
        int num = nums[nums.length/2];
        return num;
    }

    public static int moreElement2(int[] nums){
        Set<Integer> set = new HashSet<>();
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(!set.add(nums[i])){
                int count = map.get(nums[i])+1;
                map.put(nums[i],count);
            }else{
                map.put(nums[i],1);
            }
        }
        int num = 0;
        int count = 0;
        for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
            if (entry.getValue() > count){
                count = entry.getValue();
                num = entry.getKey();
            }
        }
        return num;
    }
}
