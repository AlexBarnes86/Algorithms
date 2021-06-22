package com.toastedbits.leetcode.stacksandqueues;

import com.toastedbits.leetcode.arrays.ArrayUtil;

import java.util.Stack;

public class FloodFill {
    static class Cell {
        int r;
        int c;
        public Cell(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if(sr < 0 || sc < 0 || sr >= image.length || sc > image[sr].length) {
            return image;
        }

        Stack<Cell> stack = new Stack<>();
        stack.push(new Cell(sr, sc));
        int oldColor = image[sr][sc];
        if(oldColor == newColor) {
            return image;
        }

        while(!stack.isEmpty()) {
            Cell cur = stack.pop();
            image[cur.r][cur.c] = newColor;
            if(cur.r - 1 >= 0 && image[cur.r - 1][cur.c] == oldColor) {
                stack.push(new Cell(cur.r - 1, cur.c));
            }
            if(cur.r + 1 < image.length && image[cur.r + 1][cur.c] == oldColor) {
                stack.push(new Cell(cur.r + 1, cur.c));
            }
            if(cur.c - 1 >= 0 && image[cur.r][cur.c - 1] == oldColor) {
                stack.push(new Cell(cur.r, cur.c - 1));
            }
            if(cur.c + 1 < image[cur.r].length && image[cur.r][cur.c + 1] == oldColor) {
                stack.push(new Cell(cur.r, cur.c + 1));
            }
        }

        return image;
    }

    public static void main(String[] args) {
        FloodFill solver = new FloodFill();
        int [][] image = new int[][] {{0,0,0}, {0,0,0}};
        solver.floodFill(image, 0, 0, 2);
        ArrayUtil.println(image);
        System.out.println();

        image = new int[][] {{1,1,1},{1,1,0},{1,0,1}};
        solver.floodFill(image, 1, 1, 2);
        ArrayUtil.println(image);
        System.out.println();

        image = new int[][] {{0,0,0},{0,1,1}};
        solver.floodFill(image, 1, 1, 1);
        ArrayUtil.println(image);
        System.out.println();
    }
}
