package com.toastedbits.gfg.graphs;

import com.toastedbits.gfg.graphs.common.Graph;
import com.toastedbits.gfg.graphs.common.Graphs;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GFG2 {
    public static void main(@NonNull final String[] args) {
        Graph graph = Graphs.adjacencyMatrixGraph();
        //A - 0, B-1, C-2, D-3, E-4, F-5
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 1);
        graph.addEdge(1, 3, 1);
        graph.addEdge(1, 4, 1);
        graph.addEdge(2, 4, 1);
        graph.addEdge(3, 4, 1);
        graph.addEdge(3, 5, 1);
        graph.addEdge(4, 5, 1);
        graph.addEdge(6, 6, 1);

        log.info("Graph BFS {}\n{}", graph.getClass(), graph);
        Graphs.bfs(graph, 0, i -> log.info("{}", i));
        log.info("Graph DFS {}\n{}", graph.getClass(), graph);
        Graphs.dfs(graph, 0, i -> log.info("{}", i));
    }
}
