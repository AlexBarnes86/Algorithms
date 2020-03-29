package com.toastedbits.algs.gfg.graphs;

import com.toastedbits.algs.gfg.graphs.common.Graph;
import com.toastedbits.algs.gfg.graphs.common.Graphs;
import com.toastedbits.algs.gfg.graphs.common.algorithms.GraphFordFulkersonMaxFlow;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Slf4j
public class GFG155 {
    @ParameterizedTest
    @MethodSource("createGraphs")
    public void test(@NonNull final Graph graph) {
        log.debug("Graph {}:\n{}", graph.getClass(), graph);
        GraphFordFulkersonMaxFlow.Result result = GraphFordFulkersonMaxFlow.maxFlow(graph, 0, 5);
        log.debug("Max Flow: {}", result.getMaxFlow());
        log.debug("Residual Graph:\n{}", result.getResiduals());

        assertThat(23, equalTo(result.getMaxFlow()));
    }

    private static Stream<Graph> createGraphs() {
        return Stream.of(
            addData(Graphs.adjacencyListGraph()),
            addData(Graphs.adjacencyMatrixGraph()),
            addData(Graphs.adjacencyHashGraph())
        );
    }

    private static Graph addData(@NonNull final Graph graph) {
        graph.setEdge(0, 1, 16);
        graph.setEdge(0, 2, 13);
        graph.setEdge(1, 2, 10);
        graph.setEdge(1, 3, 12);
        graph.setEdge(2, 1, 4);
        graph.setEdge(2, 4, 14);
        graph.setEdge(3, 2, 9);
        graph.setEdge(3, 5, 20);
        graph.setEdge(4, 3, 7);
        graph.setEdge(4, 5, 4);

        return graph;
    }
}
