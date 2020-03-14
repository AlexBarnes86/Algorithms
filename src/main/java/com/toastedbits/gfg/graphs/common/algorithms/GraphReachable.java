package com.toastedbits.gfg.graphs.common.algorithms;

import com.toastedbits.gfg.graphs.common.Graph;
import lombok.NonNull;

public class GraphReachable {
    public static boolean allReachable(@NonNull Graph graph, int start) {
        final boolean[] visited = new boolean[graph.getSize()];
        if(start >= graph.getSize()) {
            return false;
        }
        GraphDFS.dfs(graph, start, v -> visited[v] = true);
        for (boolean v : visited) {
            if (!v) {
                return false;
            }
        }
        return true;
    }
}
