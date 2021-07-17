package com.toastedbits.leetcode.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsOfAPhoneNumber {
    Map<Character, List<Character>> digitMap = buildDigitMap();
    private static Map<Character, List<Character>> buildDigitMap() {
        Map<Character, List<Character>> map = new HashMap<>();
        map.put('2', Arrays.asList('a','b','c'));
        map.put('3', Arrays.asList('d','e','f'));
        map.put('4', Arrays.asList('g','h','i'));
        map.put('5', Arrays.asList('j','k','l'));
        map.put('6', Arrays.asList('m','n','o'));
        map.put('7', Arrays.asList('p','q','r','s'));
        map.put('8', Arrays.asList('t','u','v'));
        map.put('9', Arrays.asList('w','x','y','z'));
        return map;
    }

    List<String> list;
    public void dfs(int depth, String digits, String word) {
        if(depth == digits.length()) {
            list.add(word);
            return;
        }
        for(Character ch : digitMap.get(digits.charAt(depth))) {
            dfs(depth+1, digits, word + ch);
        }
    }

    public List<String> letterCombinations(String digits) {
        if(digits == null || digits.isEmpty()) {
            return new ArrayList<>();
        }

        list = new ArrayList<>();
        dfs(0, digits, "");
        return list;
    }

    public static void main(String[] args) {
        LetterCombinationsOfAPhoneNumber solver = new LetterCombinationsOfAPhoneNumber();
        System.out.println(solver.letterCombinations("23"));
    }
}
