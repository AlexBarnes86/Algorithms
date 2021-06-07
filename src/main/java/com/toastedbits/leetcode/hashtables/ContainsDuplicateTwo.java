package com.toastedbits.leetcode.hashtables;

import java.util.HashMap;
import java.util.Map;

public class ContainsDuplicateTwo {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if(nums == null ||  nums.length == 0 || k == 0) {
            return false;
        }

        Map<Integer, Integer> lastSeen = new HashMap<>();
        for(int i = 0; i < nums.length; ++i) {
            if(lastSeen.containsKey(nums[i]) && i - lastSeen.get(nums[i]) <= k) {
                return true;
            }
            lastSeen.put(nums[i], i);
        }

        return false;
    }

    public static void main(String[] args) {
        ContainsDuplicateTwo solver = new ContainsDuplicateTwo();
        System.out.println(solver.containsNearbyDuplicate(new int[]{1,2,3,1}, 3));
        System.out.println(solver.containsNearbyDuplicate(new int[]{1,0,1,1}, 1));
        System.out.println(!solver.containsNearbyDuplicate(new int[]{1,2,3,1,2,3}, 2));
    }
}
