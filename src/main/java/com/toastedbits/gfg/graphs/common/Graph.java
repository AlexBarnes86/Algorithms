package com.toastedbits.gfg.graphs.common;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public interface Graph {
    void setEdge(int src, int dest, int value);
    default void setEdge(int src, int dest) {
        setEdge(src, dest, 1);
    }

    default void setUndirectedEdge(int a, int b, int value) {
        setEdge(a, b, value);
        setEdge(b, a, value);
    }
    default void setUndirectedEdge(int a, int b) {
        setUndirectedEdge(a, b, 1);
    }

    default void addWeight(int src, int dest, int value) {
        getWeight(src, dest).ifPresent(w ->
            setEdge(src, dest, w + value)
        );
    }

    void deleteEdge(int src, int dest);
    default void deleteUndirectedEdge(int src, int dest) {
        deleteEdge(src, dest);
        deleteEdge(dest, src);
    }

    void deleteVertex(int vert);

    Optional<Integer> getWeight(final int src, final int dest);
    boolean containsVertex(final int vert);
    boolean containsEdge(final int src, final int dest);
    Collection<DWEdge> getAdjacentEdges(final int src);
    default Collection<Integer> getAdjacentVertecies(final int src) {
        return getAdjacentEdges(src).stream().map(DWEdge::getDest).collect(toList());
    }

    default List<com.toastedbits.gfg.graphs.common.SDWEdge> getAllEdges() {
        List<com.toastedbits.gfg.graphs.common.SDWEdge> edges = new ArrayList<>();
        for(int i = 0; i < getSize(); ++i) {
            if(containsVertex(i)) {
                final int source = i;
                edges.addAll(getAdjacentEdges(i).stream().map(dwEdge -> dwEdge.withSource(source)).collect(toList()));
            }
        }
        return edges;
    }

    default Graph addGraph(@NonNull final Graph other) {
        for(final com.toastedbits.gfg.graphs.common.SDWEdge edge : other.getAllEdges()) {
            setEdge(edge.getSrc(), edge.getDest(), edge.getWeight());
        }
        return this;
    }

    int getMaxObserved();
    default int getSize() {
        return getMaxObserved() + 1;
    }
}
