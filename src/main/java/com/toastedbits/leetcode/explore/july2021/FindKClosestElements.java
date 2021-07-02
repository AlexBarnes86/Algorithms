package com.toastedbits.leetcode.explore.july2021;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FindKClosestElements {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int idx = Arrays.binarySearch(arr, x);
        if(idx < 0) {
            idx = idx * -1  - 2;
        }
        int leftIdx = idx, rightIdx = idx + 1;
        LinkedList<Integer> result = new LinkedList<>();
        for(; k > 0; k--) {
            if(leftIdx < 0 && rightIdx >= arr.length) {
                return result;
            }
            if(leftIdx < 0) {
                result.addLast(arr[rightIdx]);
                rightIdx++;
                continue;
            }
            if(rightIdx > arr.length - 1) {
                result.addFirst(arr[leftIdx]);
                leftIdx--;
                continue;
            }

            int left = Math.abs(arr[leftIdx] - x);
            int right = Math.abs(arr[rightIdx] - x);
            if(left < right || (left == right && arr[leftIdx] < arr[rightIdx])) {
                result.addFirst(arr[leftIdx]);
                leftIdx--;
            }
            else {
                result.addLast(arr[rightIdx]);
                rightIdx++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        FindKClosestElements solver = new FindKClosestElements();
        solver.findClosestElements(new int[]{1,2,3,4,5}, 4, 3);
        solver.findClosestElements(new int[]{1,2,3,4,5}, 4, -1);
        solver.findClosestElements(new int[]{1,2,3,4,5}, 4, -1);
        solver.findClosestElements(new int[]{0,0,1,2,3,3,4,7,7,8}, 3, 5);
    }
}
