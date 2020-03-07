package com.toastedbits.gfg.graphs.common;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class Edge {
    int dest;
    int weight;

    public static Edge to(int dest, int weight) {
        return new Edge(dest, weight);
    }
}
