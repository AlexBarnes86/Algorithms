package com.toastedbits.leetcode.linkedlists;

class ReverseLinkedList {
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

    public ListNode reverseList(ListNode head) {
        ListNode cur = head;
        if(cur == null) {
            return null;
        }
        while(cur.next != null) {
            ListNode temp = cur.next.next;
            cur.next.next = head;
            head = cur.next;
            cur.next = temp;
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
        printList(reverseList(list));
    }

    public void test2() {
        ListNode list = new ListNode(1, new ListNode(2));
        printList(reverseList(list));
    }

    public void test3() {
        ListNode list = new ListNode(1);
        printList(reverseList(list));
    }

    public void test4() {
        printList(reverseList(null));
    }

    public static void main(String[] args) {
        ReverseLinkedList solver = new ReverseLinkedList();
        solver.test1();
        solver.test2();
        solver.test3();
        solver.test4();
    }
}