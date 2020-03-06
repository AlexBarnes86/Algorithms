package com.toastedbits.gfg.graphs;

import com.toastedbits.gfg.graphs.common.Graph;
import com.toastedbits.gfg.graphs.common.Graphs;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GFG1 {
    public static void testGraph(@NonNull final Graph graph) {
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 4, 1);
        graph.addEdge(1, 0, 1);
        graph.addEdge(1, 4, 1);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 1, 1);
        graph.addEdge(2, 3, 1);
        graph.addEdge(3, 1, 1);
        graph.addEdge(3, 4, 1);
        graph.addEdge(3, 2, 1);
        graph.addEdge(4, 3, 1);
        graph.addEdge(4, 0, 1);
        graph.addEdge(4, 1, 1);

        log.info("Graph: {}\n{}", graph.getClass(), graph.toString());
    }

    public static void main(@NonNull final String[] args) {
        testGraph(Graphs.adjacencyListGraph());
        testGraph(Graphs.adjacencyMatrixGraph());
    }
}
