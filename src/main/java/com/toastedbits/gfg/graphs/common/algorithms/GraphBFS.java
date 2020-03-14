package com.toastedbits.gfg.graphs.common.algorithms;

import com.toastedbits.gfg.graphs.common.DWEdge;
import com.toastedbits.gfg.graphs.common.Graph;
import lombok.NonNull;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class GraphBFS {
    public static void bfs_internal(@NonNull final Graph graph, @NonNull final Consumer<Integer> visitor, @NonNull final Queue<Integer> queue, @NonNull final Set<Integer> visited) {
        while(!queue.isEmpty()) {
            int vertex = queue.remove();
            if(!visited.contains(vertex)) {
                visitor.accept(vertex);
                visited.add(vertex);
                queue.addAll(graph.getAdjacent(vertex).stream().map(DWEdge::getDest).collect(Collectors.toList()));
            }
        }
    }

    public static void bfs(@NonNull final Graph graph, final int start, @NonNull final Consumer<Integer> visitor) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        GraphBFS.bfs_internal(graph, visitor, queue, new HashSet<>());
    }
}
