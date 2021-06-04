package com.toastedbits.leetcode.linkedlists;

public class LinkedListCycle {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public boolean hasCycle(ListNode head) {
        ListNode cur = head;
        ListNode prev;
        ListNode dummy = new ListNode(Integer.MIN_VALUE);

        while(cur != null) {
            if(cur == dummy) {
                return true;
            }
            prev = cur;
            cur = cur.next;
            prev.next = dummy;
        }

        return false;
    }
}
