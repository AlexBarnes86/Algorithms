package com.toastedbits.leetcode.stacksandqueues;

import java.util.Stack;

public class ImplementQueueUsingStacks {
    static class MyQueue {
        Stack<Integer> stackA = new Stack<>();
        Stack<Integer> stackB = new Stack<>();

        /** Initialize your data structure here. */
        public MyQueue() {
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            while(!stackA.isEmpty()) {
                stackB.push(stackA.pop());
            }
            stackA.push(x);
            while(!stackB.isEmpty()) {
                stackA.push(stackB.pop());
            }
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            return stackA.pop();
        }

        /** Get the front element. */
        public int peek() {
            return stackA.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return stackA.isEmpty();
        }
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1);
        myQueue.push(2);
        myQueue.push(3);
        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());
    }
}
