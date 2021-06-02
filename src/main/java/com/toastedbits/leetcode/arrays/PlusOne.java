package com.toastedbits.leetcode.arrays;

import java.math.BigInteger;

public class PlusOne {
    public int[] plusOne(int[] digits) {
        StringBuilder sb = new StringBuilder();
        for(int digit : digits) {
            sb.append(digit);
        }
        BigInteger bigInt = new BigInteger(sb.toString());
        bigInt = bigInt.add(BigInteger.ONE);
        String strResult = bigInt.toString();
        int[] result = new int[strResult.length()];
        for(int i = 0; i < strResult.length(); ++i) {
            result[i] = strResult.charAt(i) - '0';
        }
        return result;
    }

    private void solve(int[] ary) {
        ArrayUtil.printArray(plusOne(ary));
    }

    public static void main(String[] args) {
        PlusOne solver = new PlusOne();
        solver.solve(new int[]{1,2,3});
        solver.solve(new int[]{4,3,2,1});
        solver.solve(new int[]{0});
        solver.solve(new int[]{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
        solver.solve(new int[]{9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9});
    }
}
