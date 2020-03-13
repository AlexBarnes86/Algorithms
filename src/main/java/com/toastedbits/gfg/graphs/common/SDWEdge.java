package com.toastedbits.gfg.graphs.common;

public class SDWEdge extends DWEdge {
    private final int src;

    SDWEdge(int src, int dest, int weight) {
        super(dest, weight);
        this.src = src;
    }

    public int getSrc() {
        return this.src;
    }
}