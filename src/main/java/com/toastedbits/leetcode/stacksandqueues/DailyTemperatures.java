package com.toastedbits.leetcode.stacksandqueues;

import java.util.Arrays;
import java.util.Stack;

import static java.util.stream.Collectors.toList;

public class DailyTemperatures {
    private static class TempIdx {
        int idx;
        int temp;

        TempIdx(int idx, int temp) {
            this.idx = idx;
            this.temp = temp;
        }
    }

    public int[] dailyTemperatures(int[] temperatures) {
        int [] results = new int[temperatures.length];
        Stack<TempIdx> tempStack = new Stack<>();
        Stack<Integer> popCtStack = new Stack<>();

        for(int i = 0; i < temperatures.length; ++i) {
            int temp = temperatures[i];
            int popCt = 1;
            while(!tempStack.isEmpty() && temp > tempStack.peek().temp) {
                TempIdx tempIdx = tempStack.pop();
                results[tempIdx.idx] = popCt;
                popCt += popCtStack.pop();
            }
            popCtStack.push(popCt);
            tempStack.push(new TempIdx(i, temp));
        }

        while(!tempStack.isEmpty()) {
            TempIdx tempIdx = tempStack.pop();
            results[tempIdx.idx] = 0;
        }

        return results;
    }

    public static void main(String[] args) {
        DailyTemperatures solver = new DailyTemperatures();
        System.out.println(Arrays.stream(solver.dailyTemperatures(new int[]{73,74,75,71,69,72,76,73})).boxed().collect(toList()));
        System.out.println(Arrays.stream(solver.dailyTemperatures(new int[]{30,40,50,60})).boxed().collect(toList()));
        System.out.println(Arrays.stream(solver.dailyTemperatures(new int[]{30,60,90})).boxed().collect(toList()));

        System.out.println(Arrays.stream(solver.dailyTemperatures(new int[]{90,60,30})).boxed().collect(toList()));
        System.out.println(Arrays.stream(solver.dailyTemperatures(new int[]{90,60,30,40,100})).boxed().collect(toList()));
        System.out.println(Arrays.stream(solver.dailyTemperatures(new int[]{90})).boxed().collect(toList()));
        System.out.println(Arrays.stream(solver.dailyTemperatures(new int[]{})).boxed().collect(toList()));
    }
}
