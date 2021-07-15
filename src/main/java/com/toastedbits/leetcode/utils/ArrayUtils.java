package com.toastedbits.leetcode.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayUtils {
    public static int[] inPlaceRotateRight(int[] ary, int k) {
        k = k % ary.length;
        int count = 0;
        for (int start = 0; count < ary.length; start++) {
            int current = start;
            int prev = ary[start];
            do {
                int next = (ary.length + current + k) % ary.length;
                int temp = ary[next];
                ary[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (start != current);
        }
        return ary;
    }

    public static <T> T[] inPlaceRotateRight(T[] ary, int k) {
        k = k % ary.length;
        int count = 0;
        for (int start = 0; count < ary.length; start++) {
            int current = start;
            T prev = ary[start];
            do {
                int next = (ary.length + current + k) % ary.length;
                T temp = ary[next];
                ary[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (start != current);
        }
        return ary;
    }

    public static <T> List<T> inPlaceRotateRight(List<T> list, int k) {
        k = k % list.size();
        int count = 0;
        for (int start = 0; count < list.size(); start++) {
            int current = start;
            T prev = list.get(start);
            do {
                int next = (list.size() + current + k) % list.size();
                T temp = list.get(next);
                list.set(next, prev);
                prev = temp;
                current = next;
                count++;
            } while (start != current);
        }
        return list;
    }

    public static int[] inPlaceRotateLeft(int[] ary, int k) {
        k = k % ary.length;
        int count = 0;
        for (int start = 0; count < ary.length; start++) {
            int current = start;
            int prev = ary[start];
            do {
                int next = (ary.length + current - k) % ary.length;
                int temp = ary[next];
                ary[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (start != current);
        }
        return ary;
    }

    public static <T> T[] inPlaceRotateLeft(T[] ary, int k) {
        k = k % ary.length;
        int count = 0;
        for (int start = 0; count < ary.length; start++) {
            int current = start;
            T prev = ary[start];
            do {
                int next = (ary.length + current - k) % ary.length;
                T temp = ary[next];
                ary[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (start != current);
        }
        return ary;
    }

    public static <T> List<T> inPlaceRotateLeft(List<T> list, int k) {
        k = k % list.size();
        int count = 0;
        for (int start = 0; count < list.size(); start++) {
            int current = start;
            T prev = list.get(start);
            do {
                int next = (list.size() + current - k) % list.size();
                T temp = list.get(next);
                list.set(next, prev);
                prev = temp;
                current = next;
                count++;
            } while (start != current);
        }
        return list;
    }

    public static List<Integer> range(int start, int count) {
        List<Integer> list = new ArrayList<>(count);
        for(int i = 0; i < count; ++i) {
            list.add(start+i);
        }
        return list;
    }

    public static <T> T[] toArray(Class<T> clazz, List<T> list) {
        @SuppressWarnings("unchecked")
        T[] ary = (T[])Array.newInstance(clazz, list.size());
        for(int i = 0; i < list.size(); ++i) {
            ary[i] = list.get(i);
        }
        return ary;
    }

    public static int[] toArray(List<Integer> list) {
        int[] ary = new int[list.size()];
        for(int i = 0; i < list.size(); ++i) {
            ary[i] = list.get(i);
        }
        return ary;
    }

    public static int linearSearch(int [] ary, int target) {
        for(int i = 0; i < ary.length; ++i) {
            if(ary[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void println(int[][] ary) {
        for(int r = 0; r < ary.length; ++r) {
            for(int c = 0; c < ary[r].length; ++c) {
                System.out.print(ary[r][c]);
                if(c != ary[r].length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }

    public static void println(char[][] ary) {
        for(int r = 0; r < ary.length; ++r) {
            for(int c = 0; c < ary[r].length; ++c) {
                System.out.print(ary[r][c]);
                if(c != ary[r].length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }
}
