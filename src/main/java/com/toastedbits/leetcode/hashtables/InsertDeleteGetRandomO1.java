package com.toastedbits.leetcode.hashtables;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class InsertDeleteGetRandomO1 {
    class RandomizedSet {
        final Random random = new Random();
        final Set<Integer> set = new HashSet<>();

        /** Initialize your data structure here. */
        public RandomizedSet() {

        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            boolean res = !set.contains(val);
            set.add(val);
            return res;
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            boolean res = set.contains(val);
            set.remove(val);
            return res;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            int val = random.nextInt(set.size());
            return (int)set.toArray()[val];
        }
    }

    void test1() {
        RandomizedSet rs = new RandomizedSet();
        System.out.println("I1: " + rs.insert(1));
        System.out.println("R2: " + rs.remove(2));
        System.out.println("I2: " + rs.insert(2));
        System.out.println("Rn: " + rs.getRandom());
        System.out.println("R1: " + rs.remove(1));
        System.out.println("I2: " + rs.insert(2));
        System.out.println("Rn: " + rs.getRandom());
    }

    public static void main(String[] args) {
        InsertDeleteGetRandomO1 solver = new InsertDeleteGetRandomO1();
        solver.test1();
    }
}
