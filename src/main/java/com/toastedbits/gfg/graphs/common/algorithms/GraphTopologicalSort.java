package com.toastedbits.algs.gfg.graphs.common.algorithms;

import com.toastedbits.algs.gfg.graphs.common.Graph;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GraphTopologicalSort {
    private static void sortUtil(final Graph graph, final int idx, final Stack<Integer> stack, final boolean[] visited) {
        visited[idx] = true;

        for(int adj : graph.getAdjacentVertecies(idx)) {
            if(!visited[adj]) {
                sortUtil(graph, adj, stack, visited);
            }
        }

        stack.push(idx);
    }

    public static List<Integer> sort(@NonNull final Graph graph) {
        final Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[graph.getSize()];

        for(int i = 0; i < graph.getSize(); ++i) {
            if(!visited[i]) {
                sortUtil(graph, i, stack, visited);
            }
        }

        final List<Integer> result = new ArrayList <>();
        while(!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }
}
