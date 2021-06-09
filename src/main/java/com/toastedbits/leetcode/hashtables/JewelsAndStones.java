package com.toastedbits.leetcode.hashtables;

import java.util.Arrays;

public class JewelsAndStones {
    public int numJewelsInStones(String jewels, String stones) {
        char[] jewelChars = jewels.toCharArray();
        Arrays.sort(jewelChars);
        int ct = 0;
        for(char ch : stones.toCharArray()) {
            if(Arrays.binarySearch(jewelChars, ch) >= 0) {
                ct++;
            }
        }
        return ct;
    }

    public static void main(String[] args) {
        JewelsAndStones solver = new JewelsAndStones();
        System.out.println(solver.numJewelsInStones("aA", "aAAbbbb"));
        System.out.println(solver.numJewelsInStones("z", "ZZ"));
    }
}
