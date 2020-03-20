package com.toastedbits.algs.gfg.graphs.common;

public class Edges {
    public static DWEdge to(int dest, int weight) {
        return new DWEdge(dest, weight);
    }

    public static SDWEdge weighted(int src, int dest, int weight) {
        return new SDWEdge(src, dest, weight);
    }

    public static SDWEdge between(int src, int dest) {
        return weighted(src, dest, 1);
    }
}