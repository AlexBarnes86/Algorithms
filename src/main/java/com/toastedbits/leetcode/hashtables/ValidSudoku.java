package com.toastedbits.leetcode.hashtables;

public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] presentCol = new boolean[9][9];
        boolean[][] presentRow = new boolean[9][9];
        boolean[][] presentBox = new boolean[9][9];

        for (int r = 0; r < 9; ++r) {
            for (int c = 0; c < 9; ++c) {
                if(board[r][c] != '.') {
                    int v = board[r][c] - '1';
                    if(presentRow[r][v]) {
                        return false;
                    }
                    presentRow[r][v] = true;

                    if(presentCol[v][c]) {
                        return false;
                    }
                    presentCol[v][c] = true;

                    int box = (c / 3) + (r / 3) * 3;
                    int cell = c % 3 + (r%3) * 3;
                    if(presentBox[box][v]) {
                        return false;
                    }
                    presentBox[box][v] = true;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        ValidSudoku solver = new ValidSudoku();
        System.out.println(solver.isValidSudoku(
            new char[][]
            {{'5','3','.','.','7','.','.','.','.'}
            ,{'6','.','.','1','9','5','.','.','.'}
            ,{'.','9','8','.','.','.','.','6','.'}
            ,{'8','.','.','.','6','.','.','.','3'}
            ,{'4','.','.','8','.','3','.','.','1'}
            ,{'7','.','.','.','2','.','.','.','6'}
            ,{'.','6','.','.','.','.','2','8','.'}
            ,{'.','.','.','4','1','9','.','.','5'}
            ,{'.','.','.','.','8','.','.','7','9'}})
        );

        System.out.println(solver.isValidSudoku(
            new char[][]
            {{'8','3','.','.','7','.','.','.','.'}
            ,{'6','.','.','1','9','5','.','.','.'}
            ,{'.','9','8','.','.','.','.','6','.'}
            ,{'8','.','.','.','6','.','.','.','3'}
            ,{'4','.','.','8','.','3','.','.','1'}
            ,{'7','.','.','.','2','.','.','.','6'}
            ,{'.','6','.','.','.','.','2','8','.'}
            ,{'.','.','.','4','1','9','.','.','5'}
            ,{'.','.','.','.','8','.','.','7','9'}})
        );
    }
}
