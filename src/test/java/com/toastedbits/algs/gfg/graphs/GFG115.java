package com.toastedbits.algs.gfg.graphs;

import com.toastedbits.algs.gfg.graphs.common.Graph;
import com.toastedbits.algs.gfg.graphs.common.Graphs;
import com.toastedbits.algs.gfg.graphs.common.algorithms.GraphIsReachable;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class GFG115 {
    @ParameterizedTest
    @MethodSource("createGraphs")
    public void testReachability(@NonNull final Graph graph) {
        log.debug("Graph {}:\n{}", graph.getClass(), graph);

        boolean isReachable = GraphIsReachable.isReachable(graph, 0, 3);
        log.debug("Is 0 -> 3 Reachable: {}", isReachable);
        assertTrue(isReachable);

        isReachable = GraphIsReachable.isReachable(graph, 3, 0);
        log.debug("Is 3 -> 0 Reachable: {}", isReachable);
        assertFalse(isReachable);
    }

    private static Stream<Graph> createGraphs() {
        return Stream.of(
            addData(Graphs.adjacencyListGraph()),
            addData(Graphs.adjacencyMatrixGraph()),
            addData(Graphs.adjacencyHashGraph())
        );
    }

    private static Graph addData(@NonNull final Graph graph) {
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);

        return graph;
    }
}
