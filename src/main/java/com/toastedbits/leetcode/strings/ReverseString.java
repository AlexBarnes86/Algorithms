package com.toastedbits.leetcode.strings;

public class ReverseString {
    public void reverseString(char[] s) {
        if(s == null) {
            return;
        }

        StringBuilder sb = new StringBuilder(new String(s));
        char[] ary = sb.reverse().toString().toCharArray();
        System.arraycopy(ary, 0, s, 0, s.length);
    }

    public static void main(String[] args) {
        ReverseString solver = new ReverseString();
        solver.reverseString(new char[]{});
        solver.reverseString(new char[]{'a'});
        solver.reverseString(new char[]{'a','b'});
    }
}
