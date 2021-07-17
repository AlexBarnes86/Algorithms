package com.toastedbits.leetcode.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Permutations {
    private void swap(List<Integer> list, int a, int b) {
        int temp = list.get(a);
        list.set(a, list.get(b));
        list.set(b, temp);
    }

    //https://en.wikipedia.org/wiki/Heap%27s_algorithm
    public List<List<Integer>> heapsPermutation(List<Integer> list) {
        List<List<Integer>> result = new ArrayList<>();
        int[] state = new int[list.size()];
        result.add(new ArrayList<>(list));
        int i = 1;
        while(i < list.size()) {
            if(state[i] < i) {
                if(i % 2 == 0) {
                    swap(list, 0, i);
                }
                else {
                    swap(list, state[i], i);
                }
                result.add(new ArrayList<>(list));
                state[i]++;
                i = 1;
            }
            else {
                state[i] = 0;
                i++;
            }
        }
        return result;
    }

    public List<List<Integer>> permute(int[] nums) {
        return heapsPermutation(Arrays.stream(nums).boxed().collect(Collectors.toList()));
    }

    public static void main(String[] args) {
        Permutations solver = new Permutations();
        System.out.println(solver.permute(new int[]{1,2,3}));
    }
}
