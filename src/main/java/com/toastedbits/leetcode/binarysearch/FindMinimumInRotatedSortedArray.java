package com.toastedbits.leetcode.binarysearch;

import com.toastedbits.leetcode.utils.ArrayUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FindMinimumInRotatedSortedArray {
    public int findRotation(int[] ary, int left, int right) {
        if(left >= right) {
            return -1;
        }

        int mid = left+(right-left)/2;
        if(mid != 0 && ary[mid] < ary[mid-1]) {
            return mid;
        }
        else if(mid != ary.length-1 && ary[mid] > ary[mid+1]) {
            return (mid+1);
        }
        else if(ary[left] > ary[mid]) {
            return findRotation(ary, left, mid);
        }
        else if(ary[right] < ary[mid]) {
            return findRotation(ary, mid, right);
        }
        return -1;
    }

    public int findRotation(int[] ary) {
        if(ary == null || ary.length == 0) {
            return -1;
        }
        if(ary.length == 1 || ary[0] < ary[ary.length-1]) {
            return 0;
        }
        return findRotation(ary, 0, ary.length-1);
    }

    public int findMin(int[] nums) {
        return nums[findRotation(nums)];
    }

    public void test() {
        Random rand = new Random();
        for(int i = 0; i < 1000; ++i) {
            List<Integer> list = IntStream.range(-3, 3).boxed().collect(Collectors.toList());
            int randomRot = rand.nextInt(40);
            int[] ary = ArrayUtils.toArray(list);
            System.out.print(list);
            ArrayUtils.inPlaceRotateLeft(list, randomRot);
            ArrayUtils.inPlaceRotateLeft(ary, randomRot);
            System.out.print(" -> ");
            System.out.print(list);
            int rot = randomRot % list.size();
            int foundRot = findRotation(ary);
            int minIdx = findMin(ary);
            System.out.println(", random rot: " + rot + ", foundRot: " + foundRot + " -> " + (rot == foundRot) + ", minIdx: " + minIdx + ", " + (minIdx == ArrayUtils.linearSearch(ary, -3)));
        }
    }

    public static void main(String[] args) {
        FindMinimumInRotatedSortedArray solver = new FindMinimumInRotatedSortedArray();
        for(int i = -10; i <= 10; ++i) {
            int[] ary = new int[]{0, 1, 2, 3, 4, 5, 6, 7};
            ArrayUtils.inPlaceRotateLeft(ary, i);
            System.out.println(Arrays.toString(ary));
            System.out.println(solver.findMin(ary));
        }
        System.out.println(solver.findMin(new int[]{}));
        System.out.println(solver.findMin(new int[]{0}));
        System.out.println(solver.findMin(new int[]{0,1}));
    }
}
