package com.tan;

import java.util.Arrays;

/**
 * @author tanchusheng
 * @date 2024/1/30 10:17
 */
public class 轮转数组 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(rotate(new int[]{1, 2, 3, 4, 5, 6, 7}, 3)));

    }


    public static int[] rotate(int[] nums, int k) {
        int length = nums.length;
        int[] result = new int[length];
        for (int j = 0; j < nums.length; j++) {
            result[(j + k) % length] = nums[j];
        }
        System.arraycopy(result, 0, nums, 0, length);
        return nums;
    }

    private static int removeDuplicates(int[] nums) {

        int qty = 0;
        for (int i = 0; i < nums.length; i++) {
//            nums

        }

        return nums.length;
    };
}
