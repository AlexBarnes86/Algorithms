package com.toastedbits.leetcode.arrays;

public class ValidMountainArray {
    public boolean validMountainArray(int[] arr) {
        if(arr.length < 3) {
            return false;
        }
        int i;
        boolean inclined = false;
        for(i = 1; i < arr.length; ++i) {
            if(arr[i-1] == arr[i]) {
                return false;
            }
            else if(arr[i-1] > arr[i]) {
                break;
            }
            inclined = true;
        }
        if(!inclined) {
            return false;
        }
        boolean declined = false;
        for(; i < arr.length; ++i) {
            if(arr[i-1] <= arr[i]) {
                return false;
            }
            declined = true;
        }
        return declined;
    }

    public static void main(String[] args) {
        ValidMountainArray vma = new ValidMountainArray();
        System.out.println(vma.validMountainArray(new int[]{0,2,3,4,5,2,1,0}));
        System.out.println(vma.validMountainArray(new int[]{0,2,3,3,5,2,1,0}));
        System.out.println();
        System.out.println(vma.validMountainArray(new int[]{2,1}));
        System.out.println(vma.validMountainArray(new int[]{3,5,5}));
        System.out.println(vma.validMountainArray(new int[]{0,3,2,1}));
        System.out.println(vma.validMountainArray(new int[]{0,1,2,3,4,5,6,7,8,9}));
        System.out.println(vma.validMountainArray(new int[]{9,8,7,6,5,4,3,2,1,0}));
    }
}
