package com.toastedbits.leetcode.utils;

public class BinarySearchTemplate2 {
    /**
     * Template #2 is an advanced form of Binary Search.
     * It is used to search for an element or condition which requires accessing the current index and
     * its immediate right neighbor's index in the array.
     *
     * + An advanced way to implement Binary Search.
     * + Search Condition needs to access element's immediate right neighbor
     * + Use element's right neighbor to determine if condition is met and decide whether to go left or right
     * + Gurantees Search Space is at least 2 in size at each step
     * + Post-processing required. Loop/Recursion ends when you have 1 element left.
     *   Need to assess if the remaining element meets the condition.
     */
    int binarySearch(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0, right = nums.length;
        while(left < right) {
            // Prevent (left + right) overflow
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                return mid;
            }
            else if(nums[mid] < target) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }

        // Post-processing:
        // End Condition: left == right
        if(left != nums.length && nums[left] == target) {
            return left;
        }

        return -1;
    }
}
