package com.toastedbits.leetcode.hashtables;

import java.util.HashMap;
import java.util.Map;

public class FourSumTwo {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int ct = 0;
        Map<Integer, Integer> possible123 = new HashMap<>();
        for(int i = 0; i < nums1.length; ++i) {
            for(int j = 0; j < nums2.length; ++j) {
                for (int k = 0; k < nums3.length; ++k) {
                    possible123.compute(nums1[i]+nums2[j]+nums3[k], (key, v) -> v == null ? 1 : v+1);
                }
            }
        }

        for (int l = 0; l < nums1.length; ++l) {
            if(possible123.containsKey(-nums4[l])) {
                ct += possible123.get(-nums4[l]);
            }
        }

        return ct;
    }

    public static void main(String[] args) {
        FourSumTwo solver = new FourSumTwo();
        System.out.println(solver.fourSumCount(new int[]{1,2},new int[]{-2,-1}, new int[]{-1,2}, new int[]{0,2}) == 2);
        System.out.println(solver.fourSumCount(new int[]{0},new int[]{0}, new int[]{0}, new int[]{0}) == 1);
    }
}
