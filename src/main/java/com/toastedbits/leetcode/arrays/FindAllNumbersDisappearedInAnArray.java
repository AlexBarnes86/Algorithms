package com.toastedbits.leetcode.arrays;

import java.util.ArrayList;
import java.util.List;

public class FindAllNumbersDisappearedInAnArray {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        boolean[] present = new boolean[nums.length+1];
        for (int num : nums) {
            present[num] = true;
        }
        List<Integer> notPresent = new ArrayList<>();
        for(int i = 1; i < present.length; ++i) {
            if(!present[i]) {
                notPresent.add(i);
            }
        }
        return notPresent;
    }

    private void printArray(int[] ary) {
        printArray(ary, ary.length);
    }

    private void printArray(int[] ary, int len) {
        System.out.print("len: " + len + ", ary: ");
        for(int i = 0; i < len; ++i) {
            System.out.print(ary[i]);
            if(i != len-1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    private void solve(int[] ary) {
        System.out.println(findDisappearedNumbers(ary));
    }

    public static void main(String[] args) {
        FindAllNumbersDisappearedInAnArray solver = new FindAllNumbersDisappearedInAnArray();
        solver.solve(new int[]{4,3,2,7,8,2,3,1});
        solver.solve(new int[]{});
        solver.solve(new int[]{2,2,3,1});
        solver.solve(new int[]{1,2,3,4,5,6});
    }
}
