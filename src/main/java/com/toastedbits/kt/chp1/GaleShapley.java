package com.toastedbits.kt.chp1;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

/**
 * Assume a set of men, m, and women, w
 *
 * For each man propose to highest ranked w that he has not yet proposed to
 * if w is free they become engaged
 * if w is engaged
 *   let m' be who she is engaged to
 *   if w ranks m > m' then
 *     m' becomes free
 *     m becomes engaged to w
 *   if w ranks m < m' then
 *     m remains free
 */

public class GaleShapley {
    @Data
    @Builder
    @RequiredArgsConstructor(access= AccessLevel.PRIVATE)
    private static class Instance {
        private final List<List<Integer>> men;
        private final List<List<Integer>> women;

        public static Instance createInstance(int n) {
            List<List<Integer>> men = new ArrayList<>();
            List<List<Integer>> women = new ArrayList<>();
            for(int i = 0; i < n; ++i) {
                men.add(permutedRange(0, n));
                women.add(permutedRange(0, n));
            }
            return new Instance(men, women);
        }

        public Map<Integer, Integer> solve() {
            final Map<Integer, Integer> proposals = new HashMap<>();
            final Map<Integer, Integer> engagements = new HashMap<>();
            final Queue<Integer> queue = new PriorityQueue<>();
            queue.addAll(IntStream.range(0, men.size()).boxed().collect(toList()));

            while(!queue.isEmpty()) {
                final int m = queue.remove();
                final int proposalIdx = proposals.compute(m, (k, v) -> v == null ? 0 : v + 1);
                final List<Integer> mPref = men.get(m);
                final int w = mPref.get(proposalIdx);

                //System.out.format("m: %d, proposalIdx: %d, mPref: %s, w: %d => ", m, proposalIdx, mPref, w);
                if(!engagements.containsKey(w)) {
                    List<Integer> wPref = women.get(w);
                    engagements.put(w, m);
                    //System.out.format("Engaged: m2: N, wPref: %s, Engagements: %s%n", wPref, engagements);
                }
                else {
                    int m2 = engagements.get(w);
                    List<Integer> wPref = women.get(w);
                    if(wPref.indexOf(m) < wPref.indexOf(m2)) {
                        engagements.put(w, m);
                        queue.add(m2);
                        //System.out.format("Engaged: m2: %d, wPref: %s, Engagements: %s%n", m2, wPref, engagements);
                    }
                    else {
                        queue.add(m);
                        //System.out.format("Ignored: m2: %d, wPref: %s, Engagements: %s%n", m2, wPref, engagements);
                    }
                }
            }

            return engagements;
        }

        private static List<Integer> permutedRange(int start, int end) {
            List<Integer> list = IntStream.range(start, end).boxed().collect(toList());
            Collections.shuffle(list);
            return list;
        }

        public void print(OutputStream os) {
            PrintWriter pw = new PrintWriter(os);
            pw.format("Men: %n%s%n", men);
            pw.format("Women: %n%s%n", women);
            pw.flush();
        }

    }

    public static void main(String[] args) {
        for(int i = 0; i < 10; ++i) {
            System.out.println("Iteration: " + i);
            int n = 3;
            Instance instance = Instance.createInstance(n);
            instance.print(System.out);
            System.out.println("Engagements:\n" + instance.solve());
        }
    }

    private void test() {
        Instance oneInstance = Instance.builder()
                .men(Lists.newArrayList(
                        Lists.newArrayList(0, 2, 1),
                        Lists.newArrayList(2, 0, 1),
                        Lists.newArrayList(0, 2, 1)
                    )
                )
                .women(Lists.newArrayList(
                        Lists.newArrayList(1,2,0),
                        Lists.newArrayList(0,1,2),
                        Lists.newArrayList(1,0,2)
                    )
                )
                .build();

        Map<Integer, Integer> solution = ImmutableMap.<Integer, Integer>builder()
                .put(0, 2)
                .put(1, 0)
                .put(2, 1)
                .build();

        Map<Integer, Integer> attemptedSolution = oneInstance.solve();
        System.out.println("One Instance: ");
        oneInstance.print(System.out);
        System.out.println("Attempted Solution: " + attemptedSolution);
        System.out.println("Actual Solution: " + solution);
        System.out.println("Solutions match: " + oneInstance.solve().equals(solution));
    }
}
