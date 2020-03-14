package com.toastedbits.gfg.graphs.common;

import lombok.ToString;

@ToString
public class SDWEdge extends DWEdge {
    private final Integer src;

    SDWEdge(final Integer src, final Integer dest, final Integer weight) {
        super(dest, weight);
        this.src = src;
    }

    public int getSrc() {
        return this.src;
    }
}