package com.toastedbits.gfg.graphs;

import com.toastedbits.gfg.graphs.common.Graph;
import com.toastedbits.gfg.graphs.common.Graphs;
import com.toastedbits.gfg.graphs.common.algorithms.GraphIsReachable;
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
        graph.setEdge(0, 1);
        graph.setEdge(0, 2);
        graph.setEdge(1, 2);
        graph.setEdge(2, 0);
        graph.setEdge(2, 3);
        graph.setEdge(3, 3);

        return graph;
    }
}
