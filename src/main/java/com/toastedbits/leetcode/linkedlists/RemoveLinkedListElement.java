package com.toastedbits.leetcode.linkedlists;

class RemoveLinkedListElement {
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

    public ListNode removeElements(ListNode head, int val) {
        while(head != null && head.val == val) {
            head = head.next;
        }
        if(head == null) {
            return null;
        }
        ListNode prev = head;
        ListNode cur = head.next;
        while(cur != null) {
            if(cur.val == val) {
                prev.next = cur.next;
                cur = cur.next;
            }
            else {
                prev = cur;
                cur = cur.next;
            }
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
        ListNode list = new ListNode(1, new ListNode(2, new ListNode(6, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6)))))));
        printList(removeElements(list, 6));
    }

    public void test2() {
        ListNode list = new ListNode(1, new ListNode(2, new ListNode(6, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6)))))));
        printList(removeElements(list, 1));
    }

    public void test3() {
        ListNode list = new ListNode(1);
        printList(removeElements(list, 1));
    }

    public void test4() {
        printList(removeElements(null, 6));
    }

    public void test5() {
        printList(removeElements(new ListNode(1), 6));
    }

    public static void main(String[] args) {
        RemoveLinkedListElement solver = new RemoveLinkedListElement();
        solver.test1();
        solver.test2();
        solver.test3();
        solver.test4();
        solver.test5();
    }
}