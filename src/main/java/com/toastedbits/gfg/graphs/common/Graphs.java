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

    public static AdjacencyHashGraph adjacencyHashGraph() {
        return new AdjacencyHashGraph();
    }

    private static void bfs_internal(@NonNull final Graph graph, @NonNull final Consumer<Integer> visitor, @NonNull final Queue<Integer> queue, @NonNull final Set<Integer> visited) {
        while(!queue.isEmpty()) {
            int vertex = queue.remove();
            if(!visited.contains(vertex)) {
                visitor.accept(vertex);
                visited.add(vertex);
                queue.addAll(graph.adjacent(vertex).stream().map(DWEdge::getDest).collect(Collectors.toList()));
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
                graph.adjacent(vertex).stream().map(DWEdge::getDest).forEach(stack::push);
            }
        }
    }

    public static void dfs(@NonNull final Graph graph, final int start, @NonNull final Consumer<Integer> visitor) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.add(start);
        dfs_internal(graph, visitor, stack, new HashSet<>());
    }

    public static boolean allReachable(@NonNull Graph graph, int start) {
        final boolean[] visited = new boolean[graph.getSize()];
        if(start >= graph.getSize()) {
            return false;
        }
        dfs(graph, start, v -> visited[v] = true);
        for(int i = 0; i < visited.length; ++i) {
            if(!visited[i]) {
                return false;
            }
        }
        return true;
    }

    public static Optional<Integer> findMotherVertex(@NonNull final Graph graph) {
        final boolean[] visited = new boolean[graph.getSize()];
        int mother_vertex = 0;
        for(int i = 0; i < visited.length; ++i) {
            if(!visited[i]) {
                mother_vertex = i;
            }
            dfs(graph, i, v -> visited[v] = true);
        }

        if(allReachable(graph, mother_vertex)) {
            return Optional.of(mother_vertex);
        }
        return Optional.empty();
    }

    public static boolean[][] transitiveClosure(@NonNull final Graph graph) {
        final boolean [][] reachable = new boolean[graph.getSize()][graph.getSize()];
        final Deque<SDWEdge> stack = new ArrayDeque<>();

        for(int vertex = 0; vertex < graph.getSize(); vertex++) {
            stack.push(Edges.between(vertex, vertex));
            while(!stack.isEmpty()) {
                SDWEdge sd = stack.pop();
                reachable[sd.getSrc()][sd.getDest()] = true;
                for (final DWEdge adj : graph.adjacent(sd.getDest())) {
                    if (!reachable[sd.getSrc()][adj.getDest()]) {
                        stack.push(Edges.between(sd.getSrc(), adj.getDest()));
                    }
                }
            }
        }

        return reachable;
    }
}
