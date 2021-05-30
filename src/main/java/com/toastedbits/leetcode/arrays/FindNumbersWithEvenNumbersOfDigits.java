package com.toastedbits.leetcode.arrays;

public class FindNumbersWithEvenNumbersOfDigits {
    private int countDigits(int num) {
        int ct = 0;
        while(num != 0) {
            ct++;
            num /= 10;
        }
        return ct;
    }

    public int findNumbers(int[] nums) {
        int ct = 0;
        for(int num : nums) {
            ct += (countDigits(num) % 2 == 0 ? 1 : 0);
        }
        return ct;
    }
}
