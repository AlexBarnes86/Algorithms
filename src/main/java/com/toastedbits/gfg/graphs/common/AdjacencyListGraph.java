package com.toastedbits.gfg.graphs.common;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import java.util.*;

public class AdjacencyListGraph implements Graph {
    private List<List<Edge>> list = new ArrayList<>();

    AdjacencyListGraph() {
    }

    @Override
    public void addEdge(int src, int dest, int value) {
        while(src >= list.size()) {
            list.add(null);
        }
        if(list.get(src) == null) {
            list.set(src, new ArrayList<>());
        }
        list.get(src).add(Edge.to(dest, value));
    }

    @Override
    public void addUndirectedEdge(int a, int b, int value) {
        addEdge(a, b, value);
        addEdge(b, a, value);
    }

    @Override
    public Optional<Integer> getWeight(final int src, final int dest) {
        if(src >= list.size()) {
            return Optional.empty();
        }

        for(final Edge edge : list.get(src)) {
            if(edge.getDest() == dest) {
                return Optional.of(edge.getWeight());
            }
        }

        return Optional.empty();
    }

    @Override
    public boolean contains(final int src, final int dest) {
        return getWeight(src, dest).isPresent();
    }

    @Override
    public Collection<Edge> adjacent(final int vertex) {
        if(vertex >= list.size() || list.get(vertex) == null) {
            return ImmutableSet.of();
        }

        return ImmutableSet.copyOf(list.get(vertex));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for(int i = 0; i < list.size(); ++i) {
            final List<Edge> edges = list.get(i);
            sb.append(i + " -> " + edges);
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
