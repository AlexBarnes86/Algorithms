package com.toastedbits.leetcode.arrays;

public class FindPivotIndex {
    public int pivotIndex(int[] nums) {
        int[] rightSums = new int[nums.length];

        int sum = 0;
        for(int i = nums.length - 1; i >= 0; --i) {
            rightSums[i] = sum;
            sum += nums[i];
        }

        sum = 0;
        for(int i = 0; i < nums.length; ++i) {
            if(sum == rightSums[i]) {
                return i;
            }
            sum += nums[i];
        }

        return -1;
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
        System.out.println(pivotIndex(ary));
    }

    public static void main(String[] args) {
        FindPivotIndex fpi = new FindPivotIndex();
        fpi.solve(new int[]{1,7,3,6,5,6});
        fpi.solve(new int[]{1,2,3});
        fpi.solve(new int[]{2,1,-1});
    }
}
