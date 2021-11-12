package com.example.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @version 1.0
 * @description: 只出现一次的数字
 * @author: Tcs
 * @date: 2021-03-26 14:580
 **/
public class OnlyOnce {


    public static void main(String[] args) {
        int[] nums = {2,2,1};
        int result = hashsetNumber(nums);
        System.out.println("result:"+result);
    }

    /**
     * 异或
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {
        int num=0;
        for(int i=0;i<nums.length;i++){
            num=num^nums[i];
        }
        return num;
    }

    /**
     * set集合不能重复
     * @param nums
     * @return
     */
    public static int hashsetNumber(int[] nums){
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!set.add(nums[i])) {
                set.remove(nums[i]);
            }
        }
        return set.iterator().next();
    }
}
