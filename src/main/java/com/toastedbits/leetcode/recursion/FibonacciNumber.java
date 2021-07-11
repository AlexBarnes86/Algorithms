package com.toastedbits.leetcode.recursion;

import java.util.HashMap;
import java.util.Map;

public class FibonacciNumber {
    public int fib(int n, Map<Integer, Integer> memo) {
        if(memo.containsKey(n)) {
            return memo.get(n);
        }
        int result = fib(n-1) + fib(n-2);
        memo.put(n, result);
        return result;
    }

    public int fib(int n) {
        Map<Integer, Integer> memo = new HashMap<>();
        memo.put(0, 0); memo.put(1,1); memo.put(2,1);
        return fib(n, memo);
    }
}
