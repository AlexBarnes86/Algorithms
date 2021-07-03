package com.toastedbits.leetcode.trie;

//Unsolved
public class _MaximumXOROfTwoNumbersInAnArray {
    static class Trie {
        private Integer value;
        private Trie[] children;
        private Trie parent;

        public Trie() {

        }

        public void insert(int num) {
            Trie cur = this;
            String bits = new StringBuilder(Integer.toString(num, 2)).reverse().toString();
            for(char c : bits.toCharArray()) {
                if(cur.children == null) {
                    cur.children = new Trie[2];
                }
                if(cur.children[c-'0'] == null) {
                    cur.children[c-'0'] = new Trie();
                    cur.children[c-'0'].parent = cur;
                }
                cur = cur.children[c-'0'];
            }
            cur.value = num;
        }

        void findMaximumXorFor(int num) {
            String bits = new StringBuilder(Integer.toString(num, 2)).reverse().toString();
            Trie cur = this;
            for(char c : bits.toCharArray()) {
                cur =  cur.children[c-'0'];
            }
            System.out.println(cur.value);
        }
    }

    public static int findMaximumXOR(int[] nums) {
        Trie trie = new Trie();
        int max = 0;
        for(int num : nums) {
            max = Math.max(max, num);
            trie.insert(num);
        }
        System.out.println(max);
        trie.findMaximumXorFor(max);
        return 0;
    }

    public static void main(String[] args) {
        findMaximumXOR(new int[]{3,10,5,25,2,8});
    }
}
