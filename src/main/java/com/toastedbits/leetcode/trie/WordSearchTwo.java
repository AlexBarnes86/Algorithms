package com.toastedbits.leetcode.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordSearchTwo {
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

        void dfs(int r, int c, StringBuilder sb, Trie cur, boolean[][] visited, char[][] board, Set<String> discovered) {
            if(r < 0 || r >= board.length || c < 0 || c >= board[r].length || visited[r][c] || cur == null) {
                return;
            }
            visited[r][c] = true;
            sb.append(board[r][c]);
            cur = cur.children == null ? null : cur.children.get(board[r][c]);
            if(cur != null && cur.isWord) {
                discovered.add(sb.toString());
            }
            if(cur != null && cur.children != null) {
                dfs(r + 1, c, sb, cur, visited, board, discovered);
                dfs(r - 1, c, sb, cur, visited, board, discovered);
                dfs(r, c + 1, sb, cur, visited, board, discovered);
                dfs(r, c - 1, sb, cur, visited, board, discovered);
            }
            visited[r][c] = false;
            sb.deleteCharAt(sb.length()-1);
        }
    }

    public List<String> findWords(char[][] board, String[] words) {
        if(board == null || board.length == 0 || board[0].length == 0) {
            return new ArrayList<>();
        }

        Trie trie = new Trie();
        Arrays.stream(words).forEach(trie::insert);
        boolean[][] visited = new boolean[board.length][board[0].length];
        Set<String> discovered = new HashSet<>();
        for(int r = 0; r < board.length; ++r) {
            for(int c = 0; c < board[0].length; ++c) {
                trie.dfs(r, c, new StringBuilder(), trie, visited, board, discovered);
            }
        }
        return new ArrayList<>(discovered);
    }

    public void test1() {
        System.out.println("== Test 1 ==");
        char[][] board = new char[][] {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String[] words = new String[]{"oath","pea","eat","rain"};
        System.out.println(findWords(board, words));
    }

    public void test2() {
        System.out.println("== Test 2 ==");
        char[][] board = new char[][] {{'a','b'},{'c','d'}};
        String[] words = new String[]{"abcb"};
        System.out.println(findWords(board, words));
    }

    public void myTest1() {
        System.out.println("== My Test 1 ==");
        char[][] board = new char[][]{};
        String[] words = new String[]{};
        System.out.println(findWords(board, words));
    }

    public void myTest2() {
        System.out.println("== My Test 2 ==");
        char[][] board = new char[][] {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String[] words = new String[]{};
        System.out.println(findWords(board, words));
    }

    public void myTest3() {
        System.out.println("== My Test 3 ==");
        char[][] board = new char[][] {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String[] words = new String[]{"a"};
        System.out.println(findWords(board, words));
    }

    public static void main(String[] args) {
        WordSearchTwo solver = new WordSearchTwo();
        solver.test1();
        solver.test2();
        solver.myTest1();
        solver.myTest2();
        solver.myTest3();
    }
}
