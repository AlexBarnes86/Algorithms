package com.toastedbits.leetcode.stacksandqueues;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.sqrt;
import static java.lang.Math.ceil;
import static java.lang.Math.floor;

public class PerfectSquares {
    static int legendreNumSquares(int n) {
        if(n == 0) {
            return 0;
        }
        if(ceil(sqrt(n)) == floor(sqrt(n))) {
            return 1;
        }
        while(n % 4 == 0) { //Remove 4^a term
            n/=4;
        }
        if(n%8==7) { //Check if now of form 8b + 7
            return 4;
        }
        for(int i = 1; i*i <= n; ++i) {
            int base = (int)sqrt(n-i*i);
            if(base*base == n-i*i) {
                return 2;
            }
        }
        return 3;
    }

    Map<Integer, Integer> cache = new HashMap<>();

    public PerfectSquares() {
        cache.put(0, 0);
        cache.put(1, 1);
        for(int i = 1; i <= 10_000; ++i) {
            numSquares(i);
        }
    }

    public int numSquares(int n) {
        if(cache.containsKey(n)) {
            return cache.get(n);
        }
        int min = Integer.MAX_VALUE;
        for(int i = 1; i*i <= n; ++i) {
            min = Math.min(min, numSquares(n - i*i)+1);
        }
        cache.put(n, min);
        return min;
    }

    public static void main(String[] args) {
        PerfectSquares solver = new PerfectSquares();
        for(int i = 0; i < 100; ++i) {
            System.out.println(i + ": " + (solver.numSquares(i) == legendreNumSquares(i)));
        }
    }
}
