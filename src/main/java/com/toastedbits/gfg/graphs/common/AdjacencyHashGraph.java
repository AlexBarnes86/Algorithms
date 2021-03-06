package com.toastedbits.gfg.graphs.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;

class AdjacencyHashGraph implements com.toastedbits.gfg.graphs.common.Graph {
    private int maxObserved;
    private final Map<Integer, Map<Integer, Integer>> graph;

    AdjacencyHashGraph() {
        maxObserved = 0;
        graph = new HashMap<>();
    }

    @Override
    public void setEdge(final int src, final int dest, final int value) {
        int fringe = Math.max(src, dest);
        maxObserved = Math.max(maxObserved, fringe);

        final Map<Integer, Integer> edges = graph.computeIfAbsent(src, HashMap::new);
        edges.put(dest, value);
        graph.computeIfAbsent(dest, HashMap::new);
    }

    @Override
    public void deleteEdge(final int src, final int dest) {
        checkArgument(containsVertex(src), "No source vertex: " + src);
        checkArgument(containsVertex(dest), "No destination vertex: " + dest);
        if (graph.containsKey(src)) {
            graph.get(src).remove(dest);
        }
    }

    @Override
    public void deleteVertex(final int vert) {
        checkArgument(containsVertex(vert), "No source vertex: " + vert);
        if(graph.containsKey(vert)) {
            for(final com.toastedbits.gfg.graphs.common.DWEdge edge : getAdjacentEdges(vert)) {
                deleteUndirectedEdge(vert, edge.getDest());
            }
            graph.remove(vert);
        }
    }

    @Override
    public Optional<Integer> getWeight(final int src, final int dest) {
        checkArgument(containsVertex(src), "No source vertex: " + src);
        checkArgument(containsVertex(dest), "No destination vertex: " + dest);
        final Map<Integer, Integer> edges = graph.get(src);
        return Optional.ofNullable(edges.get(dest));
    }

    @Override
    public boolean containsVertex(final int vert) {
        return graph.containsKey(vert);
    }

    @Override
    public boolean containsEdge(final int src, final int dest) {
        checkArgument(containsVertex(src), "No source vertex: " + src);
        checkArgument(containsVertex(dest), "No destination vertex: " + dest);
        final Map<Integer, Integer> edges = graph.get(src);
        return edges != null && edges.containsKey(dest);
    }

    @Override
    public Collection<DWEdge> getAdjacentEdges(final int src) {
        checkArgument(containsVertex(src), "No source vertex: " + src);
        final Map<Integer, Integer> edges = graph.get(src);
        final List<com.toastedbits.gfg.graphs.common.DWEdge> adj = new ArrayList<>();
        if(edges != null) {
            for (Map.Entry<Integer, Integer> edge : edges.entrySet()) {
                adj.add(com.toastedbits.gfg.graphs.common.Edges.to(edge.getKey(), edge.getValue()));
            }
        }

        return adj;
    }

    @Override
    public int getMaxObserved() {
        return maxObserved;
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
            sb.append(vertIdx).append(" -> ");
            boolean firstEdge = true;
            for(Map.Entry<Integer, Integer> edge : graph.get(vertIdx).entrySet()) {
                if(!firstEdge) {
                    sb.append(", ");
                }
                sb.append("(").append(edge.getKey()).append(", ").append(edge.getValue()).append(")");
                firstEdge = false;
            }
        }
        return sb.toString();
    }
}
