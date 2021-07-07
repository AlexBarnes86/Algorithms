package com.toastedbits.leetcode.binarysearch;

public class FindPeakElement {
    private boolean isPeak(int[] nums, int n) {
        return n >= 0 && n < nums.length && nums[n-1] < nums[n] && nums[n] > nums[n+1];
    }

    private int findPeakElement(int[] nums, int left, int right) {
        while (left < right) {
            int mid = left + (right - left) / 2;
            if(isPeak(nums, mid)) {
                return mid;
            }
            else if(nums[mid-1] < nums[mid]) {
                left = mid;
            }
            else {
                right = mid;
            }
        }

        return -1;
    }

    public int findPeakElement(int[] nums) {
        if(nums == null) {
            return -1;
        }
        else if(nums.length == 1) {
            return 0;
        }
        else if(nums.length == 2) {
            return nums[0] < nums[1] ? 1 : 0;
        }
        else if(nums[0] > nums[1]) {
            return 0;
        }
        else if(nums[nums.length-1] > nums[nums.length-2]) {
            return nums.length - 1;
        }

        return findPeakElement(nums, 1, nums.length-1);
    }

    public void test1() {
        System.out.println("== Test 1 ==");
        System.out.println(findPeakElement(new int[]{1,2,3,1}));
    }

    public void test2() {
        System.out.println("== Test 2 ==");
        System.out.println(findPeakElement(new int[]{1,2,1,3,5,6,4}));
    }

    public void myTest1() {
        System.out.println("== My Test 1 ==");
        System.out.println(findPeakElement(new int[]{0}));
    }
    public void myTest2() {
        System.out.println("== My Test 2 ==");
        System.out.println(findPeakElement(new int[]{0,1}));
    }
    public void myTest3() {
        System.out.println("== My Test 3 ==");
        System.out.println(findPeakElement(new int[]{1,0}));
    }
    public void myTest4() {
        System.out.println("== My Test 4 ==");
        System.out.println(findPeakElement(new int[]{2,1,0}));
    }
    public void myTest5() {
        System.out.println("== My Test 5 ==");
        System.out.println(findPeakElement(new int[]{0,1,2}));
    }


    public static void main(String[] args) {
        FindPeakElement solver = new FindPeakElement();
        solver.test1();
        solver.test2();
        solver.myTest1();
        solver.myTest2();
        solver.myTest3();
        solver.myTest4();
        solver.myTest5();
    }
}
