package com.toastedbits.leetcode.arrays;

import java.util.Arrays;

public class TwoSumTwo {
    public int[] twoSum(int[] numbers, int target) {
        if(numbers == null || numbers.length < 2) {
            return new int[]{};
        }

        for(int i = 0; i < numbers.length; ++i) {
            int idx = Arrays.binarySearch(numbers, i+1, numbers.length, target - numbers[i]);
            if(idx >= 0 && idx != i) {
                return new int[]{i+1, idx+1};
            }
        }

        return new int[]{};
    }

    private void solve(int[] numbers, int target) {
        System.out.print("numbers: [");
        ArrayUtil.print(numbers);
        System.out.print("] target: " + target + " ");
        ArrayUtil.println(twoSum(numbers, target));
    }

    public static void main(String[] args) {
        TwoSumTwo solver = new TwoSumTwo();
        solver.solve(new int[]{2,7,11,15}, 9);
        solver.solve(new int[]{2,3,4}, 6);
        solver.solve(new int[]{-1,0}, -1);
        solver.solve(new int[]{2,7,11,15}, 22);
        solver.solve(new int[]{2,7,11,15}, -1);
        solver.solve(new int[]{1,2,3,4,4,9,56,90}, 8);
        solver.solve(new int[]{1,3,4,4}, 8);
    }
}
