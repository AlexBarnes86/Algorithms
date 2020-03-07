package com.toastedbits.gfg.graphs.common;

import java.util.Collection;
import java.util.Optional;

public interface Graph {
    void addEdge(int src, int dest, int value);
    void addUndirectedEdge(int a, int b, int value);
    Optional<Integer> getWeight(final int src, final int dest);
    boolean contains(final int src, final int dest);
    Collection<Edge> adjacent(final int start);
}
