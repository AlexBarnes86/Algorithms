package com.toastedbits.leetcode.binarysearch;

public class BinarySearch {
    public int search(int[] nums, int target, int left, int right) {
        int mid = (left + right)/2;
        //System.out.format("left: %d, right: %d, nums[%d]: %d, target: %d%n", left, right, mid, nums[mid], target);
        if(left > right) {
            return -1;
        }
        if(nums[mid] == target) {
            return mid;
        }
        return nums[mid] > target ? search(nums, target, left, mid-1) : search(nums, target, mid+1, right);
    }

    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return -1;
        }
        return search(nums, target, 0, nums.length-1);
    }

    public void test1() {
        System.out.println("== Test 1 ==");
        System.out.println(search(new int[]{-1,0,3,5,9,12}, 9));
    }

    public void test2() {
        System.out.println("== Test 2 ==");
        System.out.println(search(new int[]{-1,0,3,5,9,12}, 2));
    }

    public void myTest1() {
        System.out.println("== My Test 1 ==");
        System.out.println(search(new int[]{}, 2));
        System.out.println(search(new int[]{1}, 2));
        System.out.println(search(new int[]{1}, 1));
        System.out.println(search(new int[]{1,2}, 1));
        System.out.println(search(new int[]{1,2}, 2));
    }

    public static void main(String[] args) {
        BinarySearch solver = new BinarySearch();
        solver.test1();
        solver.test2();
        solver.myTest1();
    }
}
