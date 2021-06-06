package com.toastedbits.leetcode.linkedlists;

public class OddEvenLinkedList {
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

    public ListNode oddEvenList(ListNode head) {
        if (head == null) return null;
        ListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
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
        printList(oddEvenList(list));
    }

    public void test2() {
        ListNode list = new ListNode(2, new ListNode(1, new ListNode(3, new ListNode(5, new ListNode(6, new ListNode(4, new ListNode(7)))))));
        printList(oddEvenList(list));
    }

    public void test3() {
        printList(oddEvenList(null));
    }

    public void test4() {
        printList(oddEvenList(new ListNode(1)));
    }

    public void test5() {
        printList(oddEvenList(new ListNode(1, new ListNode(2))));
    }

    public void test6() {
        printList(oddEvenList(new ListNode(1, new ListNode(2, new ListNode(3)))));
    }

    public void test7() {
        ListNode list = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6, new ListNode(7, new ListNode(8))))))));
        printList(oddEvenList(list));
    }

    public static void main(String[] args) {
        OddEvenLinkedList solver = new OddEvenLinkedList();
        solver.test1();
        solver.test2();
        solver.test3();
        solver.test4();
        solver.test5();
        solver.test6();
        solver.test7();
    }
}