package com.toastedbits.gfg.graphs.common.algorithms;

import com.toastedbits.gfg.graphs.common.Graph;
import lombok.NonNull;

import java.util.Optional;

public class GraphMotherVertex {
    public static Optional<Integer> findMotherVertex(@NonNull final Graph graph) {
        final boolean[] visited = new boolean[graph.getSize()];
        int mother_vertex = 0;
        for(int i = 0; i < visited.length; ++i) {
            if(!visited[i]) {
                mother_vertex = i;
            }
            GraphDFS.dfs(graph, i, v -> visited[v] = true);
        }

        if(com.toastedbits.gfg.graphs.common.algorithms.GraphReachable.allReachable(graph, mother_vertex)) {
            return Optional.of(mother_vertex);
        }
        return Optional.empty();
    }
}
