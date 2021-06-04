package com.toastedbits.leetcode.linkedlists;

public class DesignLinkedList {
    static class MyLinkedList {
        class Node {
            int val;
            Node next;

            public Node(int val, Node next) {
                this.val = val;
                this.next = next;
            }
        }

        Node head;

        /** Initialize your data structure here. */
        public MyLinkedList() {
        }

        /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
        public int get(int index) {
            Node cur = head;
            for(int i = 0; i < index; ++i) {
                if(cur == null) {
                    return -1;
                }
                cur = cur.next;
            }
            if(cur == null) {
                return -1;
            }
            return cur.val;
        }

        /** Add a node of value val before the first element of the linked list. After the insertion,
         * the new node will be the first node of the linked list. */
        public void addAtHead(int val) {
            head = new Node(val, head);
        }

        /** Append a node of value val to the last element of the linked list. */
        public void addAtTail(int val) {
            Node cur = head;
            if(cur == null) {
                head = new Node(val, null);
            }
            else {
                while (cur.next != null) {
                    cur = cur.next;
                }
                cur.next = new Node(val, null);
            }
        }

        /** Add a node of value val before the index-th node in the linked list.
         * If index equals to the length of linked list, the node will be appended to the end of linked list.
         * If index is greater than the length, the node will not be inserted. */
        public void addAtIndex(int index, int val) {
            if(index == 0) {
                addAtHead(val);
            }
            else if(head != null) {
                Node cur = head;
                for (int i = 0; i < index - 1; ++i) {
                    if(cur == null) {
                        return;
                    }
                    cur = cur.next;
                }
                if(cur == null) {
                    return;
                }
                cur.next = new Node(val, cur.next);
            }
        }

        /** Delete the index-th node in the linked list, if the index is valid. */
        public void deleteAtIndex(int index) {
            Node cur = head;
            Node prev = null;
            for(int i = 0; i < index; ++i) {
                if(cur == null) {
                    return;
                }
                prev = cur;
                cur = cur.next;
            }
            if(cur == head) {
                head = head.next;
            }
            else {
                prev.next = prev.next != null ? prev.next.next : null;
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Node cur = head;
            while(cur != null) {
                sb.append(cur.val);
                if(cur.next != null) {
                    sb.append(", ");
                }
                cur = cur.next;
            }
            return sb.toString();
        }
    }

    private static void test1() {
        MyLinkedList list = new MyLinkedList();
        list.addAtHead(1);
        System.out.println(list);

        list.addAtTail(3);
        System.out.println(list);

        list.addAtIndex(1,2);
        System.out.println(list);

        System.out.println("get: " + list.get(1));
        System.out.println(list);

        list.deleteAtIndex(0);
        System.out.println(list);

        System.out.println("get: " + list.get(0));
        System.out.println(list);
    }

    private static void test2() {
        MyLinkedList list = new MyLinkedList();
        list.addAtIndex(0, 10);
        System.out.println(list);

        list.addAtIndex(0, 20);
        System.out.println(list);

        list.addAtIndex(1, 30);
        System.out.println(list);

        System.out.println("get: " + list.get(0));
        System.out.println(list);
    }

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.addAtHead(2);
        System.out.println(list);

        list.deleteAtIndex(1);
        System.out.println(list);

        list.addAtHead(2);
        System.out.println(list);

        list.addAtHead(7);
        System.out.println(list);

        list.addAtHead(3);
        System.out.println(list);

        list.addAtHead(2);
        System.out.println(list);

        list.addAtHead(5);
        System.out.println(list);

        list.addAtTail(5);
        System.out.println(list);

        System.out.println(list.get(5));
        System.out.println(list);

        list.deleteAtIndex(6);
        System.out.println(list);

        list.deleteAtIndex(4);
        System.out.println(list);
    }
}
