package com.example.leetcode;

/**
 * @author Tan.cs
 * @version 1.0
 * @description
 * @date 2021/6/8 10:17
 **/
public class ReverseNum {

    public static void main(String[] args) {

        String a = "11111111111\n3333333";
        System.out.println(a);

        reverse(123);
    }

    public static int reverse(int x) {
        int rev = 0;
        while(x != 0){
            if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10){
                return 0;
            }
            int digit = x % 10;
            x /= 10;
            rev = rev * 10 + digit;
        }
        return rev;
    }
}
