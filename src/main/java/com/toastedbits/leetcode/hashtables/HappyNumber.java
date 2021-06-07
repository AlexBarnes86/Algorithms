package com.toastedbits.leetcode.hashtables;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {
    private int step(int n) {
        int total = 0;
        while(n != 0) {
            total += (n % 10) * (n % 10);
            n /= 10;
        }
        return total;
    }

    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while(!set.contains(n)) {
            set.add(n);
            n = step(n);
            if(n == 1) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        HappyNumber solver = new HappyNumber();
        System.out.println(solver.isHappy(19));
        System.out.println(solver.isHappy(2));
    }
}
