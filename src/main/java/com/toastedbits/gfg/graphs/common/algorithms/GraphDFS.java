package com.toastedbits.gfg.graphs.common.algorithms;

import com.toastedbits.gfg.graphs.common.DWEdge;
import com.toastedbits.gfg.graphs.common.Graph;
import lombok.NonNull;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class GraphDFS {
    private static void dfs_internal(@NonNull final Graph graph, @NonNull final Consumer<Integer> visitor, @NonNull final Deque<Integer> stack, @NonNull final Set<Integer> visited) {
        while(!stack.isEmpty()) {
            int vertex = stack.pop();
            if(!visited.contains(vertex)) {
                visitor.accept(vertex);
                visited.add(vertex);
                graph.getAdjacentEdges(vertex).stream().map(DWEdge::getDest).forEach(stack::push);
            }
        }
    }

    public static void dfs(@NonNull final Graph graph, final int start, @NonNull final Consumer<Integer> visitor) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.add(start);
        dfs_internal(graph, visitor, stack, new HashSet<>());
    }
}
