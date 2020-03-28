package com.toastedbits.algs.gfg.graphs.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public interface Graph {
    void addEdge(int src, int dest, int value);
    default void addEdge(int src, int dest) {
        addEdge(src, dest, 1);
    }

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

    default List<SDWEdge> getAllEdges() {
        List<SDWEdge> edges = new ArrayList<>();
        for(int i = 0; i < getSize(); ++i) {
            if(containsVertex(i)) {
                final int source = i;
                edges.addAll(getAdjacentEdges(i).stream().map(dwEdge -> dwEdge.withSource(source)).collect(toList()));
            }
        }
        return edges;
    }

    int getMaxObserved();
    default int getSize() {
        return getMaxObserved() + 1;
    }
}
