package com.example.leetcode.demo;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author Tan.cs
 * @version 1.0
 * @description
 * @date 2021/6/28 10:51
 **/
public class Demo_01 {

    public static void main(String[] args) {
        Demo_01 demo01 = new Demo_01();
        demo01.partition("aab");
    }

    public List<List<String>> partition(String s) {
        int len = s.length();
        List<List<String>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        Deque<String> stack = new ArrayDeque<>();
        char[] charArray = s.toCharArray();
        dfs(charArray, 0, len, stack, res);
        return res;

    }

    private void dfs(char[] charArray, int index, int len, Deque<String> stack, List<List<String>> res) {
        if (index == len) {
            res.add(new ArrayList<>(stack));
            return;
        }

        for (int i = index; i < len; i++) {
            if (!checkPalindrome(charArray, index, i)) {
                continue;
            }
            dfs(charArray, i + 1, len, stack, res);
            stack.removeLast();
        }
    }

    private boolean checkPalindrome(char[] charArray, int left, int right) {
        while (left < right){
            if (charArray[left] != charArray[right]){
                return false;
            }
            left++;
            right--;
        }
        return true;

    }


}
