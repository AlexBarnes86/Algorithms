package com.toastedbits.leetcode.hashtables;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        if(Objects.equals(s, t)) {
            return true;
        }
        if(s == null || t == null || s.length() != t.length()) {
            return false;
        }

        Map<Character, Character> map = new HashMap<>();
        Map<Character, Character> inv = new HashMap<>();
        for(int i = 0; i < s.length(); ++i) {
            Character from = s.charAt(i);
            Character to = t.charAt(i);
            if(map.containsKey(from) && !map.get(from).equals(to)) {
                return false;
            }
            else if(inv.containsKey(to) && !inv.get(to).equals(from)) {
                return false;
            }
            else {
                map.put(from, to);
                inv.put(to, from);
            }
        }

        return true;
    }

    public static void main(String[] args) {
        IsomorphicStrings solver = new IsomorphicStrings();
        System.out.println(solver.isIsomorphic("egg", "add"));
        System.out.println(!solver.isIsomorphic("foo", "bar"));
        System.out.println(solver.isIsomorphic("paper", "title"));
        System.out.println(solver.isIsomorphic(null, null));
        System.out.println(!solver.isIsomorphic("", null));
        System.out.println(!solver.isIsomorphic(null, ""));
        System.out.println(solver.isIsomorphic("", ""));
        System.out.println(!solver.isIsomorphic("", "a"));
        System.out.println(!solver.isIsomorphic(null, "a"));
        System.out.println(!solver.isIsomorphic("badc", "baba"));
    }
}
