package com.toastedbits.algs.gfg.graphs;

import com.toastedbits.algs.gfg.graphs.common.Graph;
import com.toastedbits.algs.gfg.graphs.common.Graphs;
import com.toastedbits.algs.gfg.graphs.common.algorithms.GraphDijstrasShortestPaths;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

//Dijkstra’s shortest path algorithm
//https://www.geeksforgeeks.org/greedy-algorithms-set-6-dijkstras-shortest-path-algorithm/

@Slf4j
public class GFG94 {
    @ParameterizedTest
    @MethodSource("createGraphs")
    public void test(@NonNull final Graph graph) {
        log.debug("Graph {}:\n{}", graph.getClass(), graph);
        List<Integer> shortestPaths = GraphDijstrasShortestPaths.pathCost(graph, 0);
        log.debug("Shortest Paths: {}", shortestPaths);
    }

    private static Stream<Graph> createGraphs() {
        return Stream.of(
            addData(Graphs.adjacencyListGraph()),
            addData(Graphs.adjacencyMatrixGraph()),
            addData(Graphs.adjacencyHashGraph())
        );
    }

    private static Graph addData(@NonNull final Graph graph) {
        graph.addUndirectedEdge(0, 1, 4);
        graph.addUndirectedEdge(0, 7, 8);
        graph.addUndirectedEdge(1, 2, 8);
        graph.addUndirectedEdge(1, 7, 11);
        graph.addUndirectedEdge(2, 3, 7);
        graph.addUndirectedEdge(2, 5, 4);
        graph.addUndirectedEdge(2, 8, 2);
        graph.addUndirectedEdge(3, 4, 9);
        graph.addUndirectedEdge(3, 5, 14);
        graph.addUndirectedEdge(4, 5, 10);
        graph.addUndirectedEdge(5, 6, 2);
        graph.addUndirectedEdge(6, 7, 1);
        graph.addUndirectedEdge(6, 8, 6);
        graph.addUndirectedEdge(7, 8, 7);

        return graph;
    }
}
