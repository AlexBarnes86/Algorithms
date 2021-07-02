package com.toastedbits.leetcode.explore.july2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

public class GrayCode {
    Map<Integer, List<String>> cache = new HashMap<>();

    public List<String> helper(int n) {
        if(n == 1) {
            return Arrays.asList("0", "1");
        }
        if(cache.containsKey(n)) {
            return cache.get(n);
        }
        List<String> subCodes = helper(n-1);
        List<String> result = new ArrayList<>();
        for(String subCode : subCodes) {
            result.add("0" + subCode);
        }
        for(int i = subCodes.size() - 1; i >= 0; i--) {
            result.add("1" + subCodes.get(i));
        }
        cache.put(n, result);
        return result;
    }

    public List<Integer> grayCode(int n) {
        return helper(n).stream().map(str -> Integer.valueOf(str, 2)).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        GrayCode solver = new GrayCode();
        System.out.println(solver.grayCode(4));
    }
}
