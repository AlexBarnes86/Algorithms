package com.toastedbits.leetcode.binarysearch;

import com.toastedbits.leetcode.utils.ArrayUtils;

import java.util.Arrays;

public class FindMinimumInRotatedSortedArrayTwo {
    public int linearFindMin(int[] nums) {
        int min = nums[0];
        if(nums[0] < nums[nums.length-1]) {
            return nums[0];
        }

        for(int i = 1; i < nums.length; ++i) {
            if(nums[i] < min) {
                return nums[i];
            }
        }

        return nums[0];
    }

    //Because of arrays of the form {a,a,...,b,a,a,...} our worst case will always be equivalent to linearFindMin O(n)
    //Non-degenerate case can still benefit from binary search though
    public int findMin(int[] nums) {
        int left = 0, right = nums.length -1;
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(nums[right] > nums[mid]) {
                right = mid;
            }
            else if (nums[right] == nums[mid]) {
                right = right-1;
            }
            else {
                left = mid+1;
            }
        }
        return nums[left];
    }

    public static void main(String[] args) {
        FindMinimumInRotatedSortedArrayTwo solver = new FindMinimumInRotatedSortedArrayTwo();
        for(int i = -10; i <= 10; ++i) {
            int[] ary = new int[]{-1,0,0,0,0,1,2,2,2,2,3,4,5,6,7};
            ArrayUtils.inPlaceRotateLeft(ary, i);
            System.out.println(Arrays.toString(ary));
            System.out.println(solver.findMin(ary));
        }

        System.out.println(solver.findMin(new int[]{0}));
        System.out.println(solver.findMin(new int[]{0,1}));
        System.out.println(solver.findMin(new int[]{3,3,3,3,3,3,3,3,1,3}));
        System.out.println(solver.findMin(new int[]{10,1,10,10,10}));

    }
}
