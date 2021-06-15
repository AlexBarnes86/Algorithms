package com.toastedbits.leetcode.stacksandqueues;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class DesignCircularQueue {
    class MyCircularQueue {
        Deque<Integer> myQueue = new LinkedList<>();
        int capacity;

        public MyCircularQueue(int k) {
            capacity = k;
        }

        public boolean enQueue(int value) {
            if(myQueue.size() == capacity) {
                return false;
            }
            myQueue.add(value);
            return true;
        }

        public boolean deQueue() {
            if(myQueue.size() == 0) {
                return false;
            }
            myQueue.remove();
            return true;
        }

        public int Front() {
            if(myQueue.isEmpty()) {
                return -1;
            }
            return myQueue.getFirst();
        }

        public int Rear() {
            if(myQueue.isEmpty()) {
                return -1;
            }
            return myQueue.getLast();
        }

        public boolean isEmpty() {
            return myQueue.isEmpty();
        }

        public boolean isFull() {
            return myQueue.size() == capacity;
        }
    }
}
