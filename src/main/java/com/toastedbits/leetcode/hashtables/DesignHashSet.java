package com.toastedbits.leetcode.hashtables;

import java.util.HashSet;

public class DesignHashSet {
    class MyHashSet {
        HashSet<Integer> set = new HashSet<>();
        /** Initialize your data structure here. */
        public MyHashSet() {

        }

        public void add(int key) {
            set.add(key);
        }

        public void remove(int key) {
            set.remove(key);
        }

        /** Returns true if this set contains the specified element */
        public boolean contains(int key) {
            return set.contains(key);
        }
    }
}
