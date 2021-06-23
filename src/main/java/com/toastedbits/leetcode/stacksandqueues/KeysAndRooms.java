package com.toastedbits.leetcode.stacksandqueues;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KeysAndRooms {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> keys = new HashSet<>();
        keys.add(0);
        for(int i = 0; i < rooms.size(); ++i) {
            if(!keys.contains(i)) {
                return false;
            }
            keys.addAll(rooms.get(i));
        }
        return true;
    }

    public void test1() {
        List<List<Integer>> rooms = new ArrayList<>();
        rooms.add(Collections.singletonList(1));
        rooms.add(Collections.singletonList(2));
        rooms.add(Collections.singletonList(3));
        rooms.add(Collections.emptyList());
        System.out.println(canVisitAllRooms(rooms));
    }

    public void test2() {
        List<List<Integer>> rooms = new ArrayList<>();
        rooms.add(Arrays.asList(1,3));
        rooms.add(Arrays.asList(3,0,1));
        rooms.add(Collections.singletonList(2));
        rooms.add(Collections.singletonList(0));
        System.out.println(canVisitAllRooms(rooms));
    }

    public void test3() {
        List<List<Integer>> rooms = new ArrayList<>();
        rooms.add(Arrays.asList(2));
        rooms.add(Arrays.asList());
        rooms.add(Collections.singletonList(3));
        System.out.println(canVisitAllRooms(rooms));
    }

    public static void main(String[] args) {
        KeysAndRooms solver = new KeysAndRooms();
        solver.test1();
        solver.test2();
        solver.test3();
    }
}
