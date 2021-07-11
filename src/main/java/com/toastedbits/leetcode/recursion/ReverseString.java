package com.toastedbits.leetcode.recursion;

import java.util.Arrays;

public class ReverseString {
    private void reverseString(int left, int right, char[] s) {
        if(left >= right) {
            return;
        }
        s[left] ^= s[right];
        s[right] ^= s[left];
        s[left] ^= s[right];
        reverseString(left+1, right-1, s);
    }

    public void reverseString(char[] s) {
        if(s == null || s.length <= 1) {
            return;
        }
        reverseString(0, s.length-1, s);
    }

    public static void main(String[] args) {
        ReverseString solver = new ReverseString();
        for(int i = 0; i < 10; ++i) {
            char[] ary = new char[i];
            for(char j = 0; j < i; ++j) {
                ary[j] = (char)('a' + j);
            }
            System.out.println(Arrays.toString(ary));
            solver.reverseString(ary);
            System.out.println(Arrays.toString(ary));
        }
    }
}
