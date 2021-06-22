package com.toastedbits.leetcode.stacksandqueues;

import java.util.LinkedList;
import java.util.Queue;

public class ImplementStackUsingQueues {

    static class MyStack {
        Queue<Integer> queueA = new LinkedList<>();
        Queue<Integer> queueB = new LinkedList<>();

        /** Initialize your data structure here. */
        public MyStack() {

        }

        /** Push element x onto stack. */
        public void push(int x) {
            queueA.offer(x);
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            while(queueA.size() > 1) {
                queueB.offer(queueA.poll());
            }
            int result = queueA.poll();

            Queue<Integer> temp = queueA;
            queueA = queueB;
            queueB = temp;

            return result;
        }

        /** Get the top element. */
        public int top() {
            while(queueA.size() > 1) {
                queueB.offer(queueA.poll());
            }
            int result = queueA.peek();
            queueB.offer(queueA.poll());

            Queue<Integer> temp = queueA;
            queueA = queueB;
            queueB = temp;

            return result;
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return queueA.isEmpty();
        }
    }

    public static void main(String[] args) {
        MyStack myQueue = new MyStack();
        myQueue.push(1);
        myQueue.push(2);
        myQueue.push(3);
        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());
    }
}
