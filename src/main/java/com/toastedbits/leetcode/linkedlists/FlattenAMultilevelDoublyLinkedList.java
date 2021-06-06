package com.toastedbits.leetcode.linkedlists;

import java.util.UUID;

public class FlattenAMultilevelDoublyLinkedList {
    class Node {
        public String id;
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node(int val) {
            this.val = val;
            this.id = UUID.randomUUID().toString();
        }

        public Node(int val, String id) {
            this.val = val;
            this.id = id;
        }

        @Override
        public String toString() {
            return String.format("{id: %s, val: %d, prev: %s, next: %s, child: %s}",
                    id, val,
                    prev != null ? prev.id + " (" + prev.val + ")" : null,
                    next != null ? next.id + " (" + next.val + ")": null,
                    child != null ? child.id + " (" + child.val + ")": null
            );
        }
    };

    public Node flatten(Node head) {
        if(head != null) {
            Node after = head.next;
            Node next = flatten(head.child);
            if(next != null) {
                head.next = next;
                next.prev = head;
                while(next.next != null) {
                    next = next.next;
                }
                if(after != null) {
                    after.prev = next;
                }
                next.next = after;
            }
            head.child = null;
            flatten(after);
        }
        return head;
    }

    public void test1() {
        Node l1n1 = new Node(1);
        Node l1n2 = new Node(2);
        Node l2n1 = new Node(3);

        l1n1.next = l1n2;
        l1n1.child = l2n1;

        l2n1.prev = l1n1;

        flatten(l1n1);

        System.out.println(l1n1);
        System.out.println(l1n2);
        System.out.println(l2n1);
    }

    public void test2() {
        System.out.println(flatten(null));
    }

    public void test3() {
        Node l1n1 = new Node(1, "l1n1");
        Node l1n2 = new Node(2, "l1n2");
        Node l1n3 = new Node(3, "l1n3");
        Node l1n4 = new Node(4, "l1n4");
        Node l1n5 = new Node(5, "l1n5");
        Node l1n6 = new Node(6, "l1n6");

        Node l2n7 = new Node(7, "l2n7");
        Node l2n8 = new Node(8, "l2n8");
        Node l2n9 = new Node(9, "l2n9");
        Node l2n10 = new Node(10, "l2n10");

        Node l3n11 = new Node(11, "l3n11");
        Node l3n12 = new Node(12, "l3n12");

        l1n1.next = l1n2;
        l1n2.next = l1n3;
        l1n3.next = l1n4;
        l1n4.next = l1n5;
        l1n5.next = l1n6;

        l1n2.prev = l1n1;
        l1n3.prev = l1n2;
        l1n4.prev = l1n3;
        l1n5.prev = l1n4;
        l1n6.prev = l1n5;

        l2n7.next = l2n8;
        l2n8.next = l2n9;
        l2n9.next = l2n10;

        l2n8.prev = l2n7;
        l2n9.prev = l2n8;
        l2n10.prev = l2n9;

        l3n11.next = l3n12;

        l3n12.prev = l3n11;

        l1n3.child = l2n7;
        l2n8.child = l3n11;

        flatten(l1n1);

        System.out.println(l1n1);
        System.out.println(l1n2);
        System.out.println(l1n3);
        System.out.println(l1n4);
        System.out.println(l1n5);
        System.out.println(l1n6);
        System.out.println(l2n7);
        System.out.println(l2n8);
        System.out.println(l2n9);
        System.out.println(l2n10);
        System.out.println(l3n11);
        System.out.println(l3n12);
    }

    public void test4() {
        Node l1n1 = new Node(1, "l1n1");
        Node l1n2 = new Node(2, "l1n2");
        Node l1n3 = new Node(3, "l1n3");
        Node l1n4 = new Node(4, "l1n4");
        Node l1n5 = new Node(5, "l1n5");
        Node l1n6 = new Node(6, "l1n6");

        Node l2n7 = new Node(7, "l2n7");
        Node l2n8 = new Node(8, "l2n8");
        Node l2n9 = new Node(9, "l2n9");
        Node l2n10 = new Node(10, "l2n10");

        Node l3n11 = new Node(11, "l3n11");
        Node l3n12 = new Node(12, "l3n12");

        l1n1.next = l1n2;
        l1n2.next = l1n3;
        l1n3.next = l1n4;
        l1n4.next = l1n5;
        l1n5.next = l1n6;

        l1n2.prev = l1n1;
        l1n3.prev = l1n2;
        l1n4.prev = l1n3;
        l1n5.prev = l1n4;
        l1n6.prev = l1n5;

        l2n7.next = l2n8;
        l2n8.next = l2n9;
        l2n9.next = l2n10;

        l2n8.prev = l2n7;
        l2n9.prev = l2n8;
        l2n10.prev = l2n9;

        l3n11.next = l3n12;

        l3n12.prev = l3n11;

        l1n3.child = l2n7;
        l1n4.child = l3n11;

        flatten(l1n1);

        System.out.println(l1n1);
        System.out.println(l1n2);
        System.out.println(l1n3);
        System.out.println(l1n4);
        System.out.println(l1n5);
        System.out.println(l1n6);
        System.out.println(l2n7);
        System.out.println(l2n8);
        System.out.println(l2n9);
        System.out.println(l2n10);
        System.out.println(l3n11);
        System.out.println(l3n12);
    }

    public void test5() {
        Node l1n1 = new Node(1, "l1n1");
        Node l2n2 = new Node(2, "l2n2");
        Node l3n3 = new Node(3, "l3n3");

        l1n1.child = l2n2;
        l2n2.child = l3n3;

        flatten(l1n1);

        System.out.println(l1n1);
        System.out.println(l2n2);
        System.out.println(l3n3);
    }

    public static void main(String[] args) {
        FlattenAMultilevelDoublyLinkedList solver = new FlattenAMultilevelDoublyLinkedList();
//        solver.test1();
//        solver.test2();
//        solver.test3();
//        solver.test4();
        solver.test5();
    }
}
