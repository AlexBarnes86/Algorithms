package com.toastedbits.leetcode.linkedlists;

public class RotateList {
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

        @Override
        public String toString() {
            return "{val: " + val + "}";
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

    public ListNode rotateLeft(final ListNode head, int k) {
        if(head == null || k == 0) {
            return head;
        }

        int size = countNodes(head);
        k %= size;
        if(k == 0) {
            return head;
        }

        ListNode cur = head;
        for(int i = 0 ; i < k-1; ++i) {
            cur = cur.next;
        }
        ListNode newHead = cur.next;
        cur.next = null;
        cur = newHead;
        while(cur.next != null) {
            cur = cur.next;
        }
        cur.next = head;
        return newHead;
    }

    public ListNode rotateRight(final ListNode head, int k) {
        if(head == null || k == 0) {
            return head;
        }

        int size = countNodes(head);
        k = k % size;
        if(k == 0) {
            return head;
        }

        ListNode prev = null;
        ListNode cur = head;
        for(int i = 0 ; i < size - k; ++i) {
            prev = cur;
            cur = cur.next;
        }
        prev.next = null;
        ListNode newHead = cur;
        while(cur.next != null) {
            cur = cur.next;
        }
        cur.next = head;

        return newHead;
    }

    public String listToString(ListNode head) {
        StringBuilder sb = new StringBuilder();
        ListNode cur = head;
        while(cur != null) {
            sb.append(cur);
            if(cur.next != null) {
                sb.append(", ");
            }
            cur = cur.next;
        }
        return sb.toString();
    }

    public void printList(ListNode head) {
        System.out.println(listToString(head));
    }

    public void test1() {
        for(int i = 0; i < 7; ++i) {
            ListNode list = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
            System.out.println(i + ": " + listToString(rotateRight(list, i)));
        }
    }

    public static void main(String[] args) {
        RotateList solver = new RotateList();
        solver.test1();
    }
}
