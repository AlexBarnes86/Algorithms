package com.toastedbits.leetcode.recursion;

public class NQueensTwo {
    private static final int PLACE = 1;
    private static final int REMOVE = -1;

    private int[][] adjustQueen(int[][] board, int row, int col, int increment) {
        //increment intended to be PLACE or REMOVE
        //horizontal
        for(int i = 0; i < board[0].length; ++i) {
            board[row][i] += increment;
        }
        //vertical
        for(int i = 0; i < board.length; ++i) {
            if(i == row) continue; //Avoid double counting our current row, col
            board[i][col] += increment;
        }
        //Diagonal, top left to bottom right
        int dr = row-1, dc = col-1;
        while(dr >= 0 && dc >= 0) {
            board[dr][dc] += increment;
            dr--; dc--;
        }
        dr = row+1; dc = col+1;
        while(dr < board.length && dc < board[0].length) {
            board[dr][dc] += increment;
            dr++; dc++;
        }
        //Diagonal, bottom left to top right
        dr = row+1; dc = col-1;
        while(dr < board.length && dc >= 0) {
            board[dr][dc] += increment;
            dr++; dc--;
        }
        dr = row-1; dc = col+1;
        while(dr >= 0 && dc < board[0].length) {
            board[dr][dc] += increment;
            dr--; dc++;
        }
        return board;
    }

    private int dfs(int[][] board, int row, int count) {
        for(int col = 0; col < board[0].length; ++col) {
            if(board[row][col] == 0) {
                if(row == board.length-1) {
                    count++;
                }
                else {
                    adjustQueen(board, row, col, PLACE);
                    count = dfs(board, row + 1, count);
                    adjustQueen(board, row, col, REMOVE);
                }
            }
        }
        return count;
    }

    public int totalNQueens(int n) {
        return dfs(new int[n][n], 0, 0);
    }

    public static void main(String[] args) {

        NQueensTwo solver = new NQueensTwo();
        System.out.println(solver.totalNQueens(3));

        for(int i = 1; i <= 9; ++i) {
            System.out.println(solver.totalNQueens(i));
        }
    }
}
