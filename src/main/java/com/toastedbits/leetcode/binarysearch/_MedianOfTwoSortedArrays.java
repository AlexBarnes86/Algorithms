package com.toastedbits.leetcode.binarysearch;

public class _MedianOfTwoSortedArrays {
    private double findMedianSortedArray(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        else if(nums.length == 1) {
            return nums[0];
        }
        return nums.length % 2 == 0 ? (nums[nums.length/2-1]+nums[nums.length/2])/2.0 : nums[nums.length/2+1];
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1 == null || nums1.length == 0) {
            return findMedianSortedArray(nums2);
        }
        else if(nums2 == null ||  nums2.length == 0) {
            return findMedianSortedArray(nums1);
        }

        int totalLength = nums1.length + nums2.length;
        int left1 = 0, right1 = nums1.length-1;
        int left2 = 0, right2 = nums2.length-1;
        int totalLeft = 0;
        int totalRight = right1 + right2;

        while(totalLeft < totalRight) {
            int mid1 = left1 + (right1 - left1) / 2;
            int mid2 = left2 + (right2 - left2) / 2;

            if(nums1[mid1] < nums2[mid2]) {

            }
        }

        return 0;
    }

    public void test1() {
        System.out.println("== Test 1 ==");
        System.out.println(findMedianSortedArrays(new int[]{1,3}, new int[]{2}));
    }
    public void test2() {
        System.out.println("== Test 2 ==");
        System.out.println(findMedianSortedArrays(new int[]{1,2}, new int[]{3,4}));
    }
    public void test3() {
        System.out.println("== Test 3 ==");
        System.out.println(findMedianSortedArrays(new int[]{0,0}, new int[]{0,0}));
    }
    public void test4() {
        System.out.println("== Test 4 ==");
        System.out.println(findMedianSortedArrays(new int[]{}, new int[]{1}));
    }
    public void test5() {
        System.out.println("== Test 5 ==");
        System.out.println(findMedianSortedArrays(new int[]{2}, new int[]{}));
    }

    public static void main(String[] args) {
        _MedianOfTwoSortedArrays solver = new _MedianOfTwoSortedArrays();
        solver.test1();
        solver.test2();
        solver.test3();
        solver.test4();
        solver.test5();
    }
}
