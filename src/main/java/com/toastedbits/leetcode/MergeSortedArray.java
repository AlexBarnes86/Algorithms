package com.toastedbits.leetcode;

import java.util.Arrays;

public class MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int k = nums1.length-1;
        m--; n--;
        while(k >= 0) {
            if(n == -1) {
                nums1[k] = nums1[m];
                m--;
            }
            else if(m == -1) {
                nums1[k] = nums2[n];
                n--;
            }
            else if(nums1[m] > nums2[n]) {
                nums1[k] = nums1[m];
                m--;
            }
            else {
                nums1[k] = nums2[n];
                n--;
            }
            k--;
        }
    }

    public static void main(String[] args) {
        MergeSortedArray msa = new MergeSortedArray();
        int[] left = new int[]{0};
        msa.merge(left, 0, new int[] {1}, 1);
        Arrays.stream(left).forEach(System.out::print);
        System.out.println();
    }
}
