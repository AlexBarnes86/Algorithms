package com.toastedbits.algs.gfg.graphs;

import com.toastedbits.algs.gfg.graphs.common.Graph;
import com.toastedbits.algs.gfg.graphs.common.Graphs;
import com.toastedbits.algs.gfg.graphs.common.algorithms.GraphTopologicalSort;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;

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
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(5, 0);
        graph.addEdge(5, 2);

        return graph;
    }
}
