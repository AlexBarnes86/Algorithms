package com.toastedbits.leetcode.recursion;

import java.util.HashMap;
import java.util.Map;

public class ClimbingStairs {
    public int climbStairs(int n) {
        return climbStairs(n, new HashMap<>());
    }

    public int climbStairs(int n, Map<Integer, Integer> memo) {
        if(memo.containsKey(n)) return memo.get(n);
        if(n <= 1) return 1;
        int result = climbStairs(n-1, memo) + climbStairs(n-2, memo);
        memo.put(n, result);
        return result;
    }

    public static void main(String[] args) {
        ClimbingStairs solver = new ClimbingStairs();
        for(int i = 1; i <= 45; ++i) {
            System.out.println(i + ": " + solver.climbStairs(i));
        }
    }
}
