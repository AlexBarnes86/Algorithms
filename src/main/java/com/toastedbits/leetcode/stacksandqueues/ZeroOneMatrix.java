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
            this.c =c;
        }
    }

    void fill(int[][] mat, int val) {
        for(int r = 0; r < mat.length; ++r) {
            for(int c = 0; c < mat[r].length; ++c) {
                mat[r][c] = val;
            }
        }
    }

    public int[][] updateMatrix(int[][] mat) {
        int[][] result = new int[mat.length][mat[0].length];
        fill(result, -1);
        Queue<Cell> queue = new LinkedList<>();
        for(int r = 0; r < mat.length; ++r) {
            for(int c = 0; c < mat[r].length; ++c) {
                if(mat[r][c] == 0) {
                    result[r][c] = 0;
                    queue.offer(new Cell(r, c));
                }
            }
        }

        int depth = 0;
        while(!queue.isEmpty()) {
            int levelSize = queue.size();
            for(int i = 0; i < levelSize; ++i) {
                Cell cur = queue.poll();
                result[cur.r][cur.c] = depth;
                if(cur.r - 1 >= 0 && result[cur.r - 1][cur.c] == -1) {
                    queue.offer(new Cell(cur.r - 1, cur.c));
                    result[cur.r-1][cur.c] = -2;
                }
                if(cur.r + 1 < mat.length && result[cur.r + 1][cur.c] == -1) {
                    queue.offer(new Cell(cur.r + 1, cur.c));
                    result[cur.r+1][cur.c] = -2;
                }
                if(cur.c - 1 >= 0 && result[cur.r][cur.c - 1] == -1) {
                    queue.offer(new Cell(cur.r, cur.c - 1));
                    result[cur.r][cur.c-1] = -2;
                }
                if(cur.c + 1 < mat[cur.r].length && result[cur.r][cur.c + 1] == -1) {
                    queue.offer(new Cell(cur.r, cur.c + 1));
                    result[cur.r][cur.c+1] = -2;
                }
            }
            depth++;
        }

        return result;
    }

    public static void main(String[] args) {
        ZeroOneMatrix solver = new ZeroOneMatrix();
        ArrayUtil.println(solver.updateMatrix(new int[][]{{0,0,0},{0,1,0},{0,0,0}}));
        System.out.println();

        ArrayUtil.println(solver.updateMatrix(new int[][]{{0,0,0},{0,1,0},{1,1,1}}));
        System.out.println();

        ArrayUtil.println(solver.updateMatrix(new int[][]{{0},{0},{0},{0},{0}}));
        System.out.println();
    }
}
