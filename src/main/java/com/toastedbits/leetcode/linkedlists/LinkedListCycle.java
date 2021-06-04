package com.toastedbits.leetcode.linkedlists;

import java.util.HashSet;
import java.util.Set;

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
        Set<ListNode> set = new HashSet<>();
        while(cur != null) {
            if(set.contains(cur)) {
                return true;
            }
            set.add(cur);
            cur = cur.next;
        }

        return false;
    }
}
