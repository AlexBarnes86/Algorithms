package com.toastedbits.leetcode.strings;

public class ImplementStrStr {
    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    public static void main(String[] args) {
        ImplementStrStr solver = new ImplementStrStr();
        System.out.println(solver.strStr("hello", "ll"));
        System.out.println(solver.strStr("aaaaa", "bba"));
        System.out.println(solver.strStr("", ""));
    }
}
