package com.toastedbits.leetcode.arrays;

import java.util.Arrays;

public class ThirdMaximumNumber {
    public int thirdMax(int[] nums) {
        Arrays.sort(nums);
        int distinct = 1;
        for(int i = nums.length-2; i >= 0; --i) {
            if(nums[i] == nums[i+1]) {
                continue;
            }
            if(++distinct == 3) {
                return nums[i];
            }
        }
        return nums[nums.length-1];
    }

    private void printArray(int[] ary) {
        printArray(ary, ary.length);
    }

    private void printArray(int[] ary, int len) {
        System.out.print("len: " + len + ", ary: ");
        for(int i = 0; i < len; ++i) {
            System.out.print(ary[i]);
            if(i != len-1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    private void solve(int[] ary) {
        System.out.println(thirdMax(ary));
    }

    public static void main(String[] args) {
        ThirdMaximumNumber solver = new ThirdMaximumNumber();
        solver.solve(new int[]{3,2,1});
        solver.solve(new int[]{1,2});
        solver.solve(new int[]{2,2,3,1});
        solver.solve(new int[]{1,2,3,4,5,6});
    }
}