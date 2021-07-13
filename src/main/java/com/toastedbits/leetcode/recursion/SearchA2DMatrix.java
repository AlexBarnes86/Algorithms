package com.toastedbits.leetcode.recursion;

import java.util.Arrays;

public class SearchA2DMatrix {
    private boolean searchMatrix(int[][] matrix, int top, int bottom, int left, int right, int target) {
        if(top >= bottom || left >= right) {
//                || top < 0 || top > matrix.length
//                || bottom < 0 || bottom > matrix.length
//                || left < 0 || left > matrix[0].length
//                || right < 0 || right > matrix[0].length) {
            return false;
        }

        int midRow = top + (bottom - top) / 2;

//        System.out.println("Search row: " + midRow + " from " + left + " to " + right);
        int midCol = Arrays.binarySearch(matrix[midRow], left, right, target);
        if(midCol >= 0) {
            return true;
        }
        midCol = -(midCol+1);

        if(midCol >= matrix[0].length) {
            return searchMatrix(matrix, top, midRow, left, right, target) || searchMatrix(matrix, midRow+1, bottom, left, right, target);
        }
        else if(matrix[midRow][midCol] > target) {
            return searchMatrix(matrix, midRow+1, bottom, left, midCol+1, target) || //bottom left
                    searchMatrix(matrix, top, midRow, left, midCol+1, target) || //top left
                    searchMatrix(matrix, top, midRow, midCol+1, right, target); //top right
        }
        else {
            return searchMatrix(matrix, midRow + 1, bottom, left, midCol + 1, target) || //bottom left
                    searchMatrix(matrix, midRow, bottom, midCol + 1, right, target) || //bottom right
                    searchMatrix(matrix, top, midRow + 1, midCol + 1, right, target); //top right
        }
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        return searchMatrix(matrix, 0, matrix.length, 0, matrix[0].length, target);
    }

    public static void main(String[] args) {
        SearchA2DMatrix solver = new SearchA2DMatrix();
        System.out.println(solver.searchMatrix(new int[][]{{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}}, 5));
        System.out.println(solver.searchMatrix(new int[][]{{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}}, 20));
        System.out.println(solver.searchMatrix(new int[][]{{1},{3},{5}}, 5));
    }
}
