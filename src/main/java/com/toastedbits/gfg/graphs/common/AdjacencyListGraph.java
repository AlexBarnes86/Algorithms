package com.toastedbits.algs.gfg.graphs.common;

import com.google.common.collect.ImmutableSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;

public class AdjacencyListGraph implements Graph {
    private final List<List<DWEdge>> list;
    private int maxObserved;

    AdjacencyListGraph() {
        list = new ArrayList<>();
        maxObserved = 0;
    }

    @Override
    public void setEdge(int src, int dest, int value) {
        int fringe = Math.max(src, dest);
        maxObserved = Math.max(maxObserved, fringe);
        while(fringe >= list.size()) {
            list.add(null);
        }

        if(list.get(src) == null) {
            list.set(src, new ArrayList<>());
        }
        if(list.get(dest) == null) {
            list.set(dest, new ArrayList<>());
        }

        DWEdge newEdge = Edges.to(dest, value);
        if(!list.get(src).contains(newEdge)) {
            list.get(src).add(newEdge);
        }
    }

    @Override
    public void deleteEdge(int src, int dest) {
        checkArgument(containsVertex(src), "No source vertex: " + src);
        checkArgument(containsVertex(dest), "No destination vertex: " + dest);
        if(src < list.size()) {
            list.get(src).removeIf(e -> e.getDest() == dest);
        }
    }

    @Override
    public void deleteVertex(final int vert) {
        checkArgument(containsVertex(vert), "No source vertex: " + vert);
        if(vert < list.size()) {
            for(final DWEdge edge : getAdjacentEdges(vert)) {
                deleteUndirectedEdge(vert, edge.getDest());
            }
            list.set(vert, null);
        }
    }

    @Override
    public Optional<Integer> getWeight(final int src, final int dest) {
        checkArgument(containsVertex(src), "No source vertex: " + src);
        checkArgument(containsVertex(dest), "No destination vertex: " + dest);

        for(final DWEdge edge : list.get(src)) {
            if(edge.getDest() == dest) {
                return Optional.of(edge.getWeight());
            }
        }

        return Optional.empty();
    }

    @Override
    public boolean containsVertex(final int vert) {
        return vert < list.size() && list.get(vert) != null;
    }

    @Override
    public boolean containsEdge(final int src, final int dest) {
        checkArgument(containsVertex(src), "No source vertex: " + src);
        checkArgument(containsVertex(dest), "No destination vertex: " + dest);
        return getWeight(src, dest).isPresent();
    }

    @Override
    public Collection<DWEdge> getAdjacentEdges(final int src) {
        checkArgument(containsVertex(src), "No source vertex: " + src);
        return ImmutableSet.copyOf(list.get(src));
    }

    @Override
    public int getMaxObserved() {
        return maxObserved;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for(int i = 0; i < list.size(); ++i) {
            final List<DWEdge> edges = list.get(i);
            sb.append(i).append(" -> ").append(edges);
            if(i != list.size() - 1) {
                sb.append(", ");
            }
            if(i != list.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
