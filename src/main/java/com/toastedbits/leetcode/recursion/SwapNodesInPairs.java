package com.toastedbits.leetcode.recursion;

public class SwapNodesInPairs {
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

    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode nextNext = swapPairs(head.next.next);
        ListNode next = head.next;
        next.next = head;
        head.next = nextNext;
        return next;
    }

    private void printList(ListNode node) {
        ListNode cur = node;
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
        System.out.println("== Test 1 ==");
        ListNode list = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        printList(list);
        list = swapPairs(list);
        printList(list);
    }

    public void test2() {
        System.out.println("== Test 2 ==");
        ListNode list = swapPairs(null);
        printList(list);
    }

    public void test3() {
        System.out.println("== Test 3 ==");
        ListNode list = new ListNode(1);
        printList(list);
        list = swapPairs(list);
        printList(list);
    }

    public void myTest1() {
        System.out.println("== My Test 1 ==");
        ListNode list = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        printList(list);
        list = swapPairs(list);
        printList(list);
    }

    public static void main(String[] args) {
        SwapNodesInPairs solver = new SwapNodesInPairs();
        solver.test1();
        solver.test2();
        solver.test3();
        solver.myTest1();
    }
}
