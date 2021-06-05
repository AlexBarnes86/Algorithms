package com.toastedbits.leetcode.linkedlists;

public class IntersectionOfTwoLinkedLists {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return val + "";
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

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int sizeA = countNodes(headA);
        int sizeB = countNodes(headB);
        int diff = Math.abs(sizeA-sizeB);
        ListNode larger = headA;
        ListNode smaller = headB;
        if(sizeA < sizeB) {
            larger = headB;
            smaller = headA;
        }
        for(int i = 0; i < diff; ++i) {
            larger = larger.next;
        }
        while(larger != null && larger != smaller) {
            larger = larger.next;
            smaller = smaller.next;
        }

        return larger;
    }

    public void test1() {
        ListNode shared = new ListNode(8, new ListNode(4, new ListNode(5)));
        ListNode listA = new ListNode(4, new ListNode(1, shared));
        ListNode listB = new ListNode(5, new ListNode(6, new ListNode(1, shared)));
        System.out.println(getIntersectionNode(listA, listB));
    }

    public void test2() {
        ListNode shared = new ListNode(2, new ListNode(4));
        ListNode listA = new ListNode(1, new ListNode(9, new ListNode(1, shared)));
        ListNode listB = new ListNode(3, shared);
        System.out.println(getIntersectionNode(listA, listB));
    }

    public void test3() {
        ListNode listA = new ListNode(2, new ListNode(6, new ListNode(4)));
        ListNode listB = new ListNode(1, new ListNode(5));
        System.out.println(getIntersectionNode(listA, listB));
    }

    public void test4() {
        System.out.println(getIntersectionNode(null, null));
    }

    public void test5() {
        ListNode listA = new ListNode(2, new ListNode(6, new ListNode(4)));
        System.out.println(getIntersectionNode(listA, listA));
    }

    public static void main(String[] args) {
        IntersectionOfTwoLinkedLists solver = new IntersectionOfTwoLinkedLists();
        solver.test1();
        solver.test2();
        solver.test3();
        solver.test4();
        solver.test5();
    }
}
