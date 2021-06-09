package com.toastedbits.leetcode.hashtables;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        if(s.length() == 0) {
            return 0;
        }

        Set<Character> set = new HashSet<>();
        int ct = 0;
        for(int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if(set.contains(ch)) {
                return Math.max(ct, lengthOfLongestSubstring(s.substring(1)));
            }
            else {
                set.add(ch);
                ct++;
            }
        }

        return ct;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters solver = new LongestSubstringWithoutRepeatingCharacters();
        System.out.println(solver.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(solver.lengthOfLongestSubstring("bbbbb"));
        System.out.println(solver.lengthOfLongestSubstring("pwwkew"));
        System.out.println(solver.lengthOfLongestSubstring(""));
        System.out.println(solver.lengthOfLongestSubstring(" "));
        System.out.println(solver.lengthOfLongestSubstring("dvdf"));
    }
}
