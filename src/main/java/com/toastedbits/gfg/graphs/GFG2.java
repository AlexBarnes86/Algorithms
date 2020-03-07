package com.toastedbits.gfg.graphs;

import com.toastedbits.gfg.graphs.common.Graph;
import com.toastedbits.gfg.graphs.common.Graphs;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GFG2 {
    public static void main(@NonNull final String[] args) {
        Graph graph = Graphs.adjacencyMatrixGraph();
        graph.addEdge(2, 0, 1);
        graph.addEdge(0, 2, 1);
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 1);
        graph.addEdge(2, 3, 1);
        graph.addEdge(3, 3, 1);

        log.info("Graph {}\n{}", graph.getClass(), graph);
        Graphs.bfs(graph, 2, i -> log.info("{}", i));
    }
}
