package com.toastedbits.gfg.graphs.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

class AdjacencyHashGraph implements Graph {
    private int maxObserved = 0;
    private final Map<Integer, Map<Integer, Integer>> graph;

    AdjacencyHashGraph() {
        maxObserved = 0;
        graph = new HashMap<>();
    }

    @Override
    public void addEdge(final int src, final int dest, final int value) {
        int fringe = Math.max(src, dest);
        maxObserved = Math.max(maxObserved, fringe);
        final Map<Integer, Integer> edges = graph.computeIfAbsent(src, HashMap::new);
        edges.put(dest, value);
    }

    @Override
    public void addUndirectedEdge(final int a, final int b, final int value) {
        addEdge(a, b, value);
        addEdge(b, a, value);
    }

    @Override
    public Optional<Integer> getWeight(final int src, final int dest) {
        final Map<Integer, Integer> edges = graph.get(src);
        if(edges != null) {
            return Optional.ofNullable(edges.get(dest));
        }
        return Optional.empty();
    }

    @Override
    public boolean contains(final int src, final int dest) {
        final Map<Integer, Integer> edges = graph.get(src);
        return edges != null && edges.containsKey(dest);
    }

    @Override
    public Collection<Edge> adjacent(final int start) {
        final Map<Integer, Integer> edges = graph.get(start);
        final List<Edge> adj = new ArrayList<>();
        for(Map.Entry<Integer, Integer> edge : edges.entrySet()) {
            adj.add(Edge.to(edge.getKey(), edge.getValue()));
        }

        return adj;
    }

    @Override
    public int getSize() {
        return maxObserved + 1;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        List<Integer> entries = new ArrayList<>(graph.keySet());
        Collections.sort(entries);

        boolean firstVert = true;
        for(final Integer vertIdx : entries) {
            if(!firstVert) {
                sb.append("\n");
            }
            firstVert = false;
            sb.append(vertIdx + " -> ");
            boolean firstEdge = true;
            for(Map.Entry<Integer, Integer> edge : graph.get(vertIdx).entrySet()) {
                if(!firstEdge) {
                    sb.append(", ");
                }
                sb.append("(" + edge.getKey() + ", " + edge.getValue() + ")");
                firstEdge = false;
            }
        }
        return sb.toString();
    }
}
