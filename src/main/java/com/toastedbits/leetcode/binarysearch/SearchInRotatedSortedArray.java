package com.toastedbits.leetcode.binarysearch;

import com.toastedbits.leetcode.utils.ArrayUtils;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SearchInRotatedSortedArray {
    public int findRotation(int[] ary, int left, int right) {
        if(left >= right) {
            return -1;
        }

        int mid = (left+right)/2;
        if(mid != 0 && ary[mid] < ary[mid-1]) {
            return ary.length-mid;
        }
        else if(mid != ary.length-1 && ary[mid] > ary[mid+1]) {
            return ary.length - (mid+1);
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
        if(ary == null || ary.length == 1 || ary[0] < ary[ary.length-1]) {
            return 0;
        }
        return findRotation(ary, 0, ary.length-1);
    }

    public int binarySearch(int[] ary, int left, int right, int target) {
        while (left <= right) {
            int mid = (right + left) / 2;
            if (ary[mid] == target)
                return mid;
            else if (ary[mid] > target)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return -1;
    }

    public int findRotatedTarget(int[] ary, int rot, int target) {
        int res = binarySearch(ary, 0, ary.length-rot-1, target);
        if(res != -1) {
            return res;
        }
        return binarySearch(ary, ary.length-rot, ary.length-1, target);
    }

    public int search(int[] nums, int target) {
        return findRotatedTarget(nums, findRotation(nums), target);
    }

    public static void main(String[] args) {
        SearchInRotatedSortedArray solver = new SearchInRotatedSortedArray();
        Random rand = new Random();
        int target = 7;
        for(int i = 0; i < 1000; ++i) {
            List<Integer> list = IntStream.range(-10, 10).boxed().collect(Collectors.toList());
            int randomRot = rand.nextInt(40);
            int[] ary = ArrayUtils.toArray(list);
            System.out.print(list);
            ArrayUtils.inPlaceRotateLeft(list, randomRot);
            ArrayUtils.inPlaceRotateLeft(ary, randomRot);
            System.out.print(" -> ");
            System.out.print(list);
            int rot = randomRot % list.size();
            int foundRot = solver.findRotation(ary);
            int idx = solver.search(ary, target);
            System.out.println(", random rot: " + rot + ", foundRot: " + foundRot + " -> " + (rot == foundRot) + ", targetIdx: " + idx + ", " + (idx == ArrayUtils.linearSearch(ary, target)));
        }
    }
}
