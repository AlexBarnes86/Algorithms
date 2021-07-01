package com.toastedbits.leetcode.trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ImplementTriePrefixTree {
    static class Trie {
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

    static public Trie scrabble() throws IOException {
        Trie trie = new Trie();
        try(InputStream is = ImplementTriePrefixTree.class.getResourceAsStream("/ScrabbleWords2019.txt")) {
            try(InputStreamReader isr = new InputStreamReader(is)) {
                try (BufferedReader br = new BufferedReader(isr)) {
                    String line = null;
                    while((line = br.readLine()) != null) {
                        trie.insert(line.toLowerCase().trim());
                    }
                }
            }
        }
        return trie;
    }

    public static void main(String[] args) throws IOException {
        ImplementTriePrefixTree solver = new ImplementTriePrefixTree();
        solver.test1();
        solver.test2();

        Trie trie = scrabble();

        Scanner sc = new Scanner(System.in);
        String line = null;
        while(true) {
            line = sc.nextLine();
            if("quit()".equals(line)) {
                break;
            }
            System.out.println("is word: " + trie.search(line) + ", is prefix: " + trie.startsWith(line));
        }
    }
}
