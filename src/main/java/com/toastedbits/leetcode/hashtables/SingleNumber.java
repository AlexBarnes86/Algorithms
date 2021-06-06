package com.toastedbits.leetcode.hashtables;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SingleNumber {
    public int singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num : nums) {
            if(set.contains(num)) {
                set.remove(num);
            }
            else {
                set.add(num);
            }
        }
        return (int)set.toArray()[0];
    }
}
