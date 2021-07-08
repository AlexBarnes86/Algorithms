package com.toastedbits.leetcode.binarysearch;

import java.util.Arrays;

public class SearchForARange {
    public int[] searchRange(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return new int[]{-1,-1};
        }

        int start = Arrays.binarySearch(nums, target);
        int leftStart = start;
        int rightStart = start;
        if(start < 0) {
            return new int[]{-1,-1};
        }

        int left = 0;
        if(nums[0] != target) {
            //find left
            int prev = -1;
            while(leftStart != prev) {
                prev = leftStart;
                leftStart = Arrays.binarySearch(nums, 0, leftStart, target);
                if(leftStart < 0) {
                    left = prev;
                    break;
                }
            }
        }

        int right = nums.length-1;
        if(nums[nums.length-1] != target) {
            //find left
            int prev = -1;
            while(rightStart != prev) {
                prev = rightStart;
                rightStart = Arrays.binarySearch(nums, rightStart+1, nums.length-1, target);
                if(rightStart < 0) {
                    right = prev;
                    break;
                }
            }
        }

        return new int[]{left, right};
    }

    public static void main(String[] args) {
        SearchForARange solver = new SearchForARange();
        System.out.println(Arrays.toString(solver.searchRange(new int[]{5,7,7,8,8,10}, 8)));
        System.out.println(Arrays.toString(solver.searchRange(new int[]{5,7,7,8,8,10}, 6)));
        System.out.println(Arrays.toString(solver.searchRange(new int[]{}, 0)));
        System.out.println(Arrays.toString(solver.searchRange(new int[]{2,2}, 2)));
        System.out.println(Arrays.toString(solver.searchRange(new int[]{0,0,1,2,2}, 0)));
        System.out.println(Arrays.toString(solver.searchRange(new int[]{1,4}, 4)));
    }
}
