package com.toastedbits.gfg.graphs;

import com.google.common.collect.ImmutableList;
import com.toastedbits.gfg.graphs.common.Graph;
import com.toastedbits.gfg.graphs.common.Graphs;
import com.toastedbits.gfg.graphs.common.algorithms.GraphPaths;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;

//Count all possible paths between two vertices
//https://www.geeksforgeeks.org/count-possible-paths-two-vertices/

@Slf4j
public class GFG11 {
    @ParameterizedTest
    @MethodSource("createGraphs")
    public void testFindPaths(@NonNull final Graph graph) {
        final List<List<Integer>> paths = GraphPaths.findAllPaths(graph, 0, 4);
        log.debug("Graph {}\n{}", graph.getClass(), graph);
        paths.forEach(path -> log.debug("Found path {}", path));
        assertThat(paths, hasItems(
            ImmutableList.of(0, 4),
            ImmutableList.of(0, 1, 4),
            ImmutableList.of(0, 2, 4),
            ImmutableList.of(0, 1, 3, 2, 4)
        ));
    }

    private static Stream<Graph> createGraphs() {
        return Stream.of(
            addData(Graphs.adjacencyListGraph()),
            addData(Graphs.adjacencyMatrixGraph()),
            addData(Graphs.adjacencyHashGraph())
        );
    }

    private static Graph addData(@NonNull final Graph graph) {
        //A = 0, B = 1, C = 2, D = 3, E = 4
        graph.setEdge(0, 1);
        graph.setEdge(0, 2);
        graph.setEdge(0, 4);
        graph.setEdge(1, 3);
        graph.setEdge(1, 4);
        graph.setEdge(2, 4);
        graph.setEdge(3, 2);

        return graph;
    }
}
