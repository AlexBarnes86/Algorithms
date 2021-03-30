package com.toastedbits.gfg.graphs.common;

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

    com.toastedbits.gfg.graphs.common.SDWEdge withSource(Integer source) {
        return new com.toastedbits.gfg.graphs.common.SDWEdge(source, dest, weight);
    }
}
