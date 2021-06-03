package com.toastedbits.leetcode.arrays;

//TODO: Non-optimal space complexity, can be done in O(1) with O(N) time complexity
public class RotateArray {
    public void rotate(int[] nums) {
        int temp = nums[nums.length-1];
        for(int i = nums.length - 1; i > 0; --i) {
            nums[i] = nums[i-1];
        }
        nums[0] = temp;
    }

    public void rotate(int[] nums, int k) {
        if(nums == null || nums.length < 2) {
            return;
        }

        int[] copy = new int[nums.length];
        System.arraycopy(nums, 0, copy, 0, nums.length);
        k = (k % nums.length) + nums.length;
        for(int i = 0; i < nums.length; ++i) {
            nums[i] = copy[(nums.length + (i-k)%nums.length) % nums.length];
        }
    }

    private void solve(int[] nums, int k) {
        rotate(nums, k);
        ArrayUtil.println(nums);
    }

    public static void main(String[] args) {
        RotateArray solver = new RotateArray();
        solver.solve(new int[]{1,2,3,4,5,6,7}, 3);
        solver.solve(new int[]{-1,-100,3,99}, 2);
        solver.solve(new int[]{1}, 2);
        solver.solve(new int[]{}, 2);
        solver.solve(new int[]{1,2}, 2);
        solver.solve(new int[]{1,2}, 5001);
    }
}
