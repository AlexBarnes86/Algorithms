package com.toastedbits.leetcode.arrays;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        int width = matrix[0].length, height = matrix.length;
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new ArrayList<>();
        }

        List<Integer> traverse = new ArrayList<>();
        int i = 0;//, max = 1000;
        int r = 0, c = 0;
        int leftBound = 0, rightBound = width - 1;
        int topBound = 0, bottomBound = height - 1;
        boolean moved = false;

        while(rightBound >= leftBound && topBound <= bottomBound) {
//            if (i > max) {
//                break;
//            }

            while (c <= rightBound) {
//                System.out.println("row: " + r + ", col: " + c);
                traverse.add(matrix[r][c]);
                c++;
                moved = true;
                if(++i == width*height) {
                    return traverse;
                }
            }
            if (moved) {
                c--;
                r++;
                topBound++;
            }

            moved = false;
            while(r <= bottomBound) {
//                System.out.println("row: " + r + ", col: " + c);
                traverse.add(matrix[r][c]);
                r++;
                moved = true;
                if(++i == width*height) {
                    return traverse;
                }
            }
            if(moved) {
                r--;
                c--;
                rightBound--;
            }

            moved = false;
            while(c >= leftBound) {
//                System.out.println("row: " + r + ", col: " + c);
                traverse.add(matrix[r][c]);
                c--;
                moved = true;
                if(++i == width*height) {
                    return traverse;
                }
            }
            if(moved) {
                c++;
                r--;
                bottomBound--;
            }

            moved = false;
            while(r >= topBound) {
                System.out.println("row: " + r + ", col: " + c);
                traverse.add(matrix[r][c]);
                r--;
                moved = true;
                if(++i == width*height) {
                    return traverse;
                }
            }
            if(moved) {
                r++;
                c++;
                leftBound++;
            }
        }

        return traverse;
    }

    private void solve(int[][] matrix) {
        System.out.println(spiralOrder(matrix));
    }

    public static void main(String[] args) {
        SpiralMatrix solver = new SpiralMatrix();
        solver.solve(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
        solver.solve(new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}});
        solver.solve(new int[][]{
                {0,1,2,3,4,5,6,7,8,9},
                {10,11,12,13,14,15,16,17,18,19},
                {20,21,22,23,24,25,26,27,28,29},
                {30,31,32,33,34,35,36,37,38,39},
                {40,41,42,43,44,45,46,47,48,49},
                {50,51,52,53,54,55,56,57,58,59},
        });
    }
}
