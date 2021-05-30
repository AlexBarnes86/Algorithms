package com.toastedbits.leetcode;

public class MaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0, ct = 0;
        for(int num : nums) {
            ct = num == 1 ? ct + 1 : 0;
            max = Math.max(ct, max);
        }
        return max;
    }
}
