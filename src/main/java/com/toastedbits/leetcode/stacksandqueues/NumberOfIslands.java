package com.toastedbits.leetcode.stacksandqueues;

public class NumberOfIslands {
    private void visit(char[][] grid, int r, int c) {
        if(r < grid.length && r >= 0 && c >= 0 && c < grid[r].length && grid[r][c] == '1') {
            grid[r][c] = '0';
            visit(grid, r-1, c);
            visit(grid, r+1, c);
            visit(grid, r, c-1);
            visit(grid, r, c+1);
        }
    }

    public int numIslands(char[][] grid) {
        int ct = 0;
        for(int r = 0; r < grid.length; ++r) {
            for(int c = 0; c < grid[r].length; ++c) {
                if(grid[r][c] == '0') {
                    continue;
                }
                ct++;
                visit(grid, r, c);
            }
        }
        return ct;
    }

    public static void main(String[] args) {
        char[][] grid = new char[][] {
            {'1', '1', '1', '1', '0'},
            {'1', '1', '0', '1', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '0', '0', '0'}
        };
        NumberOfIslands solver = new NumberOfIslands();
        System.out.println(solver.numIslands(grid));

        grid = new char[][] {
            {'1','1','0','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'}
        };
        System.out.println(solver.numIslands(grid));
    }
}
