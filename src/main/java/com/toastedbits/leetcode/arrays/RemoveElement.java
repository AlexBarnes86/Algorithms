package com.toastedbits.leetcode.arrays;

public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        int ct = 0;
        for(int i = nums.length-1; i >= 0; --i) {
            if(nums[i] == val) {
                ct++;
                if (nums.length - 1 - i >= 0)
                    System.arraycopy(nums, i + 1, nums, i, nums.length - 1 - i);
            }
        }
        return nums.length - ct;
    }

    public static void main(String[] args) {
        RemoveElement re = new RemoveElement();
        int[] ary = new int[] {};
        int length = re.removeElement(ary, 2);
        for(int i = 0; i < length; ++i) {
            System.out.print(ary[i]);
        }
        System.out.println();
    }
}
