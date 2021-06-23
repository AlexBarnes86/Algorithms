package com.toastedbits.leetcode.stacksandqueues;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class KeysAndRooms {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        while(!stack.isEmpty()) {
            int cur = stack.pop();
            visited[cur] = true;
            for(int key : rooms.get(cur)) {
                if(key >= 0 && key < rooms.size() && !visited[key]) {
                    stack.push(key);
                }
            }
        }

        for(boolean room : visited) {
            if(!room) {
                return false;
            }
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
        rooms.add(Collections.singletonList(1));
        System.out.println(canVisitAllRooms(rooms));
    }

    public static void main(String[] args) {
        KeysAndRooms solver = new KeysAndRooms();
        solver.test1();
        solver.test2();
        solver.test3();
    }
}
