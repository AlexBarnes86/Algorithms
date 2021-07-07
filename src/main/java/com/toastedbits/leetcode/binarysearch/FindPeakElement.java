package com.toastedbits.leetcode.binarysearch;

public class FindPeakElement {
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > nums[mid + 1])
                r = mid;
            else
                l = mid + 1;
        }
        return l;
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
