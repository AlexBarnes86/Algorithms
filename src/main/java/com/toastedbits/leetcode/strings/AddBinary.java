package com.toastedbits.leetcode.strings;

import java.math.BigInteger;

public class AddBinary {
    public String addBinary(String a, String b) {
        return new BigInteger(a, 2).add(new BigInteger(b, 2)).toString(2);
    }

    public static void main(String[] args) {
        AddBinary solver = new AddBinary();
        System.out.println(solver.addBinary("11", "1"));
    }
}
