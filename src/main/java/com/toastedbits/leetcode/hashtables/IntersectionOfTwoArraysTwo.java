package com.toastedbits.leetcode.hashtables;

import com.toastedbits.leetcode.arrays.ArrayUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class IntersectionOfTwoArraysTwo {
    public int[] intersect(int[] nums1, int[] nums2) {
        if(nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[]{};
        }

        Map<Integer, Integer> map1 = new HashMap<>();
        for(int num : nums1) {map1.compute(num, (k, v) -> v == null ? 1 : v + 1);}
        Map<Integer, Integer> map2 = new HashMap<>();
        for(int num : nums2) {map2.compute(num, (k, v) -> v == null ? 1 : v + 1);}

        Set<Integer> intersect = new HashSet<>();
        for(int num : nums2) {
            if(map1.containsKey(num)) {
                intersect.add(num);
            }
        }

        List<Integer> result = new ArrayList<>();
        intersect.forEach((n) -> {
           int repeats = Math.min(map1.get(n), map2.get(n));
           for(int i = 0; i < repeats; ++i) {
               result.add(n);
           }
        });

        int[] ary = new int[result.size()];

        for(int i = 0; i < result.size(); ++i) {
            ary[i] = result.get(i);
        }
        return ary;
    }

    public static void main(String[] args) {
        IntersectionOfTwoArraysTwo solver = new IntersectionOfTwoArraysTwo();
        ArrayUtil.println(solver.intersect(new int[]{1,2,2,1}, new int[]{2,2}));
        ArrayUtil.println(solver.intersect(new int[]{4,9,5}, new int[]{9,4,9,8,4}));
    }
}
