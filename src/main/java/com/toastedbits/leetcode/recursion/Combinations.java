package com.toastedbits.leetcode.recursion;

import java.util.ArrayList;
import java.util.List;

public class Combinations {
    private List<List<Integer>> combine(int i, int k, int n, List<Integer> combo, List<List<Integer>> combinations) {
        if(combo.size() == k) {
            combinations.add(new ArrayList<>(combo));
            return combinations;
        }

        List<Integer> nextPossible = new ArrayList<>();
        for(int j = i; j <= n; ++j) {
            nextPossible.add(j);
        }

        for(int j = 0; j < nextPossible.size(); ++j) {
            combo.add(nextPossible.get(j));
            combine(i + j + 1, k, n, combo, combinations);
            combo.remove(combo.size()-1);
        }

        return combinations;
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> combinations = new ArrayList<>();
        combine(1, k, n, new ArrayList<>(), combinations);
        return combinations;
    }

    public static void main(String[] args) {
        Combinations solver = new Combinations();
        solver.combine(4,3).forEach(System.out::println);
    }
}
