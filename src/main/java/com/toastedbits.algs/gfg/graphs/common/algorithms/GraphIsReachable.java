package com.toastedbits.algs.gfg.graphs.common.algorithms;

import com.toastedbits.algs.gfg.graphs.common.Graph;
import lombok.NonNull;

import java.util.concurrent.atomic.AtomicBoolean;

public class GraphIsReachable {
    public static boolean isReachable(@NonNull final Graph graph, final int start, final int dest) {
        //This could be optimized further by stopping the bfs search as soon as a path is found
        //We could also return the shortest path using A*
        final AtomicBoolean result = new AtomicBoolean();
        GraphBFS.bfs(graph, start, e -> {if(e.getDest() == dest) result.set(true);});
        return result.get();
    }
}
