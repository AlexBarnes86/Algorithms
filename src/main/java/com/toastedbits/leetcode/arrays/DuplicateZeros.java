package com.toastedbits.leetcode.arrays;

import java.util.Arrays;

public class DuplicateZeros {
    private void shiftRight(int[] arr, int pos) {
        int cur = arr.length-1;
        while(cur != pos) {
            arr[cur] = arr[cur-1];
            cur--;
        }
    }
    public void duplicateZeros(int[] arr) {
        for(int i = arr.length-1; i >= 0; --i) {
            if(arr[i] == 0) {
                shiftRight(arr, i);
            }
        }
    }

    public static void main(String[] args) {
        DuplicateZeros dz = new DuplicateZeros();
        int[] ary = new int[]{1,0,2,3,0,4,5,0};
        dz.duplicateZeros(ary);
        Arrays.stream(ary).forEach(System.out::print);
        System.out.println();
    }
}
