package com.toastedbits.leetcode.trie;

import java.util.HashMap;
import java.util.Map;

public class ImplementTriePrefixTree {
    class Trie {
        private boolean isWord;
        private Map<Character, Trie> children;

        public Trie() {

        }

        public void insert(String word) {
            Trie cur = this;
            for(char c : word.toCharArray()) {
                if(cur.children == null) {
                    cur.children = new HashMap<>();
                }
                if(!cur.children.containsKey(c)) {
                    cur.children.put(c, new Trie());
                }
                cur = cur.children.get(c);
            }
            cur.isWord = true;
        }

        public boolean search(String word) {
            Trie cur = this;
            for(char c : word.toCharArray()) {
                if(cur.children == null || !cur.children.containsKey(c)) {
                    return false;
                }
                cur = cur.children.get(c);
            }
            return cur.isWord;
        }

        public boolean startsWith(String prefix) {
            Trie cur = this;
            for(char c : prefix.toCharArray()) {
                if(cur.children == null || !cur.children.containsKey(c)) {
                    return false;
                }
                cur = cur.children.get(c);
            }
            return true;
        }
    }

    private void test1() {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));   // return True
        System.out.println(trie.search("app"));     // return False
        System.out.println(trie.startsWith("app")); // return True
        trie.insert("app");
        System.out.println(trie.search("app"));     // return True
    }

    public void test2() {
        Trie trie = new Trie();
        trie.search("a");
    }

    public static void main(String[] args) {
        ImplementTriePrefixTree solver = new ImplementTriePrefixTree();
        solver.test1();
        solver.test2();
    }
}
