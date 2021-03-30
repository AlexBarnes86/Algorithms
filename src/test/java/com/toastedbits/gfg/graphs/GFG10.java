package com.toastedbits.gfg.graphs;

import com.toastedbits.gfg.graphs.common.Graph;
import com.toastedbits.gfg.graphs.common.Graphs;
import com.toastedbits.gfg.graphs.common.algorithms.GraphLevelCount;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

//Count the number of nodes at given level in a tree using BFS
//https://www.geeksforgeeks.org/count-number-nodes-given-level-using-bfs/

@Slf4j
public class GFG10 {
    @ParameterizedTest
    @MethodSource("createGraphs")
    public void testLevelCounting(@NonNull final Graph graph) {
        GraphLevelCount.Results results = GraphLevelCount.solve(graph);

        log.debug("Levels: {}", results.getLevels());
        log.debug("Max Verts: {}", results.getMaxVerts());
        log.debug("Max Level: {}", results.getMaxLevel());

        assertThat(results.getMaxLevel(), equalTo(2));
        assertThat(results.getMaxVerts(), hasItems(3, 4, 5, 6));
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
        graph.setEdge(1, 3);
        graph.setEdge(1, 4);
        graph.setEdge(1, 5);
        graph.setEdge(2, 6);

        return graph;
    }
}
