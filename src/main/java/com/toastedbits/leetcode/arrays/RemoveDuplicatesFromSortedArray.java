package com.toastedbits.leetcode.arrays;

public class RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
        int length = nums.length;
        for(int i = 1; i < length; ++i) {
            while (nums[i - 1] == nums[i] && i < length) {
                for (int j = i; j < nums.length - 1; ++j) {
                    nums[j] = nums[j + 1];
                }
                length--;
            }
        }
        return length;
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

    public static void main(String[] args) {
        RemoveDuplicatesFromSortedArray rdfsa = new RemoveDuplicatesFromSortedArray();
        int[] ary = new int[]{0,0,1,1,1,2,2,3,3,4};
        int len = rdfsa.removeDuplicates(ary);
        rdfsa.printArray(ary, len);
    }
}
