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
        final int MAX = 10001;
        ListNode cur = head;
        for(int i = 0; i < MAX; ++i) {
            if(cur == null) {
                return false;
            }
            cur = cur.next;
        }

        return true;
    }
}
