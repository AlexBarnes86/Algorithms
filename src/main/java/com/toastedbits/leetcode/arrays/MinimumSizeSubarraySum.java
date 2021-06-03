package com.toastedbits.leetcode.arrays;

//TODO: Not optimal, there exists an O(n log n) solution
public class MinimumSizeSubarraySum {
    public int minSubArrayLen(int target, int[] nums) {
        if(nums == null || nums.length < 1 || target == 0) {
            return 0;
        }

        int best = Integer.MAX_VALUE;
        for(int left = 0; left < nums.length; ++left) {
            int total = nums[left];
            int ct = 1;
            while(total < target && left+ct < nums.length) {
                total += nums[left+ct];
                ct++;
            }
            if(total >= target && ct < best) {
                best = ct;
            }
        }

        return best == Integer.MAX_VALUE ? 0 : best;
    }

    private void solve(int target, int[] nums) {
        System.out.println(minSubArrayLen(target, nums));
    }

    public static void main(String[] args) {
        MinimumSizeSubarraySum solver = new MinimumSizeSubarraySum();
        solver.solve(7, new int[]{2,3,1,2,4,3});
        solver.solve(4, new int[]{1,4,4});
        solver.solve(11, new int[]{1,1,1,1,1,1,1,1});
        solver.solve(11, new int[]{});
        solver.solve(11, new int[]{1});
        solver.solve(11, new int[]{11});
        solver.solve(11, new int[]{12});
        solver.solve(11, new int[]{11, 12});
        solver.solve(11, new int[]{1,2,3,4,5});
    }
}
