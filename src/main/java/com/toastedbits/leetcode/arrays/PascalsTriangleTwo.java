package com.toastedbits.leetcode.arrays;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangleTwo {
    private static final int MAX_ROW = 33;
    private static final int C[][] = new int[MAX_ROW + 1][MAX_ROW + 1];

    static {
        binomialCoefficient(MAX_ROW, MAX_ROW);
    }

    static int binomialCoefficient(int n, int k) {
        int i, j;

        for (i = 0; i <= n; i++) {
            for (j = 0; j <= Math.min(i, k); j++) {
                if (j == 0 || j == i) {
                    C[i][j] = 1;
                }
                else {
                    C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
                }
            }
        }

        return C[n][k];
    }

    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        for(int i = 0; i <= rowIndex; ++i) {
            row.add(C[rowIndex][i]);
        }
        return row;
    }

    public static void main(String[] args) {
        PascalsTriangleTwo pt = new PascalsTriangleTwo();
        for(int i = 0; i <= 33; ++i) {
            System.out.println(i + ": " + pt.getRow(i));
        }
    }
}
