package com.toastedbits.leetcode.linkedlists;

import java.util.HashSet;
import java.util.Set;

public class LinkedListCycleTwo {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode detectCycle(ListNode head) {
        Set<ListNode> visited = new HashSet<>();
        ListNode cur = head;

        while(cur != null) {
            if(visited.contains(cur)) {
                return cur;
            }
            visited.add(cur);
            cur = cur.next;
        }

        return null;
    }

    public void test1() {
        ListNode one = new ListNode(3);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(0);
        ListNode four = new ListNode(-4);
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = two;
        System.out.println(detectCycle(one).val);
    }

    public static void main(String[] args) {
        LinkedListCycleTwo solver = new LinkedListCycleTwo();
        solver.test1();
    }
}
