package com.toastedbits.leetcode.trie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReplaceWords {
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

        public String shortest(String word) {
            Trie cur = this;
            int ct = 0;
            for(char c : word.toCharArray()) {
                if(cur != null && cur.isWord) {
                    return word.substring(0, ct);
                }
                ct++;
                if(cur == null || cur.children == null) {
                    return word;
                }
                cur = cur.children.get(c);
            }
            return word;
        }
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        Trie trie = new Trie();
        dictionary.forEach(trie::insert);
        return Arrays.stream(sentence.split(" ")).map(trie::shortest).collect(Collectors.joining(" "));
    }

    private void test1() {
        System.out.println(replaceWords(Arrays.asList("cat", "bat", "rat"), "the cattle was rattled by the battery"));
        System.out.println(replaceWords(Arrays.asList("a","b","c"), "aadsfasf absbs bbab cadsfafs"));
        System.out.println(replaceWords(Arrays.asList("a", "aa", "aaa", "aaaa"), "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa"));
        System.out.println(replaceWords(Arrays.asList("catt","cat","bat","rat"), "the cattle was rattled by the battery"));
        System.out.println(replaceWords(Arrays.asList("ac","ab"), "it is abnormal that this solution is accepted"));
    }

    public static void main(String[] args) {
        ReplaceWords solver = new ReplaceWords();
        solver.test1();
    }
}
