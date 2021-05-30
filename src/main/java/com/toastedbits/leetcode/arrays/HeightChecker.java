package com.toastedbits.leetcode.arrays;

import java.util.Arrays;

public class HeightChecker {
    public int heightChecker(int[] nums) {
        int[] copy = Arrays.copyOf(nums, nums.length);
        Arrays.sort(nums);
        int ct = 0;
        for(int i = 0; i < nums.length; ++i) {
            if(copy[i] != nums[i]) {
                ct++;
            }
        }
        return ct;
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
        System.out.println(heightChecker(ary));
    }

    public static void main(String[] args) {
        HeightChecker solver = new HeightChecker();
        solver.solve(new int[]{1,1,4,2,1,3});
        solver.solve(new int[]{5,1,2,3,4});
        solver.solve(new int[]{1,2,3,4,5});
    }
}
