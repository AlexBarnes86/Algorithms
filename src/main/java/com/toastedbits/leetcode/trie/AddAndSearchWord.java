package com.toastedbits.leetcode.trie;

import java.util.HashMap;
import java.util.Map;

public class AddAndSearchWord {
    static class WordDictionary {
        private boolean isWord;
        private Map<Character, WordDictionary> children;

        public WordDictionary() {

        }

        public void addWord(String word) {
            WordDictionary cur = this;
            for(char c : word.toCharArray()) {
                if(cur.children == null) {
                    cur.children = new HashMap<>();
                }
                if(!cur.children.containsKey(c)) {
                    cur.children.put(c, new WordDictionary());
                }
                cur = cur.children.get(c);
            }
            cur.isWord = true;
        }

        public boolean search(final String word) {
            WordDictionary cur = this;
            int ct = 0;
            for(char c : word.toCharArray()) {
                ct++;
                if(c == '.') {
                    int finalCt = ct;
                    return cur.children != null && cur.children.values().stream().anyMatch(wd ->
                        wd.search(word.substring(finalCt))
                    );
                }
                if(cur.children == null || !cur.children.containsKey(c)) {
                    return false;
                }
                cur = cur.children.get(c);
            }
            return cur.isWord;
        }
    }

    public static void test1() {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("pad")); // return False
        System.out.println(wordDictionary.search("bad")); // return True
        System.out.println(wordDictionary.search(".ad")); // return True
        System.out.println(wordDictionary.search("b..")); // return True
    }

    public static void main(String[] args) {
        test1();
    }
}
