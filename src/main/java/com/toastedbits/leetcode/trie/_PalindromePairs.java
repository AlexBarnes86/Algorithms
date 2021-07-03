package com.toastedbits.leetcode.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Unsolved
public class _PalindromePairs {
    class WordDictionary {
        private boolean isWord;
        private int index;
        private Map<Character, WordDictionary> children;

        public WordDictionary() {

        }

        public void addWord(String word, int index) {
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
            cur.index = index;
        }

        public List<Integer> search(final String word) {
            WordDictionary cur = this;
            int ct = 0;
            for(char c : word.toCharArray()) {
                ct++;
                if(c == '.') {
                    if(cur.children == null) {
                        return Collections.emptyList();
                    }
                    List<Integer> indicies = new ArrayList<>();
                    for(WordDictionary child : cur.children.values()) {
                        indicies.addAll(child.search(word.substring(ct)));
                    }
                    return indicies;
                }
                if(cur.children == null || !cur.children.containsKey(c)) {
                    return Collections.emptyList();
                }
                cur = cur.children.get(c);
            }
            return cur.isWord ? Collections.singletonList(cur.index) : Collections.emptyList();
        }
    }

    public void test1() {
        System.out.println(palindromePairs(new String[]{"abcd","dcba","lls","s","sssll"}));
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        WordDictionary trie = new WordDictionary();
        for(int i = 0; i < words.length; ++i) {
            trie.addWord(words[i], i);
        }

        List<List<Integer>> results = new ArrayList<>();
        for(int i = 0; i < words.length; ++i) {
            final int a = i;
            final StringBuilder sb = new StringBuilder(words[i]).reverse();
            trie.search(sb.toString()).forEach(j -> results.add(Arrays.asList(a, j)));
            trie.search(sb.substring(1)).forEach(j -> results.add(Arrays.asList(a, j)));
            trie.search("." + sb).forEach(j -> results.add(Arrays.asList(a, j)));
        }

        return results;
    }

    public static void main(String[] args) {
        _PalindromePairs solver = new _PalindromePairs();
        solver.test1();
    }

}
