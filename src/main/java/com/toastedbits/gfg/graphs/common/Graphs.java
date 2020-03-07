package com.toastedbits.gfg.graphs.common;

import lombok.NonNull;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Graphs {
    public static AdjacencyListGraph adjacencyListGraph() {
        return new AdjacencyListGraph();
    }

    public static AdjacencyMatrixGraph adjacencyMatrixGraph() {
        return new AdjacencyMatrixGraph();
    }

    private static void bfs_internal(@NonNull final Graph graph, final int start, @NonNull final Consumer<Integer> visitor, @NonNull Queue<Integer> fringe, @NonNull Set<Integer> visited) {
        while(!fringe.isEmpty()) {
            int vertex = fringe.remove();
            if(!visited.contains(vertex)) {
                visitor.accept(vertex);
                visited.add(vertex);
                fringe.addAll(graph.adjacent(vertex).stream().map(Edge::getDest).collect(Collectors.toList()));
            }
        }
    }
    public static void bfs(@NonNull final Graph graph, final int start, @NonNull final Consumer<Integer> visitor) {
        Queue<Integer> fringe = new ArrayDeque<>();
        fringe.add(start);
        bfs_internal(graph, start, visitor, fringe, new HashSet<>());
    }
}
