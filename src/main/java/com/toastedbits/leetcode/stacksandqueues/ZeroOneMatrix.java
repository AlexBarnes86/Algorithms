package com.toastedbits.leetcode.stacksandqueues;

import com.toastedbits.leetcode.arrays.ArrayUtil;

import java.util.LinkedList;
import java.util.Queue;

public class ZeroOneMatrix {
    static class Cell {
        int r;
        int c;
        public Cell(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public int zeroDistance(int[][] mat, int r, int c) {
        Queue<Cell> queue = new LinkedList<>();
        queue.offer(new Cell(r,c));
        int depth = 0;
        while(!queue.isEmpty()) {
            int levelSize = queue.size();
            for(int i = 0; i < levelSize; ++i) {
                Cell cur = queue.poll();
                if(mat[cur.r][cur.c] == 0) {
                    return depth;
                }

                if(cur.r - 1 >= 0) {
                    queue.offer(new Cell(cur.r - 1, cur.c));
                }
                if(cur.r + 1 < mat.length) {
                    queue.offer(new Cell(cur.r + 1, cur.c));
                }
                if(cur.c - 1 >= 0) {
                    queue.offer(new Cell(cur.r, cur.c - 1));
                }
                if(cur.c + 1 < mat[cur.r].length) {
                    queue.offer(new Cell(cur.r, cur.c + 1));
                }
            }
            depth++;
        }

        throw new IllegalArgumentException("Must provide a matrix with at least one 0");
    }

    public int[][] updateMatrix(int[][] mat) {
        int[][] result = new int[mat.length][mat[0].length];
        for(int r = 0; r < mat.length; ++r) {
            for(int c = 0; c < mat[r].length; ++c) {
                result[r][c] = zeroDistance(mat, r, c);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ZeroOneMatrix solver = new ZeroOneMatrix();
        ArrayUtil.println(solver.updateMatrix(new int[][]{{0,0,0},{0,1,0},{0,0,0}}));
        System.out.println();

        ArrayUtil.println(solver.updateMatrix(new int[][]{{0,0,0},{0,1,0},{1,1,1}}));
        System.out.println();
    }
}
