package com.toastedbits.leetcode.arrays;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayPartitionOne {
    public int arrayPairSum(int[] nums) {
        List<Integer> list = Arrays.stream(nums).boxed().sorted().collect(Collectors.toList());
        int total = 0;
        for(int i = 0; i < list.size(); i+=2) {
            total += Math.min(list.get(i), list.get(i+1));
        }
        return total;
    }

    private void solve(int[] ary) {
        System.out.println(arrayPairSum(ary));
    }

    public static void main(String[] args) {
        ArrayPartitionOne solver = new ArrayPartitionOne();
        solver.solve(new int[]{});
        solver.solve(new int[]{1,2});
        solver.solve(new int[]{4,3,2,1});
        solver.solve(new int[]{6,2,6,5,1,2});
    }

}
