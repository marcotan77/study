package com.example.leetcode;

/**
 * 验证字符串是否为回文
 */
public class isPalindrome {


    public static void main(String[] args) {
        isPalindromes("A man, a plan, a canal: Panama");
    }

    public static void isPalindromes(String s) {
        String actual = s.replaceAll("[^A-Za-z0-9]","").toLowerCase();
        String rev = new StringBuffer(actual).reverse().toString();

        if (actual.equals(rev)){
            System.out.println(true);
        }else{
            System.out.println(false);
        }
    }

}
