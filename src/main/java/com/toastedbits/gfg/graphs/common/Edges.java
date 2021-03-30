package com.toastedbits.gfg.graphs.common;

public class Edges {
    public static DWEdge to(int dest, int weight) {
        return new DWEdge(dest, weight);
    }

    public static com.toastedbits.gfg.graphs.common.SDWEdge weighted(int src, int dest, int weight) {
        return new com.toastedbits.gfg.graphs.common.SDWEdge(src, dest, weight);
    }

    public static com.toastedbits.gfg.graphs.common.SDWEdge between(int src, int dest) {
        return weighted(src, dest, 1);
    }
}