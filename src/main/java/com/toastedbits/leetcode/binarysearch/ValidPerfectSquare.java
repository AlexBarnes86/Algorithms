package com.toastedbits.leetcode.binarysearch;

public class ValidPerfectSquare {
    public boolean isPerfectSquare(int num) {
        int left = 1;
        int right = 46341; // ceil(sqrt(Integer.MAX_VALUE))
        while(left < right) {
            int mid = left + (right - left) / 2;
            int mid2 = mid*mid;
            if(mid2 == num) {
                return true;
            }
            else if(mid2 < num) {
                left = mid+1;
            }
            else {
                right = mid;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ValidPerfectSquare solver = new ValidPerfectSquare();
        for(int i = 1; i < 1000; ++i) {
            if(solver.isPerfectSquare(i)) {
                System.out.println(i + ": " + solver.isPerfectSquare(i));
            }
        }
        System.out.println(solver.isPerfectSquare(16));
        System.out.println(solver.isPerfectSquare(14));
        System.out.println(solver.isPerfectSquare(2147395600));
    }
}
