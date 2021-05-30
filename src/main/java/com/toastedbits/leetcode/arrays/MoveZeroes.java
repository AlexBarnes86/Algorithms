package com.toastedbits.leetcode.arrays;

public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        if (nums == null) {
            return;
        }

        int writePointer = 0;

        for (int readPointer = 0; readPointer < nums.length; readPointer++) {
            if (nums[readPointer] != 0) {
                nums[writePointer] = nums[readPointer];
                writePointer++;
            }
        }

        while(writePointer < nums.length) {
            nums[writePointer++] = 0;
        }
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
        moveZeroes(ary);
        printArray(ary);
    }

    public static void main(String[] args) {
        MoveZeroes mz = new MoveZeroes();
        mz.solve(new int[]{0,1,0,3,12});
        mz.solve(new int[]{0});
    }
}
