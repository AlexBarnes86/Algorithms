package com.toastedbits.leetcode.linkedlists;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CopyListWithRandomPointer {
    class Node {
        int val;
        String id;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
            this.id = UUID.randomUUID().toString();
        }

        public Node(int val, String id) {
            this.val = val;
            this.next = null;
            this.random = null;
            this.id = id;
        }
    }

    public String listToString(Node head) {
        StringBuilder sb = new StringBuilder();
        Node cur = head;
        while(cur != null) {
            sb.append(String.format("{id: %s, val: %d, random: %s}", cur.id, cur.val, cur.random == null ? null : cur.random.id));
            if(cur.next != null) {
                sb.append(", ");
            }
            cur = cur.next;
        }
        return sb.toString();
    }

    public Node copyRandomList(Node head) {
        if(head == null) {
            return null;
        }

        //Double up the list entries
        Node cur = head;
        while(cur != null) {
            Node next = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = next;
            cur = next;
        }

        //Redirect random pointers
        cur = head;
        while(cur != null && cur.next != null) {
            cur.next.random = cur.random == null ? null : cur.random.next;
            cur = cur.next.next;
        }

        //Split the two lists
        Node orig = head;
        Node copy = head.next;
        Node copyHead = copy;
        while(orig != null) {
            orig.next = copy.next;
            copy.next = orig.next != null ? orig.next.next : null;
            orig = orig.next;
            copy = copy.next;
        }

        return copyHead;
    }

    public void test1() {
        System.out.println("== Test 1 ==");
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node(7, "1"));
        nodes.add(new Node(13, "2"));
        nodes.add(new Node(11, "3"));
        nodes.add(new Node(10, "4"));
        nodes.add(new Node(1, "5"));

        nodes.get(0).random = null;
        nodes.get(1).random = nodes.get(0);
        nodes.get(2).random = nodes.get(4);
        nodes.get(3).random = nodes.get(2);
        nodes.get(4).random = nodes.get(0);

        for(int i = 0; i < nodes.size()-1; ++i) {
            nodes.get(i).next = nodes.get(i+1);
        }

        Node orig = nodes.get(0);
        System.out.println("Orig: " + listToString(orig));
        System.out.println("Copy: " + listToString(copyRandomList(orig)));
    }

    public void test2() {
        System.out.println("== Test 2 ==");
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node(1, "1"));
        nodes.add(new Node(2, "2"));

        nodes.get(0).random = nodes.get(1);
        nodes.get(1).random = nodes.get(1);

        for(int i = 0; i < nodes.size()-1; ++i) {
            nodes.get(i).next = nodes.get(i+1);
        }

        Node orig = nodes.get(0);
        System.out.println("Orig: " + listToString(orig));
        System.out.println("Copy: " + listToString(copyRandomList(orig)));
    }

    public void test3() {
        System.out.println("== Test 3 ==");
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node(3, "1"));
        nodes.add(new Node(3, "2"));
        nodes.add(new Node(3, "3"));

        nodes.get(0).random = null;
        nodes.get(1).random = nodes.get(0);
        nodes.get(2).random = null;

        for(int i = 0; i < nodes.size()-1; ++i) {
            nodes.get(i).next = nodes.get(i+1);
        }

        Node orig = nodes.get(0);
        System.out.println("Orig: " + listToString(orig));
        System.out.println("Copy: " + listToString(copyRandomList(orig)));
    }

    public static void main(String[] args) {
        CopyListWithRandomPointer solver = new CopyListWithRandomPointer();
        solver.test1();
        solver.test2();
        solver.test3();
    }
}
