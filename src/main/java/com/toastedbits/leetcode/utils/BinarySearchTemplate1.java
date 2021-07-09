package com.toastedbits.leetcode.utils;

public class BinarySearchTemplate1 {
    /**
     * Template #1 is the most basic and elementary form of Binary Search.
     * It is the standard Binary Search Template that most high schools or universities use when they first teach students
     * computer science. Template #1 is used to search for an element or condition which can be determined by accessing a
     * single index in the array.
     *
     * + Most basic and elementary form of Binary Search
     * + Search Condition can be determined without comparing to the element's neighbors (or use specific elements around it)
     * + No post-processing required because at each step, you are checking to see if the element has been found.
     *   If you reach the end, then you know the element is not found
     *
     * This template is useful:
     * + when no post processing is needed
     * + looking for condition on a single element
     * + Example: only need to search and return the matched index
     */
    public static int binarySearch(int[] nums, int target){
        if(nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0, right = nums.length - 1;
        while(left <= right) {
            // Prevent (left + right) overflow
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                return mid;
            }
            else if(nums[mid] < target) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        //No more candidates, all have been evaluated
        // End Condition: left > right, right+1 == left

        return -1;
    }
}
