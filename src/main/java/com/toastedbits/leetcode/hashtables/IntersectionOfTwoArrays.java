package com.toastedbits.leetcode.hashtables;

import java.util.HashSet;
import java.util.Set;

public class IntersectionOfTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        for(int num : nums1) {set1.add(num);}
        Set<Integer> result = new HashSet<>();
        for(int num : nums2) {
            if(set1.contains(num)) {
                result.add(num);
            }
        }

        Object[] objAry = result.toArray();
        int[] ary = new int[result.size()];

        for(int i = 0; i < objAry.length; ++i) {
            ary[i] = (int)objAry[i];
        }
        return ary;
    }
}
