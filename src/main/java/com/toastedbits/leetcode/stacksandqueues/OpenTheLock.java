package com.toastedbits.leetcode.stacksandqueues;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class OpenTheLock {
    public static class State {
        int numMoves;
        String value;

        public static int matching(String s1, String s2) {
            int ct = 0;
            for(int i = 0; i < s1.length(); ++i) {
                if(i > s2.length()) {
                    return ct;
                }
                if(s1.charAt(i) == s2.charAt(i)) {
                    ct++;
                }
            }
            return ct;
        }

        public State(int numMoves, String value) {
            this.numMoves = numMoves;
            this.value = value;
        }

        public List<State> nextStates(String target, Set<String> visited) {
            List<State> nextStates = new ArrayList<>();
            for(int i = 0; i < value.length(); ++i) {
                char ch = value.charAt(i);

                char[] upChars = value.toCharArray();
                char up = (char) ((((ch - '0') + 1) % 10) + '0');
                upChars[i] = up;
                String upStr = new String(upChars);
                if(!visited.contains(upStr)) {
                    nextStates.add(new State(numMoves + 1, upStr));
                    visited.add(upStr);
                }

                char down = (char) ((((ch - '0') + 9) % 10) + '0');
                char[] downChars = value.toCharArray();
                downChars[i] = down;
                String downStr = new String(downChars);
                if(!visited.contains(downStr)) {
                    nextStates.add(new State(numMoves + 1, downStr));
                    visited.add(downStr);
                }
            }

            //Collections.sort(nextStates, (s1, s2) -> matching(s2.value, target) - matching(s1.value, target));
            return nextStates;
        }

        @Override
        public String toString() {
            return "{numMoves: " + numMoves + ", value: " + value + "}";
        }
    }

    public int openLock(String[] deadends, String target) {
        Queue<State> frontier = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        visited.addAll(Arrays.asList(deadends));
        visited.remove(target);

        frontier.add(new State(0, "0000"));
        if(Arrays.asList(deadends).contains("0000") && !target.equals("0000")) {
            return -1;
        }

        while(!frontier.isEmpty()) {
            State cur = frontier.poll();
            if(cur.value.equals(target)) {
                return cur.numMoves;
            }
            frontier.addAll(cur.nextStates(target, visited));
        }
        return -1;
    }

    public static void main(String[] args) {
//        for(char ch = '0'; ch <= '9'; ch++) {
//            char up = (char) ((((ch - '0') + 1) % 10) + '0');
//            char down = (char) ((((ch - '0') + 9) % 10) + '0');
//            System.out.println(down + ", " + ch + ", " + up);
//        }

        OpenTheLock solver = new OpenTheLock();
//        OpenTheLock.State state = new State(0, "5490");
//        state.nextStates("5555", new HashSet<>(Arrays.asList(new String[]{"1234"}))).forEach(System.out::println);

        System.out.println(solver.openLock(new String[]{"8888"}, "0009"));
        System.out.println(solver.openLock(new String[]{"0201","0101","0102","1212","2002"}, "0202"));
        System.out.println(solver.openLock(new String[]{"8887","8889","8878","8898","8788","8988","7888","9888"}, "8888"));
        System.out.println(solver.openLock(new String[]{"0000"}, "8888"));
        System.out.println(solver.openLock(new String[]{"0000"}, "0000"));
    }
}
