package com.toastedbits.leetcode.binarysearch;

public class FindTheDuplicateNumber {
    //Floyd's Tortoise and Hare (Cycle Detection)
    //https://leetcode.com/problems/find-the-duplicate-number/solution/
    int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;
        while ((slow = nums[slow]) != (fast = nums[nums[fast]]))
            ;
        slow = 0;
        while ((slow = nums[slow]) != (fast = nums[fast]))
            ;
        return slow;
    }

    public static void main(String[] args) {
        FindTheDuplicateNumber solver = new FindTheDuplicateNumber();
        System.out.println(solver.findDuplicate(new int[]{1,3,4,2,2}));
        System.out.println(solver.findDuplicate(new int[]{3,1,3,4,2}));
        System.out.println(solver.findDuplicate(new int[]{1,1}));
        System.out.println(solver.findDuplicate(new int[]{1,1,2}));
    }
}
