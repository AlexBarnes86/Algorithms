package com.toastedbits.leetcode.arrays;

public class SortArrayByParity {
    public int[] sortArrayByParity(int[] nums) {
        if (nums == null) {
            return null;
        }

        int leftPointer = 0, rightPointer = 0;

        while(leftPointer < nums.length && rightPointer < nums.length) {
            if(nums[leftPointer] % 2 == 0) {
                leftPointer++;
                rightPointer = leftPointer+1;
                continue;
            }
            else if(nums[rightPointer] % 2 == 1) {
                rightPointer++;
                continue;
            }
            int temp = nums[leftPointer];
            nums[leftPointer] = nums[rightPointer];
            nums[rightPointer] = temp;
            leftPointer++;
            rightPointer++;
        }

        return nums;
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
        sortArrayByParity(ary);
        printArray(ary);
    }

    public static void main(String[] args) {
        SortArrayByParity p = new SortArrayByParity();
        p.solve(new int[]{3,1,2,4});
        p.solve(new int[]{0});
    }
}
