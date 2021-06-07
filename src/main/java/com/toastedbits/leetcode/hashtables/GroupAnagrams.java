package com.toastedbits.leetcode.hashtables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for(String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            map.compute(new String(chars), (k, v) -> {
                if(v == null) {
                    List<String> newList = new ArrayList<>();
                    newList.add(str);
                    return newList;
                }
                else {
                    v.add(str);
                    return v;
                }
            });
        }
        List<List<String>> result = new ArrayList<>();
        for(Map.Entry<String, List<String>> entry : map.entrySet()) {
            result.add(entry.getValue());
        }

        return result;
    }

    public static void main(String[] args) {
        GroupAnagrams solver = new GroupAnagrams();
        System.out.println(solver.groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"}));
        System.out.println(solver.groupAnagrams(new String[]{""}));
        System.out.println(solver.groupAnagrams(new String[]{"a"}));
    }
}
