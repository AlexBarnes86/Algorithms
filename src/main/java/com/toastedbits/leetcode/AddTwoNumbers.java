package com.toastedbits.leetcode;

import java.math.BigInteger;

public class AddTwoNumbers {
    // Definition for singly-linked list.
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    private BigInteger listToBigInt(ListNode node) {
        StringBuilder sb = new StringBuilder();
        while(node != null) {
            sb.append(node.val);
            node = node.next;
        }
        return new BigInteger(sb.reverse().toString());
    }

    private ListNode bigIntToList(BigInteger num) {
        ListNode node = null;
        for(char ch : num.toString().toCharArray()) {
            node = new ListNode(ch-'0', node);
        }
        return node;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return bigIntToList(listToBigInt(l1).add(listToBigInt(l2)));
    }

    private void printList(ListNode list) {
        while(list != null) {
            System.out.print(list.val);
            list = list.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        AddTwoNumbers atn = new AddTwoNumbers();
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3, null)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4, null)));

        atn.printList(l1);
        atn.printList(l2);

        System.out.println(atn.listToBigInt(l1));
        System.out.println(atn.listToBigInt(l2));
        System.out.println(atn.listToBigInt(l1).add(atn.listToBigInt(l2)));

        ListNode ans = atn.addTwoNumbers(l1, l2);

        atn.printList(atn.bigIntToList(new BigInteger("1234")));
        atn.printList(ans);
    }
}
