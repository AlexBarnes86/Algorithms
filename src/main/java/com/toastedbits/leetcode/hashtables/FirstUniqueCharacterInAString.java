package com.toastedbits.leetcode.hashtables;

import java.util.HashMap;
import java.util.Map;

public class FirstUniqueCharacterInAString {
    public int firstUniqChar(String s) {
        if(s == null || s.length() == 0) {
            return -1;
        }

        Map<Character, Integer> count = new HashMap<>();
        for(int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            count.compute(ch, (k, v) -> v == null ? 1 : v + 1);
        }

        for(int i = 0; i < s.length(); ++i) {
            if(count.get(s.charAt(i)) == 1) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        FirstUniqueCharacterInAString solver = new FirstUniqueCharacterInAString();
        System.out.println(solver.firstUniqChar("leetcode"));
        System.out.println(solver.firstUniqChar("loveleetcode"));
        System.out.println(solver.firstUniqChar("aabb"));
        System.out.println(solver.firstUniqChar("aaaa"));
        System.out.println(solver.firstUniqChar("bbbbaaaa"));
    }
}
