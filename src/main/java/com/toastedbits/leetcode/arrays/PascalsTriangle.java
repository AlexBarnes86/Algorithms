package com.toastedbits.leetcode.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PascalsTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Collections.singletonList(1));
        for(int i = 1; i < numRows; ++i) {
            List<Integer> row = new ArrayList<>();
            for(int j = 0; j <= i; ++j) {
                int left = j == 0 ? 0 : triangle.get(i-1).get(j-1);
                int right = j == i ? 0 : triangle.get(i-1).get(j);
                row.add(left+right);
            }
            triangle.add(row);
        }
        return triangle;
    }

    public static void main(String[] args) {
        PascalsTriangle pt = new PascalsTriangle();
        for(int i = 1; i <= 30; ++i) {
            System.out.println(i + ": " + pt.generate(i));
        }
    }
}
