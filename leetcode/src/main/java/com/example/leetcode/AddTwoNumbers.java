package com.example.leetcode;

/**
 * @author Tan.cs
 * @version 1.0
 * @description
 * @date 2021/6/5 10:19
 **/
public class AddTwoNumbers {

    public static void main(String[] args) {

    }

    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = new ListNode(2,new ListNode(4,new ListNode(3,new ListNode())));
        l2 = new ListNode(5,new ListNode(6,new ListNode(4,new ListNode())));
        return l1;

    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
