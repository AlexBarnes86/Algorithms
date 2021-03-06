package com.toastedbits.gfg.graphs;

import com.toastedbits.gfg.graphs.common.Graph;
import com.toastedbits.gfg.graphs.common.Graphs;
import com.toastedbits.gfg.graphs.common.algorithms.GraphMotherVertex;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

//Find Mother Vertex in a Graph
//https://www.geeksforgeeks.org/find-a-mother-vertex-in-a-graph/

@Slf4j
public class GFG7 {
    @ParameterizedTest
    @MethodSource("createGraphs")
    public void test(@NonNull final Graph graph) {
        log.info("Graph {}\n{}", graph.getClass(), graph);
        Optional<Integer> mother = GraphMotherVertex.findMotherVertex(graph);
        log.info("Mother vertex is {}", mother);
        assertTrue(mother.isPresent());
        assertThat(mother.get(), equalTo(5));
    }

    private static Stream<Graph> createGraphs() {
        return Stream.of(
            addData(Graphs.adjacencyListGraph()),
            addData(Graphs.adjacencyMatrixGraph()),
            addData(Graphs.adjacencyHashGraph())
        );
    }

    private static Graph addData(@NonNull final Graph graph) {
        graph.setEdge(0, 1, 1);
        graph.setEdge(0, 2, 1);
        graph.setEdge(1, 3, 1);
        graph.setEdge(4, 1, 1);
        graph.setEdge(5, 2, 1);
        graph.setEdge(5, 6, 1);
        graph.setEdge(6, 4, 1);
        graph.setEdge(6, 0, 1);

        return graph;
    }
}
