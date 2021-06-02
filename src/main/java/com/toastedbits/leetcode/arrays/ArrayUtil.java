package com.toastedbits.leetcode.arrays;

public class ArrayUtil {
    public static void printArray(int[] ary) {
        printArray(ary, ary.length);
    }

    public static void printArray(int[] ary, int len) {
        System.out.print("len: " + len + ", ary: ");
        for(int i = 0; i < len; ++i) {
            System.out.print(ary[i]);
            if(i != len-1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
}
