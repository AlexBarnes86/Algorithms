package com.toastedbits.leetcode.arrays;

public class DiagonalTraverse {
    enum Direction {
        UP,
        DOWN
    }

    public int[] findDiagonalOrder(int[][] mat) {
        if (mat.length == 0 || mat[0].length == 0) {
            return new int[]{};
        }
        int height = mat.length;
        int width = mat[0].length;

        int[] traversal = new int[height * width];
        int r = 0, c = 0;
        Direction dir = Direction.UP;
        for(int i = 0; i < height*width; ++i) {
            traversal[i] = mat[r][c];
            if(dir == Direction.UP) {
                r--;
                c++;
                if(c >= width) {
                    r += 2;
                    c = width - 1;
                    dir = Direction.DOWN;
                }
                else if(r < 0) {
                    r = 0;
                    dir = Direction.DOWN;
                }
            }
            else if(dir == Direction.DOWN) {
                r++;
                c--;
                if(r >= height) {
                    c += 2;
                    r = height - 1;
                    dir = Direction.UP;
                }
                else if(c < 0) {
                    c = 0;
                    dir = Direction.UP;
                }
            }
        }

        return traversal;
    }

    private void solve(int[][] mat) {
        ArrayUtil.println(findDiagonalOrder(mat));
    }

    public static void main(String[] args) {
        DiagonalTraverse dt = new DiagonalTraverse();
        dt.solve(new int[][]{{1,2,3}, {4,5,6}, {7,8,9}});
        dt.solve(new int[][]{{1,2}, {4,5}});
        dt.solve(new int[][]{{1,2}, {3,4}, {5,6}});
        dt.solve(new int[][]{{0}});
        dt.solve(new int[][]{});
    }
}
