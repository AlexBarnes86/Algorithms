package com.toastedbits.algs.gfg.graphs.common.algorithms;

import com.toastedbits.algs.gfg.graphs.common.Graph;
import lombok.NonNull;

import static com.google.common.base.Preconditions.checkArgument;

public class GraphReachable {
    public static boolean allReachable(@NonNull Graph graph, int start) {
        checkArgument(start < graph.getSize(), String.format("Start vertex [%d] is larger than graph size [%s]", start, graph.getSize()));
        final boolean[] visited = new boolean[graph.getSize()];
        GraphDFS.dfs(graph, start, v -> visited[v] = true);
        for (boolean v : visited) {
            if (!v) {
                return false;
            }
        }
        return true;
    }
}
