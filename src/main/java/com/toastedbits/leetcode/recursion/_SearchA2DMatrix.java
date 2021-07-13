package com.toastedbits.leetcode.recursion;

import java.util.Arrays;

public class _SearchA2DMatrix {
    private int firstBinarySearch(int[] ary, int left, int right, int target) {
        int next = Arrays.binarySearch(ary, left, right, target);
        int idx = next;
        while(next >= 0) {
            idx = next;
            next = Arrays.binarySearch(ary, left, next, target);
        }
        return idx;
    }

    private boolean searchMatrix(int[][] matrix, int top, int bottom, int left, int right, int target) {
        if(top > bottom || left >= right) {
            return false;
        }

        int midRow = top + (bottom - top) / 2;
        int midCol = firstBinarySearch(matrix[midRow], left, right, target);
        if(midCol >= 0) {
            return true;
        }
        midCol = -(midCol+1);

        if(matrix[midRow][midCol] < target) {
            return searchMatrix(matrix, top, midRow, midCol+1, right, target) ||
                    searchMatrix(matrix, midRow, bottom, midCol+1, right, target) ||
                    searchMatrix(matrix, midRow, bottom, left+1, midCol, target);
        }
        return searchMatrix(matrix, midRow, bottom, left+1, midCol, target) ||
                searchMatrix(matrix, top, midRow, left+1, midCol, target) ||
                searchMatrix(matrix, top, midRow, midCol+1, right, target);
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        return searchMatrix(matrix, 0, matrix.length, 0, matrix[0].length, target);
    }

    public static void main(String[] args) {
        _SearchA2DMatrix solver = new _SearchA2DMatrix();
        System.out.println(solver.searchMatrix(new int[][]{{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}}, 5));
    }


}
