package com.toastedbits.leetcode.stacksandqueues;

public class OpenTheLock {
    private enum Direction {
        LEFT, RIGHT;
    }

    private int count(int start, int target, boolean[] blocked, Direction dir) {
        if(start < 0 || start > 9) {
            throw new IllegalArgumentException("Invalid start: " + start);
        }
        if(target < 0 || target > 9) {
            throw new IllegalArgumentException("Invalid target: " + target);
        }

        int ct = 0;
        int increment = dir == Direction.LEFT ? 9 : 11;
        while(start != target) {
            if(blocked[start]) {
                return Integer.MAX_VALUE;
            }
            start = (start + increment) % 10;
            ct++;
        }
        return ct;
    }

    private int minDist(int start, int target, boolean[] blocked) {
        return Math.min(count(start, target, blocked, Direction.LEFT), count(start, target, blocked, Direction.RIGHT));
    }

    public int openLock(String[] deadends, String target) {
        int total = 0;
        for(int i = 0; i < target.length(); ++i) {
            int targetVal = target.charAt(i) - '0';
            boolean[] blocked = new boolean[10];
            for(String deadend : deadends) {
                if(i >= deadend.length()) {
                    continue;
                }
                int blockedVal = deadend.charAt(i) - '0';
                if(blockedVal < 0 || blockedVal > blocked.length) {
                    continue;
                }
                blocked[blockedVal] = true;

                int ct = minDist(0, targetVal, blocked);
                if(ct == Integer.MAX_VALUE) {
                    return -1;
                }
                total += ct;
            }
        }

        return total;
    }

    public static void main(String[] args) {
        OpenTheLock solver = new OpenTheLock();
        System.out.println(solver.openLock(new String[]{"0201","0101","0102","1212","2002"}, "0202"));
        System.out.println(solver.openLock(new String[]{"8888"}, "0009"));
        System.out.println(solver.openLock(new String[]{"8887","8889","8878","8898","8788","8988","7888","9888"}, "8888"));
        System.out.println(solver.openLock(new String[]{"0000"}, "8888"));
    }
}
