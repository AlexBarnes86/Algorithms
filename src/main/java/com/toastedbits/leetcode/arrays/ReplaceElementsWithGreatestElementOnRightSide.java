package com.toastedbits.leetcode.arrays;

public class ReplaceElementsWithGreatestElementOnRightSide {
    public int[] replaceElements(int[] arr) {
        for(int i = 0; i < arr.length - 1; ++i) {
            int max = arr[i+1];
            for(int j = i+1; j < arr.length; ++j) {
                if(arr[j] > max) {
                    max = arr[j];
                }
            }
            arr[i] = max;
        }
        if(arr.length > 0)
            arr[arr.length-1] = -1;
        return arr;
    }

    public static void main(String[] args) {
        ReplaceElementsWithGreatestElementOnRightSide replace = new ReplaceElementsWithGreatestElementOnRightSide();
        for(int e : replace.replaceElements(new int[]{})) {
            System.out.print(e + " ");
        }
    }
}
