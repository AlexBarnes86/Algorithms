package com.toastedbits.algs.gfg.graphs.common.algorithms;

import com.toastedbits.algs.gfg.graphs.common.DWEdge;
import com.toastedbits.algs.gfg.graphs.common.Edges;
import com.toastedbits.algs.gfg.graphs.common.Graph;
import com.toastedbits.algs.gfg.graphs.common.SDWEdge;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Slf4j
public class GraphBFS {
    private static void bfs_internal(@NonNull final Graph graph, @NonNull final Consumer<SDWEdge> visitor, @NonNull final Queue<SDWEdge> queue, @NonNull final Set<Integer> visited) {
        while(!queue.isEmpty()) {
            final SDWEdge edge = queue.remove();
            if(!visited.contains(edge.getDest())) {
                visitor.accept(edge);
                visited.add(edge.getDest());
                queue.addAll(graph
                    .getAdjacentEdges(edge.getDest())
                    .stream()
                    .map(e -> Edges.weighted(
                        edge.getDest(),
                        e.getDest(),
                        e.getWeight()))
                    .collect(Collectors.toList()));
            }
        }
    }

    public static void bfs(@NonNull final Graph graph, final int start, @NonNull final Consumer<SDWEdge> visitor) {
        Queue<SDWEdge> queue = new ArrayDeque<>();
        queue.add(Edges.weighted(start, start, Integer.MAX_VALUE));
        GraphBFS.bfs_internal(graph, visitor, queue, new HashSet<>());
    }

    public static Map<Integer, Integer> findLevels(@NonNull final Graph graph) {
        final Map<Integer, Integer> levels = new HashMap<>();
        levels.put(0, -1);
        GraphBFS.bfs(graph, 0, visit -> {
            int level = levels.get(visit.getSrc()) + 1;
            levels.put(visit.getDest(), level);
        });
        return levels;
    }

}
