package com.toastedbits.gfg.graphs.common;

import java.util.Collection;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public interface Graph {
    void addEdge(int src, int dest, int value);
    default void addUndirectedEdge(int a, int b, int value) {
        addEdge(a, b, value);
        addEdge(b, a, value);
    }
    default void addUndirectedEdge(int a, int b) {
        addUndirectedEdge(a, b, 1);
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

    int getMaxObserved();
    default int getSize() {
        return getMaxObserved() + 1;
    }
}
