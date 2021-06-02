package com.toastedbits.leetcode.arrays;

public class LargestNumberAtLeastTwiceOfOthers {
    public int dominantIndex(int[] nums) {
        int max = 0, second = 0;
        int idx = 0;
        for(int i = 0; i < nums.length; ++i) {
            if(nums[i] > max) {
                second = max;
                max = nums[i];
                idx = i;
            }
            else if(nums[i] > second) {
                second = nums[i];
            }
        }

        return max >= (2 * second) ? idx : -1;
    }

    private void solve(int[] ary) {
        System.out.println(dominantIndex(ary));
    }

    public static void main(String[] args) {
        LargestNumberAtLeastTwiceOfOthers solver = new LargestNumberAtLeastTwiceOfOthers();
        solver.solve(new int[]{3,6,1,0});
        solver.solve(new int[]{1,2,3,4});
        solver.solve(new int[]{1});
        solver.solve(new int[]{0,0,3,2});
    }
}
