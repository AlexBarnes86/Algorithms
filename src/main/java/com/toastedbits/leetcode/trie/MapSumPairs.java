package com.toastedbits.leetcode.trie;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class MapSumPairs {
    static class MapSum {
        private boolean isWord;
        int value;
        private Map<Character, MapSum> children;

        public MapSum() {

        }

        public void insert(String word, int value) {
            MapSum cur = this;
            for(char c : word.toCharArray()) {
                if(cur.children == null) {
                    cur.children = new HashMap<>();
                }
                if(!cur.children.containsKey(c)) {
                    cur.children.put(c, new MapSum());
                }
                cur = cur.children.get(c);
            }
            cur.isWord = true;
            cur.value = value;
        }

        public boolean search(String word) {
            MapSum cur = this;
            for(char c : word.toCharArray()) {
                if(cur.children == null || !cur.children.containsKey(c)) {
                    return false;
                }
                cur = cur.children.get(c);
            }
            return cur.isWord;
        }

        public boolean startsWith(String prefix) {
            MapSum cur = this;
            for(char c : prefix.toCharArray()) {
                if(cur.children == null || !cur.children.containsKey(c)) {
                    return false;
                }
                cur = cur.children.get(c);
            }
            return true;
        }

        public int sum(String prefix) {
            MapSum cur = this;
            for(char c : prefix.toCharArray()) {
                if(cur.children == null || !cur.children.containsKey(c)) {
                    return 0;
                }
                cur = cur.children.get(c);
            }

            Queue<MapSum> queue = new LinkedList<>();
            queue.offer(cur);
            int total = 0;
            while(!queue.isEmpty()) {
                cur = queue.poll();
                total += cur.value;
                if(cur.children != null) {
                    cur.children.values().forEach(queue::offer);
                }
            }
            return total;
        }
    }

    private void test1() {
        System.out.println("== Test1 ==");
        MapSum mapSum = new MapSum();
        mapSum.insert("apple", 3);
        System.out.println(mapSum.sum("ap"));
        mapSum.insert("app", 2);
        System.out.println(mapSum.sum("ap"));
    }

    public void myTest1() {
        System.out.println("== MyTest1 ==");
        MapSum mapSum = new MapSum();
        mapSum.search("a");
        System.out.println(mapSum.sum("b"));
    }

    public static void main(String[] args) throws IOException {
        MapSumPairs solver = new MapSumPairs();
        solver.test1();
        solver.myTest1();
    }
}
