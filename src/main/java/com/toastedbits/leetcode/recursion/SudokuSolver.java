package com.toastedbits.leetcode.recursion;

import com.toastedbits.leetcode.utils.ArrayUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class SudokuSolver {
    public boolean isValid(char[][] board, int row, int col) {
        boolean [] exists = new boolean[board[0].length];
        //horizontal
        for(int c = 0; c < board[0].length; ++c) {
            if(board[row][c] != '.') {
                if(exists[board[row][c]-49]) {
                    return false;
                }
                else {
                    exists[board[row][c]-49] = true;
                }
            }
        }

        //horizontal
        exists = new boolean[board.length];
        for(int r = 0; r < board.length; ++r) {
            if(board[r][col] != '.') {
                if(exists[board[r][col]-49]) {
                    return false;
                }
                else {
                    exists[board[r][col]-49] = true;
                }
            }
        }

        //submatrix
        exists = new boolean[board.length];
        int snapRow = row - row % 3;
        int snapCol = col - col % 3;
        for(int r = snapRow; r < snapRow + 3; ++r) {
            for(int c = snapCol; c < snapCol + 3; ++c) {
                if(board[r][c] != '.') {
                    if(exists[board[r][c] - 49]) {
                        return false;
                    }
                    else {
                        exists[board[r][c] - 49] = true;
                    }
                }
            }
        }

        return true;
    }

    private Set<Character> getPossibilities(char[][] board, int row, int col) {
        Set<Character> set = new HashSet<>();
        for(char c = '1'; c <= '9'; ++c) {
            set.add(c);
        }

        //row
        for(int c = 0; c < board[0].length; ++c) {
            set.remove(board[row][c]);
        }

        //col
        for(int r = 0; r < board.length; ++r) {
            set.remove(board[r][col]);
        }

        //cell
        row = row - (row%3);
        col = col - (col%3);
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                set.remove(board[row+i][col+j]);
            }
        }

        return set;
    }

    private char[][] clone(char[][] mat) {
        char[][] clone = new char[mat.length][mat[0].length];
        for(int r = 0; r < mat.length; ++r) {
            for(int c = 0; c < mat[0].length; ++c) {
                clone[r][c] = mat[r][c];
            }
        }
        return clone;
    }

    private char[][] soln;
    private void dfs(char[][] board, Stack<Character>[][] possibilities, int row, int col) {
        if(row >= board.length || col >= board[0].length || soln != null) {
            return;
        }

        if(board[row][col] == '.') {
            Stack<Character> saved = new Stack<>();
            saved.addAll(possibilities[row][col]);
            while(!possibilities[row][col].isEmpty()) {
                char next = possibilities[row][col].pop();
                board[row][col] = next;
                if (isValid(board, row, col)) {
                    if(row == 8 && col == 8) {
//                        System.out.println("Found it 1");
                        soln = clone(board);
                        return;
                    } else {
                        dfs(board, possibilities, col == 8 ? row + 1 : row, col == 8 ? 0 : col + 1);
                    }
                }
            }
            board[row][col] = '.';
            possibilities[row][col].addAll(saved);
        }
        else {
            if(row == 8 && col == 8 && isValid(board, row, col)) {
//                System.out.println("Found it 2");
                soln = clone(board);
                return;
            } else {
                dfs(board, possibilities, col == 8 ? row + 1 : row, col == 8 ? 0 : col + 1);
            }
        }
    }

    public void solveSudoku(char[][] board) {
        soln = null;
        Stack<Character>[][] poss = new Stack[board.length][board[0].length];
        for (int r = 0; r < board.length; ++r) {
            for (int c = 0; c < board[0].length; ++c) {
                poss[r][c] = new Stack<>();
                getPossibilities(board, r, c).forEach(poss[r][c]::push);
            }
        }

        dfs(board, poss, 0, 0);

        for (int r = 0; r < board.length; ++r) {
            for (int c = 0; c < board[0].length; ++c) {
                board[r][c] = soln[r][c];
            }
        }
    }

    public void test1() {
        System.out.println("== Test 1 ==");
        char[][] board = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
        System.out.println("Orig:");
        ArrayUtils.println(board);
//        for(int r = 0; r < board.length; ++r) {
//            for(int c = 0; c < board[0].length; ++c) {
//                if(board[r][c] == '.') {
//                    System.out.println("r: " + r + ", c: " + c + " -> " + getPossibilities(board, r, c));
//                }
//                else {
//                    System.out.println("r: " + r + ", c: " + c + " -> " + board[r][c]);
//                }
//            }
//        }
        solveSudoku(board);
        System.out.println("Solved:");
        ArrayUtils.println(board);
    }

    public void test2() {
        System.out.println("== Test 2 ==");
        char[][] board = new char[][]{
                {'.','.','9','7','4','8','.','.','.'},
                {'7','.','.','.','.','.','.','.','.'},
                {'.','2','.','1','.','9','.','.','.'},
                {'.','.','7','.','.','.','2','4','.'},
                {'.','6','4','.','1','.','5','9','.'},
                {'.','9','8','.','.','.','3','.','.'},
                {'.','.','.','8','.','3','.','2','.'},
                {'.','.','.','.','.','.','.','.','6'},
                {'.','.','.','2','7','5','9','.','.'}
        };
        System.out.println("Orig:");
        ArrayUtils.println(board);
//        for(int r = 0; r < board.length; ++r) {
//            for(int c = 0; c < board[0].length; ++c) {
//                if(board[r][c] == '.') {
//                    System.out.println("r: " + r + ", c: " + c + " -> " + getPossibilities(board, r, c));
//                }
//                else {
//                    System.out.println("r: " + r + ", c: " + c + " -> " + board[r][c]);
//                }
//            }
//        }
        solveSudoku(board);
        System.out.println("Solved:");
        ArrayUtils.println(board);
    }

    public static void main(String[] args) {
        SudokuSolver solver = new SudokuSolver();
        solver.test1();
        solver.test2();
    }
}
