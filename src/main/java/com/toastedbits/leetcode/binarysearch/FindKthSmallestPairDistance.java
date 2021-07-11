package com.toastedbits.leetcode.binarysearch;

public class FindKthSmallestPairDistance {
    //Brute force accepted
    public int smallestDistancePair(int[] nums, int k) {
        int[] dist = new int[1_000_001];
        for(int i = 0; i < nums.length; ++i) {
            for(int j = i+1; j < nums.length; ++j) {
                dist[Math.abs(nums[i]-nums[j])]++;
            }
        }
        int ct = 0;
        for(int i = 0; i < dist.length; ++i) {
            ct += dist[i];
            if(ct >= k) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        FindKthSmallestPairDistance solver = new FindKthSmallestPairDistance();
        System.out.println(solver.smallestDistancePair(new int[]{1,3,1}, 1));
        System.out.println(solver.smallestDistancePair(new int[]{1,1,1}, 2));
        System.out.println(solver.smallestDistancePair(new int[]{1,6,1}, 3));
    }
}
