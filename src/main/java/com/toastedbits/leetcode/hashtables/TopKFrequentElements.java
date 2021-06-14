package com.toastedbits.leetcode.hashtables;

import com.toastedbits.leetcode.arrays.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Long> freqMap = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Map<Long, List<Integer>> histogram = new HashMap<>();
        freqMap.forEach((num, ct) ->
            histogram.compute(ct, (freq, vals) -> {
                if(vals == null) {
                    vals = new ArrayList<>();
                }
                vals.add(num);
                return vals;
            } )
        );
        List<Long> keys = new ArrayList<>(histogram.keySet());
        Collections.sort(keys);
        List<Integer> result = new ArrayList<>();
        for(int i = keys.size()-1; i >= 0; i--) {
            for(int num : histogram.get(keys.get(i))) {
                result.add(num);
                if(result.size() == k) {
                    return toArray(result);
                }
            }
        }
        return toArray(result);
    }

    private int[] toArray(List<Integer> list) {
        int[] ary = new int[list.size()];
        for(int i = 0; i < list.size(); ++i) {
            ary[i] = list.get(i);
        }
        return ary;
    }

    public static void main(String[] args) {
        TopKFrequentElements solver = new TopKFrequentElements();
        ArrayUtil.println(solver.topKFrequent(new int[]{1,1,1,2,2,3}, 2));
    }
}
