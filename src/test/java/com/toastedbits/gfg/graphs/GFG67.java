package com.toastedbits.gfg.graphs;

import com.toastedbits.gfg.graphs.common.Graph;
import com.toastedbits.gfg.graphs.common.Graphs;
import com.toastedbits.gfg.graphs.common.algorithms.GraphTopologicalSort;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;

//Topological Sorting
//https://www.geeksforgeeks.org/topological-sorting/

@Slf4j
public class GFG67 {
    @ParameterizedTest
    @MethodSource("createGraphs")
    public void test(@NonNull final Graph graph) {
        log.debug("Graph {}:\n{}", graph.getClass(), graph);
        List<Integer> topoSort = GraphTopologicalSort.sort(graph);
        log.debug("Topological Sort: {}", topoSort);
        assertThat("sort order violated", topoSort.indexOf(0) > topoSort.indexOf(4));
        assertThat("sort order violated", topoSort.indexOf(0) > topoSort.indexOf(5));
        assertThat("sort order violated", topoSort.indexOf(1) > topoSort.indexOf(4));
        assertThat("sort order violated", topoSort.indexOf(1) > topoSort.indexOf(3));
        assertThat("sort order violated", topoSort.indexOf(2) > topoSort.indexOf(5));
        assertThat("sort order violated", topoSort.indexOf(3) > topoSort.indexOf(2));
    }

    private static Stream<Graph> createGraphs() {
        return Stream.of(
            addData(Graphs.adjacencyListGraph()),
            addData(Graphs.adjacencyMatrixGraph()),
            addData(Graphs.adjacencyHashGraph())
        );
    }

    /*
     5+----->0<------+4
     +                +
     |                |
     |                |
     v                v
     2+----->3+------>1
    */
    private static Graph addData(@NonNull final Graph graph) {
        graph.setEdge(2, 3);
        graph.setEdge(3, 1);
        graph.setEdge(4, 0);
        graph.setEdge(4, 1);
        graph.setEdge(5, 0);
        graph.setEdge(5, 2);

        return graph;
    }
}
