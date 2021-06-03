package com.toastedbits.leetcode.strings;

public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0) {
            return "";
        }
        if(strs.length == 1) {
            return strs[0];
        }

        int len = 0;
        while(true) {
            if(len >= strs[0].length()) {
                return strs[0].substring(0, len);
            }

            char ch = strs[0].charAt(len);
            for(int i = 1; i < strs.length; ++i) {
                if(len >= strs[i].length() || strs[i].charAt(len) != ch) {
                    return strs[0].substring(0, len);
                }
            }
            len++;
        }
    }

    private void solve(String[] ary) {
        System.out.println(longestCommonPrefix(ary));
    }

    public static void main(String[] args) {
        LongestCommonPrefix solver = new LongestCommonPrefix();
        solver.solve(new String[]{"flower","flow","flight"});
        solver.solve(new String[]{"dog","racecar","car"});
        solver.solve(new String[]{"dog"});
        solver.solve(new String[]{});
        solver.solve(new String[]{"", "dog"});
    }
}
