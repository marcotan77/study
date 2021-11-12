package com.example.leetcode;

/**
 * @version 1.0
 * @description:
 * @author: Tcs
 * @date: 2021-03-30 14:55
 **/
public class 合并两个有序数组 {

    public static void main(String[] args) {
        int[] nums1 = {1,2,3,0,0,0};
        int m = 3;
        int[] nums2 = {2,5,6};
        int n = 3;
//        merge(nums1,m,nums2,n);
//        testMerge();
        isPalindrome();
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {

    }

    public static void testMerge(){
        int a = 3,b =4 ;
        int center = (a + b) >> 1;
        System.out.println(center);
    }

    public static void isPalindrome(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("abc");
        stringBuffer.reverse();
        System.out.println(stringBuffer.toString());
    }

}
