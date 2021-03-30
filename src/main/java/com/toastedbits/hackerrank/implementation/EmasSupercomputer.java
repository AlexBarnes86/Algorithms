package com.toastedbits.hackerrank.implementation;

//import org.junit.Test;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

//Greedy solution did not work, solved with brute force
public class EmasSupercomputer {

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new PrintWriter(System.out));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        String[] grid = new String[n];

        for (int i = 0; i < n; i++) {
            String gridItem = scanner.nextLine();
            grid[i] = gridItem;
        }

        int result = twoPluses(grid);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }

    private static int twoPluses(char[][] grid) {
        int maxArea = 0;
        for(int y = 0; y < grid.length; ++y) {
            for(int x = 0; x < grid[y].length; ++x) {
                log("== maxArea so far: " + maxArea + "==");
                for(int armLen = 0; armLen < grid.length; ++armLen) {
                    log("-- x: " + x + ", y: " + y + ", armLen: " + armLen + " --");
                    log("grid: \n" + gridToString(grid));
                    char[][] child = cloneGrid(grid);
                    log("child before fillPlus: \n" + gridToString(child));
                    int plus1Area = fillPlus(child, x, y, armLen);
                    log("child after fillPlus: \n" + gridToString(child));
                    if(plus1Area == 0) {
                        break;
                    }
                    log("plus1Area: " + plus1Area);

                    Result plus2Res = findMaxArea(child);
                    log("plus2: " + plus2Res);
                    if(plus1Area * plus2Res.max > maxArea) {
                        maxArea = plus1Area * plus2Res.max;
                    }
                    log("maxArea so far: " + maxArea);
                }
            }
        }
        log("maxArea: " + maxArea);
        return maxArea;
    }

    // Complete the twoPluses function below.
    private static int twoPluses(String[] strings) {
        char[][] grid = toCharGrid(strings);
        return twoPluses(grid);
    }

    private static final Scanner scanner = new Scanner(System.in);

//    @Test
//    public void test() {
//        twoPluses(new String[] {
//            "GGGGGG",
//            "GBBBGB",
//            "GGGGGG",
//            "GGBBGB",
//            "GGGGGG"
//        });
//        twoPluses(new String[] {
//            "BBBBBBBBGBB",
//            "BBBBBBBBGBB",
//            "BBBBBBGGGGG",
//            "BBBBBBBBGBB",
//            "BBBBBBGBGBB",
//            "BBBBGBGBBBB",
//            "BBBBGBGBBBB",
//            "BBBBGBGBBBB",
//            "BGGGGGGGGGG",
//            "BBBBGBGBBBB",
//            "BBBBGBGBBBB",
//            "BBBBGBGBBBB",
//            "BBBBBBGBBBB"
//        });
//        twoPluses(new String[]{
//            "GGGGGGGG",
//            "GBGBGGBG",
//            "GBGBGGBG",
//            "GGGGGGGG",
//            "GBGBGGBG",
//            "GGGGGGGG",
//            "GBGBGGBG",
//            "GGGGGGGG"
//        });
//    }

    private static char[][] toCharGrid(String[] strings) {
        char[][] grid = new char[strings.length][strings[0].length()];
        for(int i = 0; i < strings.length; ++i) {
            for(int j = 0; j < strings[i].length(); ++j) {
                grid[i][j] = strings[i].charAt(j);
            }
        }
        return grid;
    }

    private static int countPlusArea(char[][] grid, int i, int j) {
        int leftTotal = 0, rightTotal = 0, topTotal = 0, bottomTotal = 0;
        int y = i, x = j-1;
        if(grid[i][j] != 'G') {
            return 0;
        }
        while(x >= 0 && grid[i][x] == 'G') {
            leftTotal++;
            x--;
        }
        x = j+1;
        while(x < grid[i].length && grid[i][x] == 'G') {
            rightTotal++;
            x++;
        }
        y = i-1;
        while(y >= 0 && grid[y][j] == 'G') {
            topTotal++;
            y--;
        }
        y = i+1;
        while(y < grid.length && grid[y][j] == 'G') {
            bottomTotal++;
            y++;
        }

        return Math.min(topTotal, Math.min(bottomTotal, Math.min(leftTotal, rightTotal))) * 4 + 1;
    }

    private static int fillPlus(char[][] grid, int x, int y, int armLen) {
        if(grid[y][x] != 'G' || x - armLen < 0 || x + armLen >= grid[y].length || y - armLen < 0 || y + armLen >= grid.length)
            return 0;
        grid[y][x] = 'B';

        for(int i = 1; i <= armLen; ++i) {
            if(grid[y+i][x] != 'G')
                return 0;
            grid[y+i][x] = 'B';

            if(grid[y-i][x] != 'G')
                return 0;
            grid[y-i][x] = 'B';

            if(grid[y][x+i] != 'G')
                return 0;
            grid[y][x+i] = 'B';

            if(grid[y][x-i] != 'G')
                return 0;
            grid[y][x-i] = 'B';
        }

        return armLen * 4 + 1;
    }

    private static void log(String msg) {
        //System.out.println(msg);
    }

    private static Result findMaxArea(char[][] grid) {
        int[][] plusTotals = new int[grid.length][grid[0].length];
        int maxi = 0, maxj = 0, maxArea = 0;
        for(int i = 0; i < grid.length; ++i) {
            for(int j = 0; j < grid[i].length; ++j) {
                plusTotals[i][j] = countPlusArea(grid, i, j);
                if(plusTotals[i][j] > maxArea) {
                    maxArea = plusTotals[i][j];
                    maxi = i;
                    maxj = j;
                }
            }
        }

        return new Result(maxArea, maxj, maxi);
    }

    private static class Result {
        int max;
        int x;
        int y;

        public Result(int max, int x, int y) {
            this.max = max;
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "{max: " + max + ", x: " + x + ", y: " + y + "}";
        }
    }

    private static char[][] cloneGrid(char[][] grid) {
        char [][] clone = new char[grid.length][];
        for(int i = 0; i < grid.length; i++)
        {
            char[] aMatrix = grid[i];
            int   aLength = aMatrix.length;
            clone[i] = new char[aLength];
            System.arraycopy(aMatrix, 0, clone[i], 0, aLength);
        }
        return clone;
    }

    private static String gridToString(char[][] grid) {
        StringBuilder sb = new StringBuilder();
        for(int y = 0; y < grid.length; ++y) {
            sb.append(Arrays.toString(grid[y]) + "\n");
        }
        return sb.toString();
    }
}
