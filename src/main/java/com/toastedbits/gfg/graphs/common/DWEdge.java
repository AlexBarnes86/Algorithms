package com.toastedbits.algs.gfg.graphs.common;

import lombok.Data;
import lombok.Getter;

@Data
public class DWEdge {
    @Getter private final Integer dest;
    @Getter private final Integer weight;

    DWEdge(Integer dest, Integer weight) {
        this.dest = dest;
        this.weight = weight;
    }

    SDWEdge withSource(Integer source) {
        return new SDWEdge(source, dest, weight);
    }
}
