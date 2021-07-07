package com.toastedbits.leetcode.binarysearch;

import java.util.Map;

public class FirstBadVersion {
    int binarySearch(int left, int right) {
        int mid = left + (right - left) / 2;
        while(left <= right) {
            // Prevent (left + right) overflow
            mid = left + (right - left) / 2;
            boolean isBad = isBadVersion(mid);
            if(isBad && (mid == 1 || !isBadVersion(mid-1))) {
                return mid;
            }
            else if(isBad) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }

        return mid == 1 ? 1 : -1;
    }

    public int firstBadVersion(int n) {
        return binarySearch(1, n);
    }

    int badVersion;
    private boolean isBadVersion(int n) {
        return n >= badVersion;
    }

    public void test1() {
        System.out.println("== Test 1 ==");
        badVersion = 4;
        System.out.println(firstBadVersion(5));
    }

    public void test2() {
        System.out.println("== Test 2 ==");
        badVersion = 1;
        System.out.println(firstBadVersion(1));
    }

    public void myTest1() {
        System.out.println("== My Test 1 ==");
        badVersion = 1;
        System.out.println(firstBadVersion(Integer.MAX_VALUE));
    }

    public void myTest2() {
        System.out.println("== My Test 2 ==");
        badVersion = 2;
        System.out.println(firstBadVersion(2));
    }

    public static void main(String[] args) {
        FirstBadVersion solver = new FirstBadVersion();
        solver.test1();
        solver.test2();
        solver.myTest1();
        solver.myTest2();
    }
}
