package com.toastedbits.leetcode.arrays;

public class RemoveDuplicatesFromSortedArray2 {
    public int removeDuplicates(int[] nums) {
        int cur = 0;
        int len = nums.length > 0 ? 1 : 0;
        for(int i = 1; i < nums.length; ++i) {
            while (i < nums.length && nums[cur] == nums[i]) {
                i++;
            }
            if (i < nums.length) {
                nums[len] = nums[i];
                cur = len;
                len++;
            }
        }
        return len;
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
        int len = removeDuplicates(ary);
        printArray(ary, len);
    }

    public static void main(String[] args) {
        RemoveDuplicatesFromSortedArray2 rdfsa = new RemoveDuplicatesFromSortedArray2();
        rdfsa.solve(new int[]{1,1,2});
        rdfsa.solve(new int[]{0,0,1,1,1,2,2,3,3,4});
        rdfsa.solve(new int[]{0});
        rdfsa.solve(new int[]{});
    }
}
