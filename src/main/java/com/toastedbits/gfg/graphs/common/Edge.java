package com.toastedbits.gfg.graphs.common;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
public class Edge {
    private Edge(final int dest, final int weight) {
        this.dest = dest;
        this.weight = weight;
    }

    private final int dest;
    private final int weight;

    public static Edge to(final int dest, final int weight) {
        return new Edge(dest, weight);
    }
}
