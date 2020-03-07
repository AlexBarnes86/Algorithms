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

    private static void bfs_internal(@NonNull final Graph graph, @NonNull final Consumer<Integer> visitor, @NonNull final Queue<Integer> queue, @NonNull final Set<Integer> visited) {
        while(!queue.isEmpty()) {
            int vertex = queue.remove();
            if(!visited.contains(vertex)) {
                visitor.accept(vertex);
                visited.add(vertex);
                queue.addAll(graph.adjacent(vertex).stream().map(Edge::getDest).collect(Collectors.toList()));
            }
        }
    }

    public static void bfs(@NonNull final Graph graph, final int start, @NonNull final Consumer<Integer> visitor) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        bfs_internal(graph, visitor, queue, new HashSet<>());
    }

    private static void dfs_internal(@NonNull final Graph graph, @NonNull final Consumer<Integer> visitor, @NonNull final Deque<Integer> stack, @NonNull final Set<Integer> visited) {
        while(!stack.isEmpty()) {
            int vertex = stack.pop();
            if(!visited.contains(vertex)) {
                visitor.accept(vertex);
                visited.add(vertex);
                graph.adjacent(vertex).stream().map(Edge::getDest).forEach(stack::push);
            }
        }
    }

    public static void dfs(@NonNull final Graph graph, final int start, @NonNull final Consumer<Integer> visitor) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.add(start);
        dfs_internal(graph, visitor, stack, new HashSet<>());
    }
}
