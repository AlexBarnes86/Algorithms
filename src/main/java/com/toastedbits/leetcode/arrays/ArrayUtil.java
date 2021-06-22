package com.toastedbits.leetcode.arrays;

public class ArrayUtil {
    public static void println(int[] ary) {
        println(ary, ary.length);
    }

    public static void println(int[] ary, int len) {
        print(ary, len);
        System.out.println();
    }

    public static void print(int[] ary) {
        print(ary, ary.length);
    }

    public static void print(int[] ary, int len) {
        System.out.print("len: " + len + ", ary: ");
        for(int i = 0; i < len; ++i) {
            System.out.print(ary[i]);
            if(i != len-1) {
                System.out.print(", ");
            }
        }
    }
    
    public static void println(int[][] ary) {
        for(int r = 0; r < ary.length; ++r) {
            for(int c = 0; c < ary[r].length; ++c) {
                System.out.print(ary[r][c]);
                if(c != ary[r].length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }
}
