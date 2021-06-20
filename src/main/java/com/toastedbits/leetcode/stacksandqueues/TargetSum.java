package com.toastedbits.leetcode.stacksandqueues;

import java.util.Arrays;

public class TargetSum {
    //https://leetcode.com/problems/target-sum/solution/
    public int findTargetSumWays(int[] nums, int S) {
        int sum = Arrays.stream(nums).sum();

        if (S > sum || S < -sum) {
            return 0;
        }

        int[][] dp = new int[nums.length][sum*2+1];
        dp[0][nums[0]+sum] = 1;
        dp[0][-nums[0]+sum] += 1;

        for (int i = 1; i < nums.length; i++) {
            for (int j = -sum; j <= sum; j++) {
                if (dp[i-1][j+sum] != 0) {
                    int n = nums[i];
                    dp[i][j+sum+n] += dp[i-1][j+sum];
                    dp[i][j+sum-n] += dp[i-1][j+sum];
                }
            }
        }

        return dp[nums.length-1][S+sum];
    }

    public static void main(String[] args) {
        TargetSum solver = new TargetSum();
        System.out.println(solver.findTargetSumWays(new int[]{1,1,1,1,1}, 3));
        System.out.println(solver.findTargetSumWays(new int[]{1}, 1));
    }
}
