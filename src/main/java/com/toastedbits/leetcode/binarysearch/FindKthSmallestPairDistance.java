package com.toastedbits.leetcode.binarysearch;

import java.util.Arrays;

public class FindKthSmallestPairDistance {
    // binary search + DP
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] dp = new int[n+1];
        Arrays.fill(dp, 0);
        int lo = 0, hi = nums[n-1] - nums[0];

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int j = 1;
            for (int i = 0; i < n-1; i++) {
                // dp[i] stores the number of element
                // from [0...i] that has distance between i ~ j (j > i)
                // bigger than mid
                while (j < n && nums[j] - nums[i] <= mid) j++;
                dp[i+1] = dp[i] + (j-1-i);
            }

            if (dp[n-1] < k) lo = mid + 1;
            else hi = mid;
        }

        return lo;
    }

    public static void main(String[] args) {
        FindKthSmallestPairDistance solver = new FindKthSmallestPairDistance();
        System.out.println(solver.smallestDistancePair(new int[]{1,3,1}, 1));
        System.out.println(solver.smallestDistancePair(new int[]{1,1,1}, 2));
        System.out.println(solver.smallestDistancePair(new int[]{1,6,1}, 3));
    }
}
