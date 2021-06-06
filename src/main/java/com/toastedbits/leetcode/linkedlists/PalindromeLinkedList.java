package com.toastedbits.leetcode.linkedlists;

public class PalindromeLinkedList {
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

    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) {
            return true;
        }

        int size = countNodes(head);
        ListNode cur = head.next;
        ListNode prev = head;
        for(int i = 0; i < size/2 - 1; ++i) {
            prev = cur;
            cur = cur.next;
        }

//        System.out.print("List: ");
//        printList(head);
//        System.out.println("Prev: " + prev.val + ", Cur: " + cur.val);

        prev.next = reverseList(cur);
        ListNode mid = prev.next;
        cur = mid;

//        System.out.print("Head: ");
//        printList(head);
//        System.out.print("Mid: ");
//        printList(mid);

        while(head != mid) {
            if(head.val != cur.val) {
                return false;
            }
            head = head.next;
            cur = cur.next;
        }
        return true;
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
        ListNode list = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(1))));
        System.out.println(isPalindrome(list));
//        System.out.println();
    }

    public void test2() {
        ListNode list = new ListNode(1, new ListNode(2));
        System.out.println(!isPalindrome(list));
//        System.out.println();
    }

    public void test3() {
        ListNode list = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(2, new ListNode(1)))));
        System.out.println(isPalindrome(list));
//        System.out.println();
    }

    public void test4() {
        System.out.println(isPalindrome(null));
//        System.out.println();
    }

    public void test5() {
        System.out.println(isPalindrome(new ListNode(1)));
//        System.out.println();
    }

    public void test6() {
        System.out.println(isPalindrome(new ListNode(1, new ListNode(1))));
//        System.out.println();
    }

    public static void main(String[] args) {
        PalindromeLinkedList solver = new PalindromeLinkedList();
        solver.test1();
        solver.test2();
        solver.test3();
        solver.test4();
        solver.test5();
        solver.test6();
    }
}
