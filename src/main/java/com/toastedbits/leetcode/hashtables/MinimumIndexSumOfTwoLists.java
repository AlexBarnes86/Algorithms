package com.toastedbits.leetcode.hashtables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinimumIndexSumOfTwoLists {
    public String[] findRestaurant(String[] list1, String[] list2) {
        if(list1 == null || list2 == null) {
            return new String[]{};
        }

        Map<Integer, String> list1Prefs = new HashMap<>();
        Map<String, Integer> list2Prefs = new HashMap<>();
        for(int i = 0; i < list1.length; ++i) {
            list1Prefs.put(i, list1[i]);
        }

        for(int i = 0; i < list2.length; ++i) {
            list2Prefs.put(list2[i], i);
        }

        int min = Integer.MAX_VALUE;
        List<String> best = new ArrayList<>();
        for(Map.Entry<Integer, String> entry : list1Prefs.entrySet()) {
            if(list2Prefs.containsKey(entry.getValue())) {
                int sum = entry.getKey() + list2Prefs.get(entry.getValue());
                if(sum < min) {
                    min = sum;
                    best = new ArrayList<>();
                    best.add(entry.getValue());
                }
                else if(sum == min) {
                    best.add(entry.getValue());
                }
            }
        }

        String[] result = new String[best.size()];
        for(int i = 0; i < best.size(); ++i) {
            result[i] = best.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        MinimumIndexSumOfTwoLists solver = new MinimumIndexSumOfTwoLists();
        System.out.println(Arrays.asList(solver.findRestaurant(
            new String[]{"Shogun","Tapioca Express","Burger King","KFC"},
            new String[]{"Piatti","The Grill at Torrey Pines","Hungry Hunter Steakhouse","Shogun"})
        ));
        System.out.println(Arrays.asList(solver.findRestaurant(
            new String[]{"Shogun","Tapioca Express","Burger King","KFC"},
            new String[]{"KFC","Shogun","Burger King"})
        ));
        System.out.println(Arrays.asList(solver.findRestaurant(
            new String[]{"Shogun","Tapioca Express","Burger King","KFC"},
            new String[]{"KFC","Burger King","Tapioca Express","Shogun"})
        ));
        System.out.println(Arrays.asList(solver.findRestaurant(
            new String[]{"Shogun","Tapioca Express","Burger King","KFC"},
            new String[]{"KNN","KFC","Burger King","Tapioca Express","Shogun"})
        ));
        System.out.println(Arrays.asList(solver.findRestaurant(
            new String[]{"KFC"},
            new String[]{"KFC"})
        ));

        System.out.println(Arrays.asList(solver.findRestaurant(
            new String[]{},
            new String[]{"KFC"})
        ));
    }
}
