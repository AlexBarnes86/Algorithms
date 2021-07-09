package com.toastedbits.leetcode.binarysearch;

public class PowXN {
    public double helper(double orig, long n) {
        if(n == 0) {
            return 1;
        }
        if(n == 1) {
            return orig;
        }

        double product = orig;
        while(n != 1 && n != 0) {
            long pow = 1;
            double cur = orig;
            while(pow < n) {
                pow <<= 1;
                if(pow < n)
                    cur *= cur;
            }

            n -= (pow >> 1);
            product *= cur;
        }

        return product;
    }

    public double myPow(double x, int n) {
        if(x == 1 || n == 0) {
            return 1;
        }

        boolean recip = false;
        long N = n;
        if(N < 0) {
            N *= -1;
            recip = true;
        }

        double res = helper(x, N);
        return recip ? 1.0 / res : res;
    }

    public static void main(String[] args) {
        PowXN solver = new PowXN();
        System.out.println(solver.myPow(2.00000, 10));
        System.out.println(solver.myPow(2.10000, 3));
        System.out.println(solver.myPow(2.00000, -2));
        System.out.println(solver.myPow(8.66731, 4));
        System.out.println(solver.myPow(0.00001, 2147483647));
        System.out.println(solver.myPow(1.00000, -2147483648));
        System.out.println(solver.myPow(2.00000, -2147483648));
    }
}
