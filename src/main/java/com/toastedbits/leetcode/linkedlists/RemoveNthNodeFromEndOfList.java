package com.toastedbits.leetcode.linkedlists;

public class RemoveNthNodeFromEndOfList {
    public class ListNode {
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

    private int countNodes(ListNode node) {
        ListNode cur = node;
        int ct = 0;
        while(cur != null) {
            ct++;
            cur = cur.next;
        }
        return ct;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int size = countNodes(head);
        ListNode cur = head;
        ListNode prev = null;
        for(int i = 0; i < size - n; ++i) {
            prev = cur;
            cur = cur.next;
        }
        if(cur == head) {
            head = head.next;
        }
        else if(prev != null && prev.next != null) {
            prev.next = prev.next.next;
        }
        else if(prev != null) {
            prev.next = null;
        }
        return head;
    }

    public void printList(ListNode head) {
        ListNode cur = head;
        while(cur != null) {
            System.out.print(cur.val);
            if(cur.next != null) {
                System.out.print(", ");
            }
            cur = cur.next;
        }
        System.out.println();
    }

    public void test1() {
        ListNode list = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        printList(removeNthFromEnd(list, 2));
    }

    public void test2() {
        ListNode list = new ListNode(1);
        printList(removeNthFromEnd(list, 1));
    }

    public void test3() {
        ListNode list = new ListNode(1, new ListNode(2));
        printList(removeNthFromEnd(list, 1));
    }

    public void test4() {
        ListNode list = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        printList(removeNthFromEnd(list, 5));
    }

    public void test5() {
        ListNode list = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        printList(removeNthFromEnd(list, 1));
    }

    public static void main(String[] args) {
        RemoveNthNodeFromEndOfList solver = new RemoveNthNodeFromEndOfList();
        solver.test1();
        solver.test2();
        solver.test3();
        solver.test4();
        solver.test5();
    }
}
