package com.toastedbits.algs.gfg.graphs;

import com.toastedbits.algs.gfg.graphs.common.Graph;
import com.toastedbits.algs.gfg.graphs.common.Graphs;
import com.toastedbits.algs.gfg.graphs.common.algorithms.GraphDijstrasShortestPaths;
import com.toastedbits.algs.gfg.graphs.common.algorithms.GraphFloydWarshall;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Slf4j
public class GFG97 {
    @ParameterizedTest
    @MethodSource("createGraphs")
    public void test(@NonNull final Graph graph) {
        log.debug("Graph {}:\n{}", graph.getClass(), graph);
        int[][] shortestPaths = GraphFloydWarshall.allPairsShortestPaths(graph);
        log.debug("Shortest Paths: {}", Arrays.asList(shortestPaths));

        final int inf = Integer.MAX_VALUE;
        int[][] solution = new int[][]{
            {0, 5, 8, 9},
            {inf, 0, 3, 4},
            {inf, inf, 0, 1},
            {inf, inf, inf, 0}
        };

        assertThat(solution, equalTo(shortestPaths));
    }

    private static Stream<Graph> createGraphs() {
        return Stream.of(
            addData(Graphs.adjacencyListGraph()),
            addData(Graphs.adjacencyMatrixGraph()),
            addData(Graphs.adjacencyHashGraph())
        );
    }

    private static Graph addData(@NonNull final Graph graph) {
        graph.addEdge(0, 1, 5);
        graph.addEdge(0, 3, 10);
        graph.addEdge(1, 2, 3);
        graph.addEdge(2, 3, 1);

        return graph;
    }
}
