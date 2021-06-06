package com.toastedbits.leetcode.linkedlists;

public class MergeTwoSortedLists {
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

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) {
            return l2;
        }
        if(l2 == null) {
            return l1;
        }
        ListNode head = l1.val <= l2.val ? l1 : l2;
        ListNode tail = head;
        if(l1.val <= l2.val) {
            l1 = l1.next;
        } else {
            l2 = l2.next;
        }

        while(tail != null) {
            if(l1 == null)  {
                tail.next = l2;
                if(l2 != null) {
                    l2 = l2.next;
                }
            }
            else if(l2 == null) {
                tail.next = l1;
                l1 = l1.next;
            }
            else if(l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            }
            else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
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

    private void test1() {
        ListNode listA = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode listB = new ListNode(1, new ListNode(3, new ListNode(4)));
        printList(mergeTwoLists(listA, listB));
    }

    private void test2() {
        printList(mergeTwoLists(null, null));
    }

    private void test3() {
        printList(mergeTwoLists(null, new ListNode(0)));
    }

    private void test4() {
        printList(mergeTwoLists(new ListNode(0), null));
    }

    private void test5() {
        ListNode listA = new ListNode(1, new ListNode(2, new ListNode(4, new ListNode(9))));
        ListNode listB = new ListNode(1, new ListNode(3));
        printList(mergeTwoLists(listA, listB));
    }

    private void test6() {
        ListNode listA = new ListNode(1, new ListNode(3));
        ListNode listB = new ListNode(1, new ListNode(2, new ListNode(4, new ListNode(9))));
        printList(mergeTwoLists(listA, listB));
    }

    public static void main(String[] args) {
        MergeTwoSortedLists solver = new MergeTwoSortedLists();
        solver.test1();
        solver.test2();
        solver.test3();
        solver.test4();
        solver.test5();
        solver.test6();
    }
}
