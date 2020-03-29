package com.toastedbits.algs.gfg.graphs;

import com.toastedbits.algs.gfg.graphs.common.AdjacencyMatrixGraph;
import com.toastedbits.algs.gfg.graphs.common.Graph;
import com.toastedbits.algs.gfg.graphs.common.Graphs;
import com.toastedbits.algs.gfg.graphs.common.algorithms.GraphKCore;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertFalse;

//Find K cores of an undirected Graph
//https://www.geeksforgeeks.org/find-k-cores-graph/

@Slf4j
public class GFG9 {
    @ParameterizedTest
    @MethodSource("createGraphs")
    public void testKCore(@NonNull final Graph graph) {
        log.info("Graph {}\n{}", graph.getClass(), graph);
        GraphKCore.kCore(graph, 3); //2, 3, 4, 6, 7
        log.info("3-Core\n{}", graph);

        assertThat(graph.getAdjacentVertecies(2), containsInAnyOrder(3, 4, 6));
        assertThat(graph.getAdjacentVertecies(3), containsInAnyOrder(2, 4, 6, 7));
        assertThat(graph.getAdjacentVertecies(4), containsInAnyOrder(2, 3, 7));
        assertThat(graph.getAdjacentVertecies(6), containsInAnyOrder(2, 3, 7));
        assertThat(graph.getAdjacentVertecies(7), containsInAnyOrder(3, 4, 6));

        if(!(graph instanceof AdjacencyMatrixGraph)){
            assertFalse(graph.containsVertex(0));
            assertFalse(graph.containsVertex(1));
            assertFalse(graph.containsVertex(5));
            assertFalse(graph.containsVertex(8));
        }
    }

    private static Stream<Graph> createGraphs() {
        return Stream.of(
            addData(Graphs.adjacencyListGraph()),
            addData(Graphs.adjacencyMatrixGraph()),
            addData(Graphs.adjacencyHashGraph())
        );
    }

    private static Graph addData(@NonNull final Graph graph) {
        graph.setUndirectedEdge(0, 1);
        graph.setUndirectedEdge(0, 2);
        graph.setUndirectedEdge(1, 2);
        graph.setUndirectedEdge(1, 5);
        graph.setUndirectedEdge(2, 3);
        graph.setUndirectedEdge(2, 4);
        graph.setUndirectedEdge(2, 5);
        graph.setUndirectedEdge(2, 6);
        graph.setUndirectedEdge(3, 4);
        graph.setUndirectedEdge(3, 6);
        graph.setUndirectedEdge(3, 7);
        graph.setUndirectedEdge(4, 7);
        graph.setUndirectedEdge(5, 6);
        graph.setUndirectedEdge(5, 8);
        graph.setUndirectedEdge(6, 7);
        graph.setUndirectedEdge(6, 8);

        return graph;
    }
}
