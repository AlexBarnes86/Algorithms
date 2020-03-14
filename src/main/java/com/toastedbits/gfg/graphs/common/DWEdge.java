package com.toastedbits.gfg.graphs.common;

import lombok.Data;
import lombok.Getter;

@Data
public class DWEdge {
    @Getter private final int dest;
    @Getter private final int weight;

    DWEdge(int dest, int weight) {
        this.dest = dest;
        this.weight = weight;
    }
}
