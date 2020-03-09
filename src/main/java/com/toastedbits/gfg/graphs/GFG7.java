package com.toastedbits.gfg.graphs;

import com.toastedbits.gfg.graphs.common.Graph;
import com.toastedbits.gfg.graphs.common.Graphs;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class GFG7 {
    private static void addGraphData1(@NonNull final Graph graph) {
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 1);
        graph.addEdge(1, 3, 1);
        graph.addEdge(4, 1, 1);
        graph.addEdge(5, 2, 1);
        graph.addEdge(5, 6, 1);
        graph.addEdge(6, 4, 1);
        graph.addEdge(6, 0, 1);
    }

    private static void test(@NonNull final Graph graph) {
        addGraphData1(graph);
        log.info("Graph {}\n{}", graph.getClass(), graph);
        Optional<Integer> mother = Graphs.findMotherVertex(graph);
        log.info("Mother vertex is {}", mother);
    }

    public static void main(@NonNull final String[] args) {
        test(Graphs.adjacencyListGraph());
        test(Graphs.adjacencyMatrixGraph());
        test(Graphs.adjacencyMatrixGraph());
    }
}
