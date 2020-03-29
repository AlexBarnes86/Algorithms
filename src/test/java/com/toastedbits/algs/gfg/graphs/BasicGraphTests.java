package com.toastedbits.algs.gfg.graphs;

import com.toastedbits.algs.gfg.graphs.common.AdjacencyMatrixGraph;
import com.toastedbits.algs.gfg.graphs.common.Edges;
import com.toastedbits.algs.gfg.graphs.common.Graph;
import com.toastedbits.algs.gfg.graphs.common.Graphs;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class BasicGraphTests {
    private static Stream<Graph> createGraphs() {
        return Stream.of(
            Graphs.adjacencyListGraph(),
            Graphs.adjacencyMatrixGraph(),
            Graphs.adjacencyHashGraph()
        );
    }

    @ParameterizedTest
    @MethodSource("createGraphs")
    public void testDiagonal(@NonNull final Graph graph) {
        //Choose a large number to force a resize
        for(int i = 0; i < 100; ++i) {
            graph.setEdge(i, i, 1);
            assertTrue(graph.containsEdge(i, i));
            assertThat(graph.getAdjacentVertecies(i), CoreMatchers.hasItem(i));
            assertThat(graph.getWeight(i, i), equalTo(Optional.of(1)));
            assertThat(graph.getAdjacentEdges(i), hasItem(Edges.to(i, 1)));
            assertTrue(graph.containsVertex(i));
            graph.deleteVertex(i);
            assertTrue(graph instanceof AdjacencyMatrixGraph || !graph.containsVertex(i));
        }
        log.debug("\n{}", graph);
    }
}
