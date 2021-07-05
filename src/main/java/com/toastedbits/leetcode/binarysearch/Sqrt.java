package com.toastedbits.leetcode.binarysearch;

import java.math.BigInteger;

public class Sqrt {
    public int mySqrt(int x) {
        BigInteger X = new BigInteger(x+"");
        BigInteger left = BigInteger.ZERO;
        BigInteger right = new BigInteger(x+"");
        while(true) {
            BigInteger mid = right.add(left).divide(BigInteger.TWO);
            BigInteger low = mid.multiply(mid);
            BigInteger high = mid.add(BigInteger.ONE).multiply(mid.add(BigInteger.ONE));
            if(X.equals(low)) {
                return mid.intValue();
            }
            else if(X.equals(high)) {
                return mid.add(BigInteger.ONE).intValue();
            }
            else if(X.compareTo(low) > 0 && X.compareTo(high) < 0) {
                return mid.intValue();
            }
            else if(X.compareTo(low) > 0) {
                left = mid;
            }
            else {
                right = mid;
            }
        }
    }

    public static void main(String[] args) {
        Sqrt solver = new Sqrt();
        for(int i = 0; i <= 100; ++i) {
            System.out.println(i + ": " + solver.mySqrt(i));
        }
        System.out.println(2147395599 + ": " + solver.mySqrt(2147395599));
    }
}
