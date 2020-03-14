package com.toastedbits.gfg.graphs;

import com.toastedbits.gfg.graphs.common.AdjacencyMatrixGraph;
import com.toastedbits.gfg.graphs.common.Graph;
import com.toastedbits.gfg.graphs.common.Graphs;
import com.toastedbits.gfg.graphs.common.algorithms.GraphKCore;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Slf4j
public class GFG9 {
    @ParameterizedTest
    @MethodSource("createGraphs")
    public void test(@NonNull final Graph graph) {
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
        graph.addUndirectedEdge(0, 1);
        graph.addUndirectedEdge(0, 2);
        graph.addUndirectedEdge(1, 2);
        graph.addUndirectedEdge(1, 5);
        graph.addUndirectedEdge(2, 3);
        graph.addUndirectedEdge(2, 4);
        graph.addUndirectedEdge(2, 5);
        graph.addUndirectedEdge(2, 6);
        graph.addUndirectedEdge(3, 4);
        graph.addUndirectedEdge(3, 6);
        graph.addUndirectedEdge(3, 7);
        graph.addUndirectedEdge(4, 7);
        graph.addUndirectedEdge(5, 6);
        graph.addUndirectedEdge(5, 8);
        graph.addUndirectedEdge(6, 7);
        graph.addUndirectedEdge(6, 8);

        return graph;
    }
}
