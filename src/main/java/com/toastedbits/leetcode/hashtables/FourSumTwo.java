package com.toastedbits.leetcode.hashtables;

import java.util.HashMap;
import java.util.Map;

public class FourSumTwo {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        final Map<Integer, Integer> possible12 = combinations(nums1, nums2);
        final Map<Integer, Integer> possible34 = combinations(nums3, nums4);

        int ct = 0;
        for(Map.Entry<Integer, Integer> entry : possible12.entrySet()) {
            if(possible34.containsKey(-entry.getKey())) {
                ct += entry.getValue() * possible34.get(-entry.getKey());
            }
        }

        return ct;
    }

    private Map<Integer, Integer> combinations(int[] ary1, int[] ary2) {
        Map<Integer, Integer> combos = new HashMap<>();
        for (int num1 : ary1) {
            for (int num2 : ary2) {
                combos.compute(num1 + num2, (key, v) -> v == null ? 1 : v + 1);
            }
        }
        return combos;
    }

    public static void main(String[] args) {
        FourSumTwo solver = new FourSumTwo();
        System.out.println("2: " + solver.fourSumCount(new int[]{1,2},new int[]{-2,-1}, new int[]{-1,2}, new int[]{0,2}));
        System.out.println("1: " + solver.fourSumCount(new int[]{0},new int[]{0}, new int[]{0}, new int[]{0}));
        System.out.println("132: " + solver.fourSumCount(new int[]{-1,1,1,1,-1}, new int[]{0,-1,-1,0,1}, new int[]{-1,-1,1,-1,-1}, new int[]{0,1,0,-1,-1}));
    }
}
