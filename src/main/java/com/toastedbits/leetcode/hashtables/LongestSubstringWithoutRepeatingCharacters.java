package com.toastedbits.leetcode.hashtables;

import java.util.HashMap;

public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        // Creating a set to store the last positions of occurrence
        HashMap<Character, Integer> seen = new HashMap<>();
        int maximum_length = 0;

        // starting the inital point of window to index 0
        int start = 0;

        for (int end = 0; end < s.length(); end++) {
            // Checking if we have already seen the element or not
            if (seen.containsKey(s.charAt(end))) {
                // If we have seen the number, move the start pointer
                // to position after the last occurrence
                start = Math.max(start, seen.get(s.charAt(end)) + 1);
            }

            // Updating the last seen value of the character
            seen.put(s.charAt(end), end);
            maximum_length = Math.max(maximum_length, end - start + 1);
        }
        return maximum_length;
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
