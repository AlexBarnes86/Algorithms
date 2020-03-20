package com.toastedbits.algs.gfg.graphs.common;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
}
